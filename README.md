# Todo list API made with Springboot framework

This API was made during the course of **Rocketseat**, where it was taught some concepts about java and the Springboot framework.

It was used planetscale database to store and retrieve data.

This API was deployed onrender using a Dockerfile. If you want to check it out click the link below.

<a href="https://java-spring-boot-todolist.onrender.com/"> Link of the API deployed on render.</a>

### Check the Swagger Documentation by clicking the link bellow:

<a href="https://java-spring-boot-todolist.onrender.com/swagger-ui">Swagger UI docs</a>

## Fuctionalities

This API is able to:

- Create a new user;
  - endpoint: `/users/register`


- Create a new task associated to an existing user;
  - endpoint: `/tasks`
  - For this route you need to set a basic auth authorization of an existing user, set the username and email.


- List all task from a user;
  - endpoint: `/tasks` 
  - For this route you need to set a basic auth authorization of an existing user, set the username and email.


- Update a task;
  - endpoint: `/tasks/:taskId`
  - For this route you need to set a basic auth authorization of an existing user, set the username and email.