# CookBook use case

This is Cook book application.  
At this point it represents the basic template for a microservice.

Group id: nl.valcon
Artifact id: cookbook

## Dependencies (versions are based on parent)
- Java: 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate (included as transitive dependency from Spring Data JPA)
- Actuator
- Lombok
- JSR305
- PostgreSQL
- Flyway
- HikariCP
- Swagger
- Swagger UI
- JUnit
- Mockito

## Content
- Spring boot application file @SpringBootApplication
- Web configuration: Jackson object mapper
- Example controller
- Example data transfer object
- Flyway basic configuration
- application.yaml
- Gradle wrapper
- Dockerfile


### Build information
The following task
```groovy
springBoot  {
    buildInfo()
}
```
allows the build information to automatically be appended to the Spring Boot Actuator info endpoint

## Packaging
Service packaging specific information if any

## Exception handling
Implemented a specialized `@ControllerAdvice` for the Spring Security exceptions
```java
@ControllerAdvice
public class SecurityExceptionHandler implements SecurityAdviceTrait {}
```

## Logging
Logging is implemented using the `Logger` class.

### build.gradle
The CI/CD pipeline uses Gradle to execute tasks. This file is used to translate those tasks to language/framework specific commands.  

### Dockerfile
The base image contains a builds of OpenJDK (version openjdk:17-slim)

## Local application startup
in order to run application locally, you need to start the application by starting docker postgres container via command:
docker-compose -f docker/dependencies.yml up -d
by running the bootrub gradle command, you will start the application:
./gradlew bootRun

### Local deployment with Docker
To run the application in your local docker enviroment you can run `docker-compose up --build`.

## Development guidelines
- Design for failure. Make sure your service can handle situations where the database is temporary unavailable as well as other services you depend on.
- By default your service will run multiple replicas with load balancing. This is done to ensure high availability (so your services still works when a node in the cluster fails) as will as rolling updates.
- Because of that make sure you handle database migrations properly because during deployment old and new versions will be running.
- When you enable Spring Security or likewise make sure the info, health, env and prometheus actuator endpoints do not require authentication
- Use immutable object, and immutable referencing
- Always create package-info.java in all packages, fill it with java docs and add @ParametersAreNonnullByDefault annotation
- Never mark method parameters as @Nullable, overload methods or use Optional instead
- Null asserts should not be done at the beginning of the method
- API fallback should be implemented only when there is some business logic to be done (return default value, revert state...), in case of only throwing exception, exception logging library will take care of it

