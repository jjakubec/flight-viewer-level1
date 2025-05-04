# FlightViewer Application

## Build Instructions
To build the application, run the following Maven command:

```sh
mvn clean install -DskipTests
```
This command will clean the project, install the dependencies, and skip the tests to speed up the build process.

## Running the Application

To run the application, run the following Maven command:

```sh
mvn spring-boot:run
``` 

This command will start the Spring Boot application.

## Testing the Application

To run the tests, use the following Maven command:

```sh
mvn test
``` 

This command will execute all the tests in the project.

## Notes

The application returns HTTP status messages in Czech language to provide user-friendly responses in Postman and other API testing tools.

Ensure that your API client supports UTF-8 encoding for proper text display.

For development configurations, check the application.properties