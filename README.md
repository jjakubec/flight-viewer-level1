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

# API Testing with Postman

To test the API endpoints using Postman, ensure that you send a **GET request** to the appropriate URL.

For example, when testing departures, use:
http://localhost:8080/api/departures?airport=EDDF&begin=2025-04-23T00:00:00&end=2025-04-30T00:00:00

### Steps to Test:
1. Open Postman and create a new request.
2. Set the request type to **GET**.
3. Enter the provided URL in the request field.
4. Click **Send** to fetch the API response.

Make sure the API server is running on **localhost:8080** before making requests.

## 📖 Generate and Open Javadoc Documentation

### ✅ 1. Generate Javadoc
Run the following command in IntelliJ IDEA terminal:

```sh
mvn javadoc:javadoc
```

This will generate the documentation in:

target/reports/apidocs/

### ✅ 2. Open Javadoc in Browser

Once generated, open the documentation using:

### Open Javadoc in Browser

```sh
start target/reports/apidocs/index.html  # Windows CMD
invoke-item target/reports/apidocs/index.html  # Windows PowerShell
xdg-open target/reports/apidocs/index.html  # Linux
open target/reports/apidocs/index.html  # macOS
```

## API Documentation Swagger

For available endpoints and detailed API usage, refer to the Swagger documentation (swagger.json).

## Notes

The application returns HTTP status messages in Czech language to provide user-friendly responses in Postman and other API testing tools.

Ensure that your API client supports UTF-8 encoding for proper text display.

For development configurations, check the application.properties