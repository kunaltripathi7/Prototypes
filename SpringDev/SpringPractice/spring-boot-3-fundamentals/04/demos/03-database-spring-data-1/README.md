# Spring Boot 3 Fundamentals

These examples and exercises are part of the course "Spring Boot 3 Fundamentals" by Jesper de Jong at [Pluralsight](https://www.pluralsight.com/).

## Accessing Databases with Spring Data

In this module, we've modified the demo application to store attendee registrations in MongoDB and event information in PostgreSQL.

This is the first step, in which we're using MongoDB. In the next step we'll also use PostgreSQL.

Steps:

- Run MongoDB ans PostgreSQL in Docker.
- Change the `Registration` record to represent a MongoDB document.
- Change `RegistrationRepository` into an interface that extends `MongoRepository`.
- Change the records that store information about organizers, venues, events and products into JPA entity classes.
- Change the repositories for those entity classes into interfaces that extend `JpaRepository`.
- Configure the application appropriately.

## Building and running the application

The minimum version of Java that is required by Spring Framework 6 and Spring Boot 3 is Java 17. Make sure you have JDK 17 or newer installed.

Make sure that you have Docker installed. Docker Desktop is available for Windows, macOS and Linux from [docker.com](https://www.docker.com).

First, start MongoDB in a Docker container using Docker Compose:

    docker compose up -d

You can build an run the application from your IDE or from the command line using the Spring Boot Maven plugin:

On Windows:

    mvnw.cmd spring-boot:run

On macOS or Linux:

    ./mvnw spring-boot:run

You can inspect the content of the MongoDB database by opening a Mongo shell in the running MongoDB container.

First find the name of the running container with `docker ps`. The name of the container will most likely be: `03-database-spring-data-1-mongo-1`.

Then run Mongo shell with a command like this (where you use the name of the MongoDB container):

    docker exec -it 03-database-spring-data-1-mongo-1 mongosh

In the shell, use the command `show collections` to show the collections that exist in the database. After you have created registrations by sending
POST requests to the application, there is a collection named `registrations`. See the content of the collection with a query command like this:

    db.registrations.find()

Note: you can send a POST request using `curl` if you have that available on your computer, or using any other HTTP client tool:

    curl http://localhost:8080/registrations -H "Content-Type: application/json" -d '{"productId": 801, "attendeeName": "Jesper de Jong"}'
