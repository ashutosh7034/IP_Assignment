# Day 3 Project - Quick Start Guide

## Prerequisites
- Java 17 or higher installed
- Maven 3.6 or higher installed
- Git (optional)

## Installation & Setup

### Step 1: Navigate to Project Directory
```bash
cd "Day3_Project"
```

### Step 2: Build the Project
```bash
mvn clean install
```

This command will:
- Download all dependencies (may take 2-3 minutes on first run)
- Compile the Java source code
- Run tests (if any)
- Package the application

### Step 3: Run the Application
```bash
mvn spring-boot:run
```

Or you can run the JAR file directly:
```bash
java -jar target/day3-employee-management-1.0.0.jar
```

### Step 4: Access the Application
- **REST API**: http://localhost:8080/api/employees
- **Frontend UI**: Open `frontend/index.html` in a web browser

## Testing the API

### Using cURL

**Get all employees:**
```bash
curl http://localhost:8080/api/employees
```

**Get specific employee:**
```bash
curl http://localhost:8080/api/employees/1
```

**Add new employee:**
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice Brown","department":"IT","salary":52000}'
```

**Update employee:**
```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe Updated","department":"IT","salary":52000}'
```

**Delete employee:**
```bash
curl -X DELETE http://localhost:8080/api/employees/1
```

### Using Frontend UI
1. Open `frontend/index.html` in your browser
2. The UI will fetch employee list automatically
3. Use the form to add new employees
4. Click update/delete buttons to modify records

## Troubleshooting

### Port Already in Use
If port 8080 is already in use, change it in `src/main/resources/application.properties`:
```properties
server.port=8081
```

### Build Errors
- Ensure Java 17+ is installed: `java -version`
- Ensure Maven 3.6+ is installed: `mvn -version`
- Clear Maven cache: `mvn clean`

### Cannot Access Endpoints
- Ensure the Spring Boot application is running
- Check that there are no error messages in console
- Verify the port in application.properties matches your access URL

## Project Files

- `src/main/java/com/employee/Employee.java` - Employee model class
- `src/main/java/com/employee/EmployeeController.java` - REST API controller
- `src/main/java/com/employee/EmployeeService.java` - Business logic service
- `src/main/java/com/employee/EmployeeManagementApp.java` - Spring Boot main application
- `src/main/resources/application.properties` - Application configuration
- `frontend/index.html` - Web UI for CRUD operations
- `pom.xml` - Maven dependencies and build configuration

## Key Features
✅ RESTful API with CRUD operations
✅ In-memory employee storage
✅ CORS enabled for frontend integration
✅ Simple web-based frontend UI
✅ Sample data pre-loaded on startup
✅ Error handling and validation

## Development
To rebuild after making changes:
```bash
mvn clean package
mvn spring-boot:run
```

## Learning Outcomes
By completing this project, you will understand:
- Spring Boot REST API development
- Creating controllers and services in Spring
- HTTP methods (GET, POST, PUT, DELETE)
- JSON request/response handling
- CORS configuration
- Frontend-Backend API integration

## Support
For questions or issues, refer to the main README.md file or check the Spring Boot documentation at https://spring.io/projects/spring-boot
