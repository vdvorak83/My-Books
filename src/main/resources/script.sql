CREATE SEQUENCE hibernate_sequence;

CREATE SEQUENCE logs_id_seq;

CREATE TYPE SEXES AS ENUM ('male', 'female');

CREATE TYPE BOOK_STATUSES AS ENUM ('Currently Reading', 'Read', 'To-Read', 'Stopped Reading');

CREATE TABLE users
(
  id                 SERIAL                                                             NOT NULL
    CONSTRAINT pk_users
    PRIMARY KEY,
  username           VARCHAR(20)                                                        NOT NULL,
  role               VARCHAR(50)                                                        NOT NULL,
  hash_password      VARCHAR(255)                                                       NOT NULL,
  name               VARCHAR(255),
  email              VARCHAR(255)                                                       NOT NULL
    CONSTRAINT proper_email
    CHECK ((email) :: TEXT ~* '^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$' :: TEXT),
  phone              VARCHAR(20)                                                        NOT NULL
    CONSTRAINT proper_phone_number
    CHECK ((phone) :: TEXT ~* '^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$' :: TEXT),
  has_profile_photo  BOOLEAN DEFAULT FALSE                                              NOT NULL,
  hash_temp_password VARCHAR(255),
  enabled            BOOLEAN,
  profile_photo_path TEXT DEFAULT 'C:\MyBooks Storage\default\user_default.png' :: TEXT NOT NULL
);

CREATE UNIQUE INDEX users_email_uindex
  ON users (email);

CREATE UNIQUE INDEX users_phone_uindex
  ON users (phone);

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
    message := 'Add new user ';
    log_str := message || user_info;
    INSERT INTO logs (text, date) VALUES (log_str, NOW());
    RETURN NEW;
  ELSIF TG_OP = 'UPDATE'
    THEN
      user_info = NEW.email;
      message := 'Update user ';
      log_str := message || log_str;
      INSERT INTO logs (text, date) VALUES (log_str, NOW());
      RETURN NEW;
  ELSIF TG_OP = 'DELETE'
    THEN
      user_info = OLD.email;
      message := 'Remove user ';
      log_str := message || user_info;
      INSERT INTO logs (text, date) VALUES (log_str, NOW());
      RETURN OLD;
  END IF;
END;
$$;

CREATE TRIGGER t_user
AFTER INSERT OR UPDATE OR DELETE
  ON users
FOR EACH ROW
EXECUTE PROCEDURE users_add_to_log();

CREATE TABLE books
(
  id          INTEGER NOT NULL
    CONSTRAINT pk_books
    PRIMARY KEY,
  author_id   INTEGER,
  title       VARCHAR NOT NULL,
  description TEXT,
  rating      REAL
);

CREATE TABLE authors
(
  id            INTEGER     NOT NULL
    CONSTRAINT pk_authors
    PRIMARY KEY,
  name          VARCHAR(20) NOT NULL,
  last_name     VARCHAR(20),
  date_of_birth INTEGER,
  date_of_death INTEGER
);

ALTER TABLE books
  ADD CONSTRAINT fk_books
FOREIGN KEY (book_author) REFERENCES authors;

ALTER TABLE books
  ADD CONSTRAINT fkfjixh2vym2cvfj3ufxj91jem7
FOREIGN KEY (book_author) REFERENCES authors;

CREATE TABLE books_rating
(
  user_id     INTEGER
    CONSTRAINT fk_user_books_rating
    REFERENCES users,
  book_id     INTEGER
    CONSTRAINT fk_book_books_rating
    REFERENCES books,
  user_rating REAL
);

CREATE TABLE users_books
(
  user_id           INTEGER NOT NULL
    CONSTRAINT fk_user_books_user_id
    REFERENCES users,
  book_id           INTEGER NOT NULL
    CONSTRAINT fk_user_books_book_id
    REFERENCES books,
  book_status       BOOK_STATUSES,
  users_book_rating DOUBLE PRECISION,
  CONSTRAINT pk_user_book
  PRIMARY KEY (book_id, user_id)
);

CREATE TABLE roles
(
  id   INTEGER     NOT NULL
    CONSTRAINT roles_pkey
    PRIMARY KEY,
  role VARCHAR(50) NOT NULL
);


ALTER TABLE users
  ADD CONSTRAINT users_roles_role_fk
FOREIGN KEY (role) REFERENCES roles (role);

CREATE TABLE persistent_logins
(
  username  VARCHAR(64) NOT NULL,
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

CREATE TABLE logs
(
  text TEXT                                               NOT NULL,
  date TIMESTAMP DEFAULT now()                            NOT NULL,
  id   INTEGER DEFAULT nextval('logs_id_seq' :: REGCLASS) NOT NULL
    CONSTRAINT logs_pkey
    PRIMARY KEY
);

CREATE VIEW view_all_authors_books AS
  SELECT
    res.author_id,
    res.name,
    res.last_name,
    res.title,
    res.description,
    res.rating
  FROM ((SELECT
           b.book_author,
           b.title,
           b.description,
           b.rating
         FROM books b) bb
    JOIN authors a ON ((bb.author_id = a.id))) res;
