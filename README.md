# demo-rest
Spring Boot, Spring Data, REST and H2

## Project Information
Simple project that enables a single REST endpoint to demonstrate
a simple GET, PUT, POST, DELETE operation implementing a full
stack server.

## Local Usage
You will need to have the _maven_pom_ project installed into your
local Maven repository or access to the GitHub repository version.

Maybe GitHub will allow anonymous access to the project repository
in the future.

Access REST endpoints:

    http://localhost:8080/user/1/todo
    http://localhost:8080/user/2/todo

    http://localhost:8080/user/1/todo/1
    http://localhost:8080/user/1/todo/2
    http://localhost:8080/user/2/todo/1

    http://localhost:8080/hello

    http://localhost:8080/helloName
    http://localhost:8080/helloName?name=Fred
