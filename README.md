# Ente Chilavu – Expense Manager

A personal expense management application built using **React** and **Spring Boot**. The application helps users manage their expenses through a secure, JWT-based authentication system.

## Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven

### Frontend
- React
- JavaScript
- HTML5
- CSS3
- Axios
- React Router

## Prerequisites

Make sure the following tools are installed:

- Java 21+
- Node.js and npm
- PostgreSQL 18
- Maven
- Git

## Project Structure

```text
ente-chilavu/
│
├── ente-chilavu-backend/
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
└── ente-chilavu-frontend/
    ├── src/
    ├── public/
    └── package.json
```

## Database Setup

Install PostgreSQL using Homebrew on macOS:

```bash
brew install postgresql@17
brew services start postgresql@17
```

Create a database user:

```sql
CREATE USER expense_user WITH PASSWORD 'Admin@123';

CREATE DATABASE expense_manager OWNER expense_user;
```

Verify the database connection:

```bash
psql -h localhost -p 5432 -U expense_user -d expense_manager
```


## This part is already available in the code for testing
## Backend Setup

Navigate to the backend directory:

```bash
cd ente-chilavu-backend
```

Configure the database in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/expense_manager
spring.datasource.username=expense_user
spring.datasource.password=your_password

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Run the Spring Boot application:

```bash
./mvnw spring-boot:run
```

Backend will be available at:

```text
http://localhost:8080
```

## Authentication

The application uses JWT-based authentication with Spring Security.

Authentication flow:

1. Users register with their name, email and password.
2. Passwords are securely hashed before storing them in PostgreSQL.
3. Users log in using their email and password.
4. Backend validates the credentials.
5. A JWT access token is generated and returned.
6. The frontend uses the token to authenticate subsequent API requests.

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Authenticate user |

## Running the Application

Start PostgreSQL:

```bash
brew services start postgresql@18
```

Start the backend in one terminal:

```bash
cd ente-chilavu-backend
./mvnw spring-boot:run
```

Start the frontend in another terminal:

```bash
cd ente-chilavu-frontend
npm run dev
```

Open the application:

```text
http://localhost:5173
```
## License

This project is developed for personal learning and development purposes.