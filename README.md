# Java REST API Test Suite with Apache HttpClient and JUnit

This repository contains a comprehensive set of API tests implemented in Java using **Apache HttpClient 5** and **JUnit 5**. It targets an example RESTful API with endpoints for health checking, user login, registration, and user data retrieval.

## Features

- Covers both **positive and negative scenarios** for:
  - `/health` (GET)
  - `/login` (POST)
  - `/register` (POST)
  - `/user/{user_id}` (GET)
- Utilizes `JUnit 5` lifecycle annotations for test setup and teardown.
- Logs status codes and verifies expected outcomes with assertions.

## Tested Endpoints

| Endpoint            | Method | Description                  |
|---------------------|--------|------------------------------|
| `/health`           | GET    | Health check                 |
| `/login`            | POST   | Login with credentials       |
| `/register`         | POST   | Register a new user          |
| `/user/{user_id}`   | GET    | Retrieve user information    |

## 🔧 Tools & Libraries

- **Java 11+**
- **JUnit 5**
- **Apache HttpClient 5**
- Built-in Java `Logger`

## How to Run Tests

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/java-api-tests.git
   cd java-api-tests
