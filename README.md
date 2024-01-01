# Flight Search API

Flight Search API is a RESTful API for managing flight information.

## Technologies

- Spring Boot 3
- Java 17
- PostgreSQL
- Maven
- Swagger (API documentation - SpringDoc-OpenAPI)

## Getting Started

To run the project on your local machine, follow the steps below.

### Requirements

- Java 17
- Maven

### Installation

1. Clone the project:

   ```bash
   git clone https://github.com/erenuygur/flight-search-api.git
   ```
2. Navigate to the project directory:

   ```bash
   cd flight-search-api
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn java -jar target/flight-search-api-1.0.0.jar
   ```
### Security Considerations
For security reasons, certain configuration details such as API keys, passwords, and sensitive information are not included in this public repository. Follow the steps below to configure these details:
   ```
      username: admin
      password: admin
   ```
Database Configuration: Configure the database connection details in the application.properties file.
   ```
      spring.datasource.url=jdbc:postgresql://localhost:5432/flight_search
      spring.datasource.username=postgres
      spring.datasource.password=1234
   ```

Once the application is running, you can access the API documentation at http://localhost:8080/swagger-ui.html.

![Swagger](/content/SwaggerDoc.png "End Points").



# Flight-Search-API
