# RickAndMortyService

## Descripción
This project is a Spring Boot API that acts as an intermediary between the client and the public Rick and Morty API. It allows users to obtain detailed information about the characters from the "Rick and Morty" series through a RESTful endpoint.

## Features
Query information about any character using their ID.
Returns character data such as name, status, species, and episode count appearances.
Formats the response in a custom schema that includes details about the character's origin.
## Requirements
* Java JDK 17
* Spring Boot 3.2.4
* Gradle
* Git
* Heroku CLI (optional)

## Running the API
To run the API, execute the following command in the root directory of the project:

Clean the project (optional):
```
./gradlew clean
```

Build the project: This step compiles your project and creates the executable JAR.
```
./gradlew build
```
Run the application: To start the Spring Boot application, use the bootRun command which runs the project as an application.
```
gradle bootRun
```

Run tests:
```
./gradlew test
```

Run Jacoco test coverage report (optional):
```
./gradlew jacocoTestReport
```

## API Endpoints
To get information on a character, make a GET request to the following endpoint:
```
curl --location 'http://localhost:8080/character/7'
```

To get information on a character from the deployed API on Heroku, make a GET request to the following endpoint:
``` 
curl --location 'https://rick-and-morty-service-fdf0de93e292.herokuapp.com/character/7'
```
