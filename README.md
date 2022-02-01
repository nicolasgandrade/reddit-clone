# Spreddit - Backend
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Heroku](https://img.shields.io/badge/heroku-%23430098.svg?style=for-the-badge&logo=heroku&logoColor=white)
![Reddit](https://img.shields.io/badge/Reddit-FF4500?style=for-the-badge&logo=reddit&logoColor=white)

> Spreddit is a Reddit clone made in Spring Boot. This API was made to my portfolio and was build with Spring Boot, Spring Data (JPA), Spring Security (JWT Authentication) and PostgreSQL.

<h4 align="center"> 
	Heroku deploy:<br>
	:gear: API: https://spreddit-backend.herokuapp.com/swagger-ui/#/ :gear:
</h4>

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
* Read the documentation in `https://spreddit-backend.herokuapp.com/swagger-ui/#/`
* Transform application.properties.example to `application.properties`.
* Create an account in `MailTrap` and replace the fields in `application.properties` to you informations.
* Create a database called `redditclone` in your PostgreSQL.
* Replace the port of you database. If you didn't change it before, it must be `5432`.
* Change the method `getApiInfo` from SwaggerConfig class to your information.

Now you can start the API.

## ☕ Testing Spreddit

You can access https://spreddit-backend.herokuapp.com/swagger-ui/#/ to see the documentation.
If you're running in your local machine, you can acces `localhost:<your-port-usually-8080>/swagger-ui/`.

<b>PS.:</b> Remember to use a HttpClient to try the API (Postman, Insomnia etc).

Reading the documentation, do the following things to test Spreddit:
1. Access the AuthController endpoint to register or login. Here, I'm using a free account of MailTrap for email testing, so if you try to register a new account with your email, you won't receive the confirmation token. So, you can login using username: `demouser1` and password: `demouser1`.
2. When you log in, you will receive a Bearer token. From now on, you'll need to authenticate with your token for each request you make. In Postman, you can click on "Authorization" tab, select "Bearer Token" and paste your token.
3. Pay Attention! Each token expires in 15 minutes after created. When you logged in, you received a "refreshToken". Store this data and make a POST to `/api/auth/refresh/token` with your username and the refresh token. Read the <a href="https://spreddit-backend.herokuapp.com/swagger-ui/#/auth-controller/refreshTokenUsingPOST" target="_blank">docs</a> for details.
4. Now you can make all the requests listed in the docs.
5. Try to create a subreddit, retrieve them, create a post, vote, comment etc. 😸

