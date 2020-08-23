# endpoint-challenge
Endpoint Challenge


Run ```chmod +x start.sh``` to make the start script executable.

Then run ```./start.sh```.  This script runs: 

- ```mvn spring-boot:build-image``` which will build the docker container for the web app. 
- ```docker-compose up``` Which will bring the database and the the web-app containers up.



# Design

A few notes on the design of the web app.

- Uses Flyway for database migration. You can see any SQL statements in src/main/resources/db.migration
- Uses JOOQ to generate Classes that represent the underlying Data Model and provides a type safe DSL for SQL in Java.
- Uses SimpleFlatMapper to map JOOQ Result Sets into DAOs (SimpleFlatMapper does a lot of magic on One-to-many relationships when fetching into Data Structures)
- Uses MapStruct to map DAOs to DTOs to decouple the Database from the API Layer, preventing any data model changes from breaking the API. (Could be achieved with SimpleFlatMapper as well, but used this to show some variance in different approaches)
- Uses Hibernate Constraint Validators for bean/param validation.
- Tests written with Mockito/JUnit
