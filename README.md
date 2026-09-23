# Job Application Tracker API

A RESTful backend application for managing and tracking job applications throughout the recruitment process.

Built with **Java 21** and **Spring Boot 3**, the project demonstrates practical backend development with **Spring Security, JWT authentication, PostgreSQL, JPA/Hibernate, validation, file handling, pagination, filtering, testing, and OpenAPI documentation**.

## ✨ Features

### 🔐 Authentication & Security

* User registration and login
* Stateless JWT-based authentication
* Secure password hashing with Spring Security
* Protected API endpoints
* User-specific application data

### 💼 Job Application Management

* Create, read, update, and delete job applications
* Track application status throughout the recruitment process
* Store company, job title, salary, applied date, and notes
* Associate applications with authenticated users

### 📄 Resume Management

* Upload resume files for job applications
* Download and delete attached resumes
* Support for PDF and DOCX files
* Local file storage

### 🔎 Pagination, Sorting & Filtering

* Paginated application lists
* Sorting by supported fields
* Filtering by application status
* Multi-field filtering

### 📚 API Documentation

* OpenAPI 3 specification
* Interactive Swagger UI
* API endpoint exploration and testing directly from the browser

### 🐳 Docker Support

* Dockerized application
* PostgreSQL container
* Docker Compose configuration for local development

### 🧪 Testing

* Automated tests with Spring Boot
* H2 in-memory database for testing
* Spring Security testing support

## 🛠 Tech Stack

| Category              | Technology                     |
| --------------------- | ------------------------------ |
| Language              | Java 21                        |
| Framework             | Spring Boot 3.2                |
| Security              | Spring Security + JWT          |
| Database              | PostgreSQL                     |
| Testing Database      | H2                             |
| Persistence           | Spring Data JPA / Hibernate    |
| Object Mapping        | MapStruct                      |
| Boilerplate Reduction | Lombok                         |
| API Documentation     | SpringDoc OpenAPI / Swagger UI |
| Build Tool            | Maven                          |
| Containerization      | Docker / Docker Compose        |

## 🏗 Architecture

The application follows a **layered architecture** that separates API handling, business logic, data access, and supporting components.

```text
Client
  │
  ▼
Controller
  │
  ▼
Service
  │
  ▼
Repository
  │
  ▼
PostgreSQL
```

Additional components handle cross-cutting concerns such as:

* JWT authentication and security
* Request validation
* DTO mapping with MapStruct
* Global exception handling
* File storage
* Application configuration

### Main Components

```text
src/main/java/com/jobtracker/jobapplicationtracker

├── config
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
├── security
└── service
```

## 🔐 Authentication Flow

The API uses **stateless JWT-based authentication**.

```text
Register
   ↓
Login
   ↓
JWT Token
   ↓
Authorization Header
   ↓
Protected API Endpoint
```

Authenticated requests use:

```http
Authorization: Bearer <JWT_TOKEN>
```

## 📌 API Endpoints

### Authentication

```http
POST /api/auth/register
POST /api/auth/login
```

### Job Applications

```http
GET    /api/applications
GET    /api/applications/{id}
POST   /api/applications
PUT    /api/applications/{id}
DELETE /api/applications/{id}
```

### Resume

```http
POST   /api/applications/{id}/resume
GET    /api/applications/{id}/resume
DELETE /api/applications/{id}/resume
```

> Endpoint paths may change as the project evolves. See Swagger UI for the current API specification.

## 📖 API Documentation

After starting the application, Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI specification:

```text
http://localhost:8080/v3/api-docs
```

Swagger UI provides an interactive interface for exploring and testing the REST API.

## 🚀 Getting Started

### Prerequisites

Make sure the following are installed:

* Java 21
* Maven 3.8+
* Docker
* Docker Compose

### 1. Clone the Repository

```bash
git clone https://github.com/Farzaneh-Nasrabadii/job-application-tracker.git

cd job-application-tracker
```

### 2. Start PostgreSQL

Start the PostgreSQL container using Docker Compose:

```bash
docker compose up -d
```

Check the running containers:

```bash
docker ps
```

### 3. Build the Application

```bash
mvn clean package
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

## ⚙️ Configuration

Application configuration is managed through environment variables.

Example:

```env
DB_URL=jdbc:postgresql://localhost:5432/jobtracker
DB_USERNAME=postgres
DB_PASSWORD=your_password

JWT_SECRET=your_secret_key
JWT_EXPIRATION=86400000
```

For security reasons, sensitive credentials should never be committed to the repository.

## 🧪 Running Tests

Run the complete test suite with:

```bash
mvn test
```

The test environment uses an **H2 in-memory database**, so a separate PostgreSQL instance is not required for running tests.

## 🐳 Running with Docker Compose

The application can also be built and started together with its supporting services:

```bash
docker compose up --build
```

To stop the containers:

```bash
docker compose down
```

## 📂 Project Structure

```text
job-application-tracker/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/jobtracker/jobapplicationtracker/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── entity/
│   │   │       ├── exception/
│   │   │       ├── mapper/
│   │   │       ├── repository/
│   │   │       ├── security/
│   │   │       └── service/
│   │   │
│   │   └── resources/
│   │
│   └── test/
│
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
```

## 🎯 Project Goals

This project was developed to practice and demonstrate practical backend development concepts, including:

* RESTful API design
* Authentication and authorization
* JWT-based security
* Secure password handling
* Relational database design
* JPA and Hibernate
* DTO-based API design
* MapStruct mapping
* Input validation
* Exception handling
* File upload and storage
* Pagination, sorting, and filtering
* OpenAPI documentation
* Docker-based development
* Automated testing

## 👩‍💻 Author

**Farzaneh Nasrabadi**

Aspiring Backend Developer focused on **Java, Spring Boot, and backend development**.

[GitHub](https://github.com/Farzaneh-Nasrabadii)

---

⭐ If you find this project useful, feel free to explore the code and follow the repository.
