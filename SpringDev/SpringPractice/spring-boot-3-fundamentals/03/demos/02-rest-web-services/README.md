# Spring Boot 3 Fundamentals

These examples and exercises are part of the course "Spring Boot 3 Fundamentals" by Jesper de Jong at [Pluralsight](https://www.pluralsight.com/).

## Building REST Web Services

In this module, we've created REST web services to get information about events, organizers, venues and products and to register attendees for events.

Steps:

- Create records that represent the data model for the demo application.
- Create repository classes (data access objects) to manage the data in the application.
- Create Spring Web MVC REST controller classes to define web services.
- Create a controller advice class to handle exceptions thrown from the controller classes.
- Use Bean Validation to validate request data.

## Running the application and testing the web services

The minimum version of Java that is required by Spring Framework 6 and Spring Boot 3 is Java 17. Make sure you have JDK 17 or newer installed.

You can build an run the application from your IDE or from the command line using the Spring Boot Maven plugin:

On Windows:

    mvnw.cmd spring-boot:run

On macOS or Linux:

    ./mvnw spring-boot:run

Then you can use an HTTP client tool to call the web services. Examples using `curl`:

Get information about the organizers of events:

    curl http://localhost:8080/organizers

Get the events for a specific organizer (in this example organizer 101; note that quotes are necessary because the shell might interpret the question mark
in the URL):

    curl "http://localhost:8080/events?organizerId=101"

Get the details of a specific event (in this example event 501):

    curl http://localhost:8080/events/501

Get the available products for an event:

    curl "http://localhost:8080/products?eventId=501"

Register an attendee:

    curl http://localhost:8080/registrations -H "Content-Type: application/json" -d '{"productId": 801, "attendeeName": "Jesper de Jong"}'
