# Spring Boot 3 Fundamentals

These examples and exercises are part of the course "Spring Boot 3 Fundamentals" by Jesper de Jong at [Pluralsight](https://www.pluralsight.com/).

## Monitoring Using Production-ready Features

In this module, you've learned about Spring Boot Actuator. This is a feature of Spring Boot that enables you to monitor and manage your Spring Boot application.

By adding Spring Boot Actuator as a dependency and enabling and exposing endpoints through HTTP, you can get information about how your application is doing
from the actuator web service.

## Building and running the application

The minimum version of Java that is required by Spring Framework 6 and Spring Boot 3 is Java 17. Make sure you have JDK 17 or newer installed.

Make sure that you have Docker installed. Docker Desktop is available for Windows, macOS and Linux from [docker.com](https://www.docker.com).

First, start MongoDB and PostgreSQL in Docker containers using Docker Compose:

    docker compose up -d

Then, build and run the events service with the "testdata" profile:

On Windows:

    cd events-service
    mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=testdata

On macOS or Linux:

    cd events-service
    ./mvnw spring-boot:run -Dspring-boot.run.profiles=testdata

Run the registration service as well.

Perform a `GET` request on the path where Actuator is mapped to in the registration service:

    curl localhost:8080/actuator

This shows you a list of enabled and exposed endpoints.

See the chapter [Production-ready Features](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator) in the Spring Boot reference
documentation for more information.

You can stop the databases running in Docker with:

    docker compose down
