# Mini-TutorBot-API

A Spring Boot REST API for an intelligent tutoring system that manages students and exercises with automated feedback and grading.

## Overview

Mini-TutorBot-API is a backend service that provides endpoints for:
- **Student Management**: Register, retrieve, and manage students
- **Exercise Repository**: Browse and filter exercises by difficulty
- **Answer Submission**: Submit answers to exercises and receive intelligent feedback with scoring

## Features

✨ **Core Features:**
- Student registration and retrieval
- Exercise library with difficulty levels
- Automated answer evaluation with feedback
- In-memory data persistence
- RESTful API design with JSON responses
- Spring Boot with embedded Tomcat server

## Technology Stack

- **Framework**: Spring Boot 3.2.5
- **Language**: Java 17
- **Build Tool**: Maven
- **Server**: Apache Tomcat (embedded)
- **Architecture**: MVC with Service/Repository layers

## Project Structure

```
src/main/java/com/tutorbot/
├── TutorbotApplication.java          # Spring Boot entry point
├── controller/
│   ├── StudentController.java         # Student endpoints
│   └── ExerciseController.java        # Exercise endpoints
├── service/
│   ├── StudentService.java            # Student business logic
│   └── ExerciseService.java           # Exercise evaluation logic
├── repository/
│   ├── StudentRepository.java         # Student data access
│   └── ExerciseRepository.java        # Exercise data access
└── model/
    ├── Student.java                   # Student entity
    ├── Exercise.java                  # Exercise entity
    └── Feedback.java                  # Feedback response
```

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Installation

1. **Clone the repository:**
```bash
git clone https://github.com/mdzpaulina/Mini-TutorBot-API.git
cd Mini-TutorBot-API
```

2. **Build the project:**
```bash
mvn clean install
```

3. **Run the application:**
```bash
mvn spring-boot:run
```

The application will start on **http://localhost:8080**

## API Endpoints

### Student Management

#### 1. Get All Students
```http
GET /api/students
```
**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Ana Torres",
    "email": "ana@university.com",
    "level": "beginner"
  },
  {
    "id": 2,
    "name": "Luis Mendoza",
    "email": "luis@university.com",
    "level": "intermediate"
  }
]
```

#### 2. Get Student by ID
```http
GET /api/students/{id}
```
**Example:**
```http
GET /api/students/1
```
**Response (200 OK):**
```json
{
  "id": 1,
  "name": "Ana Torres",
  "email": "ana@university.com",
  "level": "beginner"
}
```

#### 3. Register New Student
```http
POST /api/students
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "level": "intermediate"
}
```
**Response (201 Created):**
```json
{
  "id": 4,
  "name": "John Doe",
  "email": "john@example.com",
  "level": "intermediate"
}
```

### Exercise Management

#### 4. Get All Exercises
```http
GET /api/exercises
```
**Response (200 OK):**
```json
[
  {
    "id": 101,
    "topic": "Spring Boot",
    "question": "What annotation marks a REST controller?",
    "difficulty": "easy"
  },
  {
    "id": 102,
    "topic": "Spring Boot",
    "question": "Which annotation enables component scanning?",
    "difficulty": "easy"
  }
]
```

#### 5. Get Exercises by Difficulty
```http
GET /api/exercises?difficulty=easy
```
**Query Parameters:**
- `difficulty` (optional): Filter by difficulty level (easy, medium, hard)

**Response (200 OK):** Same as above, filtered results

#### 6. Submit Exercise Answer
```http
POST /api/exercises/submit
Content-Type: application/json

{
  "studentId": 2,
  "exerciseId": 102,
  "answer": "@SpringBootApplication"
}
```
**Response (200 OK):**
```json
{
  "studentId": 2,
  "exerciseId": 102,
  "answer": "@SpringBootApplication",
  "score": 100,
  "message": "Excellent! That is correct.",
  "correct": true
}
```

## Pre-loaded Data

### Students
| ID | Name | Email | Level |
|---|---|---|---|
| 1 | Ana Torres | ana@university.com | beginner |
| 2 | Luis Mendoza | luis@university.com | intermediate |
| 3 | Sofia Ramirez | sofia@university.com | advanced |

### Exercises
| ID | Topic | Question | Difficulty |
|---|---|---|---|
| 101 | Spring Boot | What annotation marks a REST controller? | easy |
| 102 | Spring Boot | Which annotation enables component scanning? | easy |
| 103 | Spring Core | What annotation is used for constructor injection? | medium |
| 104 | Spring Data | Which annotation marks a repository bean? | medium |

## Testing

### Using cURL

**Get all students:**
```bash
curl http://localhost:8080/api/students
```

**Register a student:**
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Test Student","email":"test@test.com","level":"intermediate"}'
```

**Submit an answer:**
```bash
curl -X POST http://localhost:8080/api/exercises/submit \
  -H "Content-Type: application/json" \
  -d '{"studentId":1,"exerciseId":101,"answer":"@RestController"}'
```

### Using Postman

1. Import the endpoints from the API documentation above
2. Test each endpoint with the provided request/response examples
3. Verify that all responses return proper JSON

## Architecture

### Design Patterns

**MVC (Model-View-Controller):**
- **Models**: Student, Exercise, Feedback entities
- **Controllers**: REST endpoints handling HTTP requests
- **Services**: Business logic layer with data processing
- **Repositories**: Data access layer with in-memory storage

**Dependency Injection:**
- Constructor-based injection for all service dependencies
- Follows Spring Framework best practices

### Data Flow

```
HTTP Request
    ↓
Controller (handles routing)
    ↓
Service (business logic)
    ↓
Repository (data access)
    ↓
Response (JSON)
```

## Build & Deployment

### Build the JAR
```bash
mvn clean package
```
JAR file location: `target/tutorbot-api-0.0.1-SNAPSHOT.jar`

### Run the JAR
```bash
java -jar target/tutorbot-api-0.0.1-SNAPSHOT.jar
```

## Development

### Build only (no tests)
```bash
mvn clean compile
```

### Run tests (if added)
```bash
mvn test
```

### Clean build artifacts
```bash
mvn clean
```

## Configuration

Application runs with default settings:
- **Port**: 8080
- **Context Path**: /
- **Server**: Apache Tomcat

To modify settings, edit `src/main/resources/application.properties`



## Authors

Team 32 
