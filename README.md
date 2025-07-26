# Notification System
Simple system for sending notifications via multiple channels (SMS, E-Mail, Push Notification) with filtering by message category and user preferences.

## Description
This project implements an architecture based on best design practices, using patterns such as Strategy, Factory, SOLID principles, and validation via DTO.

### Main features:
Sending messages to users subscribed to specific categories (Sports, Finance, Movies)

-Users define their preferred channels (SMS, E-Mail, Push)

-Persistent logs for each notification sent, including success/failure info, timestamps, and user data

- Input data validation using Bean Validation (`@NotNull`, `@NotBlank`)

- Structure prepared for easy extension and maintenance

## Technologies
Java 17+

Spring Boot 3+

JPA / Hibernate

Bean Validation (Jakarta Validation)

Lombok

Relational database (e.g., H2 for testing, Postgres or MySQL in production)

## Project Structure
**DTO**: Classes for data transfer and validation (e.g., MessageDto)

**Entity**: JPA models (User, NotificationLog)

**Enum**: Fixed types (Category, ChannelType)

**Strategy**: Interfaces and implementations of notification channels (SmsNotification, EmailNotification, PushNotification)

**Factory**: Factory to obtain the appropriate strategy based on channel (NotificationSenderFactory)

**Service**: Business logic (NotificationService, UserService)

**Controller**: REST endpoints for sending messages and querying logs (MessageController)

## REST Endpoints
Method	URL	Description	Payload / Response
POST	/api/messages	Send a message to users	JSON { "category": "SPORTS", "content": "..." }
GET	/api/logs	List all notification logs	JSON list of NotificationLog objects

## Example payload for sending


{
  "category": "SPORTS",
  "content": "World Cup final is tonight!"
}
## How to run locally
Clone the repository

Configure the database in application.properties (example for H2)

Run the application with ./mvnw spring-boot:run or using your IDE or mvn spring-boot:run

Access the simple frontend to send messages or use curl/Postman to test the endpoints

## Tests
The project includes unit tests for services and DTO validation. To run tests:


./mvnw test
Applied Best Practices
Input validation: DTOs with Bean Validation to ensure consistent data

Design Patterns: Strategy for notification channels, Factory for dynamic selection

SOLID Principles: Clear separation of concerns, easy extension and maintenance

Error handling: Fault tolerance in sending with complete logs for auditing

Clean Architecture: Well-defined folders and layers for future scalability

## Contact
Developed by Luiz Fernando de Andrade Gadêlha.

If you want, I can help format this nicely or add more sections!






