# RESTful API
 Built with Java 17 and Spring Boot, designed to manage resources for a task management application. 
 This API was created for learning purposes and utilizes an entity for a person, which can have one or more posts. 
 The API adheres to REST principles, ensuring a clean and efficient architecture. 
 
## Technologies Used
- Java 17
- Spring Boot
- Swagger for API documentation
- JUnit and Mockito for testin
- Hibernate Jpa Orm
- Mysql database
- Flyway for database migrations
  

## Project Setup

### Prerequisites

- Java 17
- Maven
- Mysql

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Patricio0022/RestAPI.git   
2. Navigate to the project directory:
   ```bash
   cd RestAPI
3. Run the project
   ```bash
   mvn spring-boot:run
   
## API Documentation
The API documentation is available via Swagger UI. To access it, open your browser and navigate to:    
    http://localhost:8080/swagger-ui/

Ex:   
![Swagger](https://github.com/Patricio0022/RestAPI/raw/main/src/main/resources/static/swagger.png)

## Testing
Tests can be run using the following command:
  ```bash
  mvn test
