# TodoManager
1. Tech Stack
- JDK 11
- Maven
- Spring Boot 2.5.2

2. ToDoManager exposes RESTFUL API at port 8443 for CRUD capabilities


3. Assumptions
- authentication is out of scope, client is already authenticated to call the endpoints


4. To run up ToDoManager locally and test its endpoints using ToDoManager.postman_collection.json under src/main/resources
`mvn clean spring-boot:run`


5. Test
`mvn clean test`