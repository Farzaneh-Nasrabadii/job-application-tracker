

# Job Application Tracker API

A RESTful backend application for managing and tracking job applications throughout the recruitment process.

Built with **Java 21**, **Spring Boot 3**, **Spring Security**, **JWT**, and **PostgreSQL**, this project demonstrates backend development practices including authentication, REST API design, data persistence, validation, file handling, pagination, filtering, and API documentation.

## ✨ Features

- 🔐 **JWT Authentication**
  - User registration and login
  - Stateless authentication
  - Password hashing with Spring Security
  - Protected API endpoints

- 💼 **Job Application Management**
  - Create, read, update, and delete applications
  - Track application status
  - Store company, job title, salary, applied date, and notes
  - Associate applications with authenticated users

- 📄 **Resume File Management**
  - Upload resume files
  - Attach resumes to job applications
  - Support for PDF and DOCX documents
  - Local file storage

- 🔎 **Pagination & Filtering**
  - Paginated application lists
  - Sorting
  - Filtering by application status
  - Multi-field filtering

- 📚 **API Documentation**
  - OpenAPI 3 documentation
  - Interactive Swagger UI
  - API endpoint testing directly from the browser

- 🐳 **Docker Support**
  - Dockerized application
  - PostgreSQL container
  - Docker Compose configuration for local development

- 🧪 **Testing**
  - H2 database for tests
  - Spring Boot testing support
  - Spring Security testing support

## 🛠 Tech Stack

| Category | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.2 |
| Security | Spring Security + JWT |
| Database | PostgreSQL |
| Testing Database | H2 |
| Persistence | Spring Data JPA / Hibernate |
| Object Mapping | MapStruct |
| Boilerplate Reduction | Lombok |
| API Documentation | SpringDoc OpenAPI / Swagger UI |
| Build Tool | Maven |
| Containerization | Docker / Docker Compose |

## 🏗 Architecture

The application follows a layered backend architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
````

Additional layers/components handle:

* JWT authentication
* Request validation
* DTO mapping
* Exception handling
* File storage
* Security configuration

### Main Components

```text
src/main/java/com/jobtracker/jobapplicationtracker

├── controller
├── service
├── repository
├── entity
├── dto
├── mapper
├── security
├── exception
└── config
```

## 🔐 Authentication Flow

The API uses JWT-based stateless authentication.

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

## 📌 Example API Endpoints

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

OpenAPI documentation:

```text
http://localhost:8080/v3/api-docs
```

Swagger UI allows you to explore and test the available REST endpoints interactively.

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

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

Using Docker Compose:

```bash
docker compose up -d
```

Check running containers:

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

For security reasons, sensitive credentials should not be committed to the repository.

## 🧪 Running Tests

Run the test suite with:

```bash
mvn test
```

The test environment uses **H2** to avoid requiring a separate PostgreSQL database.

## 🐳 Running with Docker Compose

The recommended local development setup uses Docker Compose:

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

This project was built to practice and demonstrate real-world backend development concepts, including:

* RESTful API design
* Authentication and authorization
* Secure password handling
* JWT-based security
* Relational database design
* JPA and Hibernate
* DTO-based API design
* MapStruct mapping
* Input validation
* Exception handling
* File upload and storage
* Pagination and filtering
* API documentation
* Docker-based development
* Automated testing

## 🔮 Future Improvements

Planned improvements include:

* Cloud-based file storage
* Email notifications for application deadlines
* Advanced search and filtering
* Application analytics and statistics
* Refresh token support
* CI/CD pipeline
* Integration tests with Testcontainers
* Deployment to a cloud platform

## 👩‍💻 Author

**Farzaneh Nasrabadi**

Aspiring Backend Developer focused on **Java, Spring Boot, and backend development**.

[GitHub](https://github.com/Farzaneh-Nasrabadii)

---

⭐ If you find this project useful, feel free to explore the code and follow the repository.

```