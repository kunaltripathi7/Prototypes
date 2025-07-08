# Spring Boot 3 Fundamentals

These examples and exercises are part of the course "Spring Boot 3 Fundamentals" by Jesper de Jong at [Pluralsight](https://www.pluralsight.com/).

## Creating a Spring Boot Application

In this module, we've created an empty Spring Boot application using [Spring Initializr](https://start.spring.io).

## Building and running the application

The minimum version of Java that is required by Spring Framework 6 and Spring Boot 3 is Java 17. Make sure you have JDK 17 or newer installed.

You can build and run the application on the command line using Maven and the Spring Boot Maven plugin. You do not need to install Maven separately -
the project comes with a Maven wrapper script which will automatically download the appropriate version of Maven if necessary.

In the terminal, Windows command prompt or PowerShell, first use the `cd` command to go to the directory where you've unzipped this project.
Then use one of the following commands:

On Windows:

    mvnw.cmd spring-boot:run

On macOS or Linux:

    ./mvnw spring-boot:run

The application doesn't do anything useful at this point. In the following modules, we'll be adding functionality to the application.
