# MyBooks

Website on Spring Boot with authentication system.

Before running: 
- create database and run SQL code in file `script.sql`;
- edit `application.properties` file;
- edit `hibernate.cfg.xml` file

After that just run following command:
```sh
mvn spring-boot:run (or run Application.java)
```

## Features:
 - User Registration;
 - Admin Panel;
 - Persistent Cookies;
 - File Upload;
 - Admins can login as another user, getting temporary passwords;

To add user with administrator privileges just change column `role` in `users` table from `USER` to `ADMIN`
