CREATE SEQUENCE hibernate_sequence;

CREATE SEQUENCE logs_id_seq;

CREATE TYPE SEXES AS ENUM ('male', 'female');

CREATE TYPE USER_STATUSES AS ENUM ('ADMIN', 'USER_DISABLED', 'USER_ENABLED', 'USER_DELETED', 'USER_BANNED');

CREATE TYPE BOOK_STATUSES AS ENUM ('READING', 'TO_READ', 'STOPPED_READING', 'READ');

CREATE TABLE users
(
  id                 SERIAL                                                        NOT NULL
    CONSTRAINT pk_users
    PRIMARY KEY,
  username           VARCHAR(20)                                                   NOT NULL,
  role               VARCHAR(50)                                                   NOT NULL,
  hash_password      VARCHAR(255)                                                  NOT NULL,
  name               VARCHAR(255)                                                  NOT NULL,
  email              VARCHAR(255)                                                  NOT NULL
    CONSTRAINT proper_email
    CHECK ((email) :: TEXT ~* '^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$' :: TEXT),
  phone              VARCHAR(20)                                                   NOT NULL
    CONSTRAINT proper_phone_number
    CHECK ((phone) :: TEXT ~* '^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$' :: TEXT),
  hash_temp_password VARCHAR(255),
  photo              VARCHAR(255) DEFAULT '65345955-f3f5-4f33-9cdd-6de087127b1f.png' :: CHARACTER VARYING,
  status             VARCHAR(64) DEFAULT 'USER_NOT_CONFIRMED' :: CHARACTER VARYING NOT NULL
);

CREATE UNIQUE INDEX users_username_uindex
  ON users (username);

CREATE UNIQUE INDEX users_email_uindex
  ON users (email);

CREATE UNIQUE INDEX users_phone_uindex
  ON users (phone);

CREATE TABLE books
(
  id             INTEGER                                                      NOT NULL
    CONSTRAINT pk_books
    PRIMARY KEY,
  title          VARCHAR                                                      NOT NULL,
  description    TEXT,
  rating         REAL,
  book_author_id INTEGER,
  photo          VARCHAR(255) DEFAULT 'default/book.jpg' :: CHARACTER VARYING NOT NULL
);

CREATE TABLE authors
(
  id            INTEGER                                                        NOT NULL
    CONSTRAINT pk_authors
    PRIMARY KEY,
  name          VARCHAR(20)                                                    NOT NULL,
  last_name     VARCHAR(20),
  date_of_birth INTEGER,
  date_of_death INTEGER,
  photo         VARCHAR(255) DEFAULT 'default/author.png' :: CHARACTER VARYING NOT NULL,
  about         TEXT DEFAULT 'Lorem Ipsum' :: TEXT
);

ALTER TABLE books
  ADD CONSTRAINT fk44cp76hd0fv4lg6cbdruskfvn
FOREIGN KEY (book_author_id) REFERENCES authors;

CREATE TABLE users_books
(
  book_status       VARCHAR(64),
  users_book_rating INTEGER,
  id                SERIAL NOT NULL
    CONSTRAINT users_books_id_pk
    PRIMARY KEY,
  book_id           INTEGER
    CONSTRAINT fkdwwhr7eeuyhofjtfn0xxqimph
    REFERENCES books,
  user_id           INTEGER
    CONSTRAINT fkddv9o0ehcbpn1xdvypcynju0u
    REFERENCES users
);

CREATE UNIQUE INDEX users_books_id_uindex
  ON users_books (id);

CREATE TABLE roles
(
  id   INTEGER     NOT NULL
    CONSTRAINT roles_pkey
    PRIMARY KEY,
  role VARCHAR(50) NOT NULL
);

CREATE UNIQUE INDEX roles_role_uindex
  ON roles (role);

ALTER TABLE users
  ADD CONSTRAINT users_roles_role_fk
FOREIGN KEY (role) REFERENCES roles (role);

CREATE TABLE persistent_logins
(
  username  VARCHAR(64) NOT NULL
    CONSTRAINT persistent_logins_users_username_fk
    REFERENCES users (username),
  series    VARCHAR(64) NOT NULL
    CONSTRAINT persistent_logins_pkey
    PRIMARY KEY,
  token     VARCHAR(64) NOT NULL,
  last_used TIMESTAMP   NOT NULL
);

CREATE TABLE file_info
(
  id                 BIGSERIAL NOT NULL
    CONSTRAINT file_info_pkey
    PRIMARY KEY,
  original_file_name VARCHAR(255),
  size               BIGINT,
  storage_file_name  VARCHAR(255),
  type               VARCHAR(255),
  url                VARCHAR(255)
);

CREATE UNIQUE INDEX file_info_storage_file_name_uindex
  ON file_info (storage_file_name);

ALTER TABLE users
  ADD CONSTRAINT users_file_info_storage_file_name_fk
FOREIGN KEY (photo) REFERENCES file_info (storage_file_name);

ALTER TABLE books
  ADD CONSTRAINT books_file_info_storage_file_name_fk
FOREIGN KEY (photo) REFERENCES file_info (storage_file_name);

ALTER TABLE authors
  ADD CONSTRAINT authors_file_info_storage_file_name_fk
FOREIGN KEY (photo) REFERENCES file_info (storage_file_name);

CREATE TABLE logs
(
  text TEXT                                               NOT NULL,
  date TIMESTAMP DEFAULT now()                            NOT NULL,
  id   INTEGER DEFAULT nextval('logs_id_seq' :: REGCLASS) NOT NULL
    CONSTRAINT logs_pkey
    PRIMARY KEY
);

CREATE TABLE testing
(
  user_id INTEGER NOT NULL,
  book_id INTEGER NOT NULL,
  id      SERIAL  NOT NULL
    CONSTRAINT testing_id_pk
    PRIMARY KEY
);

CREATE UNIQUE INDEX testing_id_uindex
  ON testing (id);

CREATE VIEW view_all_authors_books AS
  SELECT
    res.book_author_id,
    res.name,
    res.last_name,
    res.title,
    res.description,
    res.rating
  FROM ((SELECT
           b.book_author_id,
           b.title,
           b.description,
           b.rating
         FROM books b) bb
    JOIN authors a ON ((bb.book_author_id = a.id))) res;

CREATE FUNCTION users_add_to_log()
  RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
DECLARE
  message   VARCHAR(30);
  user_info VARCHAR(100);
  log_str   VARCHAR(254);
BEGIN
  IF TG_OP = 'INSERT'
  THEN
    user_info = NEW.email;
    message := 'ADDED user ';
    log_str := message || user_info;
    INSERT INTO logs (text, date) VALUES (log_str, NOW());
    RETURN NEW;
  ELSIF TG_OP = 'UPDATE'
    THEN
      user_info = NEW.email;
      message := 'UPDATED user ';
      log_str := message || user_info;
      INSERT INTO logs (text, date) VALUES (log_str, NOW());
      RETURN NEW;
  ELSIF TG_OP = 'DELETE'
    THEN
      user_info = OLD.email;
      message := 'DELETED user ';
      log_str := message || user_info;
      INSERT INTO logs (text, date) VALUES (log_str, NOW());
      RETURN OLD;
  END IF;
END;
$$;
