# 🚀 Student API with JWT Authentication

A Spring Boot REST API for managing students and departments, secured using JWT-based authentication. This project demonstrates backend development concepts including authentication, authorization, database integration, and RESTful API design.

---

## 📌 Features

* 🔐 JWT Authentication (Login & Register)
* 👨‍🎓 Student Management (CRUD)
* 🏫 Department Management
* 🔗 Relationship mapping (Students ↔ Departments)
* 🛡️ Secured endpoints using Spring Security
* 🐘 PostgreSQL integration (Dockerized)
* ⚡ Stateless session management

---

## 🛠️ Tech Stack

* Java 17+
* Spring Boot
* Spring Security
* JWT (io.jsonwebtoken)
* PostgreSQL
* Docker
* Maven

---

## 📂 Project Structure

```
src/
 ├── auth/            # Authentication logic (login/register)
 ├── config/          # Security + JWT configuration
 ├── controller/      # REST controllers
 ├── service/         # Business logic
 ├── repository/      # JPA repositories
 ├── entity/          # Database entities
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repo

```bash
git clone https://github.com/nikhilng99/student-api.git
cd student-api
```

---

### 2️⃣ Run PostgreSQL using Docker

```bash
docker-compose up -d
```

---

### 3️⃣ Configure application

Make sure your DB config matches:

```properties
spring.datasource.url=jdbc:postgresql://localhost:4444/student
spring.datasource.username=studentcode
spring.datasource.password=password
```

---

### 4️⃣ Run the app

```bash
mvn spring-boot:run
```

---

## 🔐 Authentication Flow

### Register

```
POST /api/v1/auth/register
```

### Login

```
POST /api/v1/auth/authenticate
```

Response:

```json
{
  "token": "your-jwt-token"
}
```

---

## 🔑 Using Secured APIs

Add this header to all protected endpoints:

```
Authorization: Bearer <your-token>
```

---

## 📮 API Endpoints

### Department

* `POST /api/v1/department`
* `GET /api/v1/department/{id}`

### Student

* `POST /api/v1/student`
* `GET /api/v1/student/dept/{id}/min-age/{age}`

---

## 🧪 Example Requests

### Create Department

```json
{
  "name": "Computer Science"
}
```

### Create Student

```json
{
  "name": "Alice",
  "email": "alice@test.com",
  "age": 20,
  "grade": "A",
  "deptId": 1
}
```

---

## 🛡️ Security

* Stateless authentication using JWT
* Passwords encrypted using BCrypt
* Custom filter for token validation
* Role-based foundation ready (can be extended)

---

## 👨‍💻 Author

Nikhil N G

---
