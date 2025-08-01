# Spring Boot 3 Fundamentals

These examples and exercises are part of the course "Spring Boot 3 Fundamentals" by Jesper de Jong at [Pluralsight](https://www.pluralsight.com/).

## Calling REST Web Services

In this module, you've learned how to call REST web services with Spring with WebClient and with an HTTP interface client.

The project is now a multi-module Maven project, with separate modules for the events service and the registration service.

Steps:

- Run MongoDB ans PostgreSQL in Docker.
- Start the events service with the "testdata" profile to initialize the PostgreSQL database.
- Use `WebClient` in the registration service to call the events service.
- Create interface `EventsClient`, which defines the client for the events service.
- Use `HttpServiceProxyFactory` to generate an implementation of interface `EventsClient`.
- Use `EventsClient` in `RegistrationController` to call the events service.

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

Run the registration service as well and execute a POST request with your favorite HTTP client tool to create a registration. You'll see that the registration
now includes the name of the event and the price of the product, which the registration service retrieved from the events service.

Note: you can send a POST request using `curl` if you have that available on your computer, or using any other HTTP client tool:

    curl http://localhost:8080/registrations -H "Content-Type: application/json" -d '{"productId": 801, "attendeeName": "Jesper de Jong"}'

You can stop the databases running in Docker with:

    docker compose down
