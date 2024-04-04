# Weather Application

This is a Spring Boot application that provides weather information. It fetches data from the OpenWeatherMap API and stores it in a PostgreSQL database.

## Technologies Used

- Java 17
- Spring Boot 2.7.5
- Spring Data JPA
- Spring Cloud OpenFeign
- PostgresSQL
- Gradle
- Lombok
- MapStruct
- Apache Commons Lang
- Hibernate Types
- SpringDoc OpenAPI

## Setup

### Prerequisites

- Java 17
- PostgreSQL

### Configuration

Update the `src/main/resources/application.yaml` file with your database and OpenWeatherMap API details.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/weather?currentSchema=weather
    username: postgres
    password: root
    driver-class-name: org.postgresql.Driver

open:
  weather:
    url: http://api.openweathermap.org
    api-key: your_openweathermap_api_key

API Endpoints
POST /api/weather/get: Get weather information for a given pincode and date.
POST /api/weather/fetch: Fetch OR save weather information for a given pincode and date from OpenWeatherMap API.