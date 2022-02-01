# Spreddit - Backend
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Reddit](https://img.shields.io/badge/Reddit-FF4500?style=for-the-badge&logo=reddit&logoColor=white)

> [Under Development] Spreddit is a Reddit clone made in Spring Boot and Angular. This repository is the backend of the app, made with Spring Boot, Spring Data (JPA), Spring Security (JWT Authentication) and PostgreSQL.


### Adjustments and Improvements

The backend (this repo) is almost complete. However, I'm still building the frontend with Angular 13. These are the main features built so far:
- [x] Register user and login/logout
- [x] Verify account by email-sent verification token
- [x] Use JWT tokens to make requests
- [x] Create Subreddits
- [x] Make posts inside the subreddits
- [x] Comment on posts

## 💻 Requirements

Before starting, make sure you meet the following requirements:
* Install `Java 11`
* Read the documentation in `${depoy url (still not deployed)}/swagger-ui/`
* Transform application.properties.example to `application.properties`.
* Create an account in `MailTrap` and replace the fields in `application.properties` to you informations.
* Create a database called `redditclone` in your PostgreSQL.
* Replace the port of you database. If you didn't change it before, it must be `5432`.
* Change the method `getApiInfo` from SwaggerConfig class to your information.

Now you can start the API.

## ☕ Using Spreddit

You can access `${deploy url (still not deployed)}/swagger-ui/` to see the documentation.
If you're running in your local machine, you can acces `localhost:<your-port-usually-8080>/swagger-ui/`.

