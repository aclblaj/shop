# Shop Spring Boot Web Service

This project is a sample Spring Boot web service for demonstrating database-backed REST APIs. It is designed for use with the SQLLiteDB project, but can be configured for other databases (PostgreSQL, H2, etc.).

## Features
- Exposes REST endpoints for address lookup and management
- Uses Spring Boot (latest version), Spring Data JPA, and Thymeleaf
- Supports PostgreSQL (default), H2 (for tests), and can be adapted for SQLite
- Example endpoint:
  - `GET http://192.168.0.102:8000/adresa?zc=500041` — returns address details for the given postal code

## Getting Started

### Prerequisites
- Java 21+
- Maven 3.9+
- PostgreSQL database (or update `application.properties` for your DB)

### Build & Run
```sh
mvn clean package
java -jar target/shop-0.0.1-SNAPSHOT.war
```

Or run directly with Maven:
```sh
mvn spring-boot:run
```

### Configuration
Edit `src/main/resources/application.properties` to set your database connection:
```
spring.datasource.url=jdbc:postgresql://<host>:<port>/<db>
spring.datasource.username=<user>
spring.datasource.password=<password>
```

For tests, an in-memory H2 database is used automatically.

## Testing
Run all tests with:
```sh
mvn test
```

## Project Structure
- `src/main/java/utbv/shop/` — main application code (package name updated to `utbv.shop`)
- `src/main/resources/` — configuration and static resources
- `src/test/java/utbv/shop/` — tests

## Endpoints
- `GET /adresa?zc=<postal_code>` — Lookup address by postal code

## Upgrading & Maintenance
- The project uses the latest Spring Boot version. To upgrade dependencies, update the `pom.xml` and re-run `mvn clean package`.
- If you change the package name, update all Java source files and configuration accordingly.

## License
This project is for educational/demo purposes.
