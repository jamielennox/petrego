# Petreg

## Overview
Petrego is a REST API that will allow the business to maintain a list of owners, their pets and attributes.

## Technologies Used

The project utilises the following technologies:
- Java 8
- Spring Boot 2
- Gradle 4.10.2 wrapper for build.
- Hibernate 5 for ORM.
- Liquibase for database change management.
- H2 in memory as the data store (for simplicity)

## Design
The following design considerations were made:
- An owner can have more than one pet
- A pet can belong to more than one owner
- A pet only likes one type of food

## Build & Run
The project utilises Gradle Wrapper.
To build the project, browse to the project root directory and simply execute:
```
./gradlew build
```
To run the application, execute:
```
./gradlew bootRun
```
This will initialise SpringBoot on port 8080 where the API can be accessed via:
```
http://localhost:8080
```
### Endpoints
The project is preloaded with sample data.
To get a list of pets that "John Doe" has, open up a browser and browse to:
```
http://localhost:8080/v1/owners/1

Results in:

{
  "ownerId": 1,
  "name": "John Doe",
  "createdDate": "2019-01-05T05:39:12.294+0000",
  "pets": [
    {
      "petId": 1,
      "name": "Rocky",
      "petType": "dog",
      "createdDate": "2019-01-05T05:39:12.294+0000"
    }
  ],
  "_links": {
    "self": {
      "href": "http://localhost:8080/v1/owners/1"
    }
  }
}
```