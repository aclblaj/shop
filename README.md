# Shop Spring Boot Web Service

This project is a sample Spring Boot web service for database-backed address management.

## Features
- Exposes REST endpoints for address lookup and management
- Uses Spring Boot 4.1.1 and Spring Data JPA
- Supports SQLite (default), PostgreSQL (via `postgres` profile), and H2 (for tests)
- Inserts 10 randomly generated sample addresses on startup when the SQLite database is empty
- Example endpoint:
  - `GET http://localhost:8000/adresa?zc=500041` — returns all addresses for the given postal code

## Getting Started

### Prerequisites
- Java 21+
- Maven 3.9+

### Build & Run

By default, the application uses the `sqlite` profile and creates `shop.db` in the current working directory:
```sh
mvn clean package
java -jar target/shop-0.0.1-SNAPSHOT.war
```

Or run directly with Maven:
```sh
mvn spring-boot:run
```

#### Switching to PostgreSQL
Set connection values through environment variables and activate the `postgres` profile:
```sh
export DB_URL=jdbc:postgresql://localhost:5432/shop
export DB_USERNAME=postgres
export DB_PASSWORD=your-password
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

On PowerShell, set the variables with `$env:DB_URL`, `$env:DB_USERNAME`, and `$env:DB_PASSWORD` before running Maven. The packaged WAR can also be started with:
```sh
java -jar target/shop-0.0.1-SNAPSHOT.war --spring.profiles.active=postgres
```

### Configuration
- **SQLite (default)**: `src/main/resources/application-sqlite.properties`.
- **PostgreSQL**: `src/main/resources/application-postgres.properties` (also available through the `postgresql` profile).

The SQLite seeder runs only with the `sqlite` profile. It does not add sample rows to PostgreSQL or H2.

## Testing
Run all tests, including the SQLite persistence and REST integration test, with:
```sh
mvn test
```

## Project Structure
- `src/main/java/utbv/shop/` — main application code
- `src/main/resources/` — configuration and static resources
- `src/test/java/utbv/shop/` — tests

## Endpoints
- `GET /adresa?zc=<postal_code>` — List all addresses with a postal code
- `GET /adresa/{id}` — Read an address by ID
- `POST /adresa` — Create an address
- `PUT /adresa/{id}` — Update an address by ID
- `DELETE /adresa/{id}` — Delete one address by ID
- `DELETE /adresa?zc=<postal_code>` — Delete all addresses with a postal code

## License
This project is for educational/demo purposes.
