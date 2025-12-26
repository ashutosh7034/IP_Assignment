# Day 4 Project - Advanced Spring Boot with JPA & JWT Authentication

## Overview
Day 4 focuses on enterprise-level Spring Boot development with database persistence, JWT authentication, and professional full-stack architecture. This project demonstrates advanced concepts including Spring Data JPA, Spring Security, REST API design, and modern frontend development with authentication.

## 📚 Assignment Links
- **Notes & Documentation**: [Day 4 Notes](https://shaileshsonareg.github.io/tcet/day4/)
- **MCQ Test**: [Day 4 Test](https://cquiz.ciacloud.in/take_test.php?test_id=MTE4)
- **Reference Code**: [GitHub - TCET Day 4](https://github.com/shaileshsonareg/tcet/tree/main/day4)
- **Project Reference**: [Spring Boot Demo](https://github.com/ciaindia/spring-boot-demo.git)
- **PRD Sample**: [Product Requirements Document](https://shaileshsonareg.github.io/tcet/day4/prd.html)

---

## 🚀 How to Run - Quick Start Guide

### Step 1: Start the Backend (Spring Boot with JWT & JPA)

```bash
# Navigate to backend folder
cd "Day 4 Project/backend"

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

**Alternative (if Maven wrapper exists):**
```bash
# Windows
.\mvnw clean install
.\mvnw spring-boot:run

# Mac/Linux
./mvnw clean install
./mvnw spring-boot:run
```

✅ **Backend is ready when you see:**
```
Started EmployeeManagementApplication in X.XXX seconds
Tomcat started on port(s): 8080 (http)
```

🌐 **Backend URLs:**
- API: http://localhost:8080/api
- H2 Console: http://localhost:8080/h2-console

### Step 2: Open the Frontend

```bash
# Simply open the HTML file in your browser
Open "Day 4 Project/frontend/index.html"
```

**OR** double-click `index.html` in File Explorer

### Step 3: Login and Test

**Default Credentials:**
- Username: `user`
- Password: `user123`

**OR Admin Account:**
- Username: `admin`
- Password: `admin123`

**Test Flow:**
1. Login with credentials above
2. View employee dashboard with statistics
3. Add new employees using the form
4. Edit existing employees
5. Delete employees (with confirmation)
6. Search employees by name/email
7. Logout and login again

### Prerequisites

✅ **Required Software:**
- **Java 17 or higher** - [Download JDK](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+** - [Download Maven](https://maven.apache.org/download.cgi)
- **Modern Web Browser** (Chrome, Firefox, Safari, Edge)

✅ **Verify Installation:**
```bash
java -version    # Should show: java version "17" or higher
mvn -version     # Should show: Apache Maven 3.6.x or higher
```

### Quick API Test (Optional)

**Login to get JWT token:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"user123"}'
```

**Use token to get employees:**
```bash
curl -H "Authorization: Bearer <YOUR_TOKEN>" \
  http://localhost:8080/api/employees
```

### Troubleshooting

**Problem: Port 8080 already in use**
```properties
# Edit: backend/src/main/resources/application.properties
server.port=8081
```

**Problem: Maven not found**
- Download Maven from https://maven.apache.org/download.cgi
- Extract and add to PATH environment variable
- Restart terminal/command prompt

**Problem: Cannot login**
- Ensure backend is running (check console for errors)
- Check browser console (F12) for error messages
- Verify credentials: user/user123 or admin/admin123

**Problem: Database errors**
- H2 database auto-initializes on startup
- Check application.properties for correct configuration
- Delete any existing database files and restart

**Problem: CORS errors**
- Ensure @CrossOrigin is enabled in controllers
- Check browser console for specific CORS error
- Verify frontend is accessing correct backend URL

For detailed instructions, see [QUICKSTART.md](QUICKSTART.md)

---

## 📋 Project: Advanced Employee Management System

### Objective
Build a complete enterprise-grade employee management system with JWT authentication, database persistence, and modern security features.

### Key Features
- 🔐 **JWT Authentication** - Secure login and token-based access control
- 👥 **User Management** - User registration, login, and role-based authorization
- 💼 **Employee CRUD** - Complete Create, Read, Update, Delete operations with JPA
- 💾 **Database Integration** - H2 in-memory database (configurable for MySQL)
- 🔍 **Search & Filter** - Search employees by name, email, or department
- 📊 **Statistics** - Calculate salary averages and departmental totals
- 🎨 **Modern UI** - Responsive frontend with authentication flow
- 🛡️ **Security** - BCrypt password hashing, CORS configuration

---

## 🏗️ Technology Stack

### Backend
- **Spring Boot 3.2.0** - Application framework
- **Java 17** - Programming language
- **Spring Data JPA** - Database operations and ORM
- **Spring Security** - Authentication and authorization
- **JWT (jjwt 0.12.3)** - JSON Web Token for stateless auth
- **H2 Database** - In-memory database (development)
- **MySQL** - Production database (optional)
- **Hibernate** - JPA implementation
- **Lombok** - Reduce boilerplate code
- **Maven** - Build and dependency management

### Frontend
- **HTML5** - Structure and semantics
- **CSS3** - Responsive design with gradients
- **JavaScript ES6** - Modern async/await patterns
- **Axios** - HTTP client for API calls
- **LocalStorage** - JWT token persistence

---

## 📊 Data Models

### User Entity (Authentication)
```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;     // Unique username
    private String email;        // Unique email
    private String password;     // BCrypt hashed password
    private String fullName;     // Display name
    private Boolean isActive;    // Account status
    private Set<String> roles;   // User roles (ROLE_USER, ROLE_ADMIN)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```

### Employee Entity (Business Data)
```java
@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;         // Employee name
    private String department;   // Department (IT, HR, Finance, etc.)
    private Double salary;       // Annual salary
    private String email;        // Contact email (unique)
    private String phone;        // Phone number
    private String position;     // Job title
    private String status;       // ACTIVE, INACTIVE
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```

---

## 🔌 API Endpoints

### Authentication Endpoints

#### POST /api/auth/register
Register a new user account
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "email": "john@example.com",
    "password": "password123",
    "fullName": "John Doe"
  }'
```

**Response**:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "johndoe",
  "email": "john@example.com",
  "message": "Registration successful"
}
```

#### POST /api/auth/login
Login and receive JWT token
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "user",
    "password": "user123"
  }'
```

**Response**:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "user",
  "email": "user@example.com",
  "message": "Login successful"
}
```

#### GET /api/auth/validate
Validate JWT token
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/auth/validate
```

### Employee Endpoints (Protected - Requires JWT)

#### GET /api/employees
Get all employees
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/employees
```

#### GET /api/employees/{id}
Get employee by ID
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/employees/1
```

#### POST /api/employees
Create new employee
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Green",
    "department": "IT",
    "salary": 55000,
    "email": "alice@example.com",
    "phone": "9999999999",
    "position": "Developer"
  }'
```

#### PUT /api/employees/{id}
Update employee
```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Green",
    "department": "IT",
    "salary": 60000,
    "email": "alice.updated@example.com",
    "phone": "9999999999",
    "position": "Senior Developer"
  }'
```

#### DELETE /api/employees/{id}
Delete employee
```bash
curl -X DELETE http://localhost:8080/api/employees/1 \
  -H "Authorization: Bearer <TOKEN>"
```

#### GET /api/employees/department/{department}
Get employees by department
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/employees/department/IT
```

#### GET /api/employees/search/{searchTerm}
Search employees
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/employees/search/Alice
```

#### GET /api/employees/stats/count
Get total employee count
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/employees/stats/count
```

#### GET /api/employees/stats/salary
Get average salary
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/employees/stats/salary
```

---

## 📂 Project Structure

```
Day 4 Project/
├── backend/
│   ├── src/main/java/com/employee/
│   │   ├── entity/
│   │   │   ├── EmployeeEntity.java      (JPA Entity with annotations)
│   │   │   └── User.java                (User entity with roles)
│   │   ├── repository/
│   │   │   ├── EmployeeRepository.java  (JpaRepository interface)
│   │   │   └── UserRepository.java      (User data access)
│   │   ├── service/
│   │   │   ├── EmployeeService.java     (Business logic)
│   │   │   └── AuthService.java         (Authentication logic)
│   │   ├── controller/
│   │   │   ├── EmployeeController.java  (REST endpoints)
│   │   │   └── AuthController.java      (Login/Register)
│   │   ├── dto/
│   │   │   ├── EmployeeDTO.java         (Data transfer object)
│   │   │   ├── LoginRequest.java        (Login request model)
│   │   │   ├── LoginResponse.java       (Login response model)
│   │   │   └── RegisterRequest.java     (Registration model)
│   │   ├── util/
│   │   │   └── JwtUtil.java             (JWT token operations)
│   │   ├── config/
│   │   │   ├── SecurityConfig.java      (Security & CORS config)
│   │   │   └── DataInitializer.java     (Sample data loader)
│   │   └── EmployeeManagementApplication.java
│   ├── src/main/resources/
│   │   └── application.properties       (App configuration)
│   └── pom.xml                          (Maven dependencies)
├── frontend/
│   └── index.html                       (Login + Dashboard UI)
├── QUICKSTART.md
└── README.md
```

---

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Web browser (Chrome, Firefox, Safari)

### Backend Setup

1. **Navigate to backend directory**
```bash
cd "Day 4 Project/backend"
```

2. **Build the project**
```bash
mvn clean install
```

3. **Run the application**
```bash
mvn spring-boot:run
```

Application starts on **http://localhost:8080**

### Frontend Setup

1. **Navigate to frontend directory**
```bash
cd "Day 4 Project/frontend"
```

2. **Open in browser**
```bash
Open index.html in web browser
```

### Default Login Credentials

**Admin Account**:
- Username: `admin`
- Password: `admin123`
- Email: `admin@example.com`

**Regular User Account**:
- Username: `user`
- Password: `user123`
- Email: `user@example.com`

---

## 💾 Database Configuration

### H2 In-Memory Database (Default)
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

**Access H2 Console**: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

### MySQL Database (Production)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

---

## 🔐 Security Features

### JWT Configuration
```properties
jwt.secret=your_super_secret_key_that_is_at_least_32_characters_long
jwt.expiration=86400000  # 24 hours
```

### Security Features Implemented
- ✅ **BCrypt Password Hashing** - Passwords are never stored in plain text
- ✅ **JWT Tokens** - Stateless authentication using signed tokens
- ✅ **Token Expiration** - Tokens expire after 24 hours
- ✅ **Role-Based Access** - ROLE_USER and ROLE_ADMIN support
- ✅ **CORS Configuration** - Secure cross-origin requests
- ✅ **Input Validation** - Prevent duplicate usernames/emails

---

## 🎨 Frontend Features

### Login/Registration Page
- Professional gradient design
- Toggle between login and registration forms
- Form validation
- Error message display
- JWT token storage in localStorage

### Employee Dashboard
- **Header**: User info and logout button
- **Statistics Cards**: Total employees, average salary
- **Add Employee Form**: Multi-field responsive form
- **Employee Table**: Sortable, with actions (edit/delete)
- **Search & Filter**: Real-time employee search
- **Responsive Design**: Works on mobile and desktop
- **Modal Dialogs**: Confirmation prompts for destructive actions

### UI Components
- Gradient backgrounds and buttons
- Card-based layout
- Professional color scheme
- Smooth animations and transitions
- Loading states and error messages
- Accessible form labels and inputs

---

## 🧪 Testing the Application

### Testing Workflow

1. **Start Backend**: `mvn spring-boot:run`
2. **Open Frontend**: `frontend/index.html` in browser
3. **Login**: Use default credentials (user/user123)
4. **Test CRUD**: Add, edit, delete employees
5. **Test Search**: Search employees by name or email
6. **View Stats**: Check statistics cards
7. **Logout**: Test logout and re-login

### Test Checklist
- [ ] User registration works
- [ ] User login returns JWT token
- [ ] Token is stored in localStorage
- [ ] Dashboard loads employee list
- [ ] Statistics display correctly
- [ ] Add employee creates new record
- [ ] Edit employee updates existing record
- [ ] Delete employee removes record
- [ ] Search filters employee list
- [ ] Logout clears token and redirects to login
- [ ] Invalid login shows error message
- [ ] Expired token redirects to login

---

## 🔧 Key Components Explained

### 1. JPA Entities
- `@Entity` - Marks class as database entity
- `@Table(name = "...")` - Maps to database table
- `@Id` - Primary key field
- `@GeneratedValue` - Auto-increment ID
- `@Column` - Column specifications (nullable, unique)

### 2. Repository Layer
- Extends `JpaRepository<Entity, ID>`
- Provides built-in CRUD methods
- Custom query methods with `@Query`
- Native SQL support

### 3. Service Layer
- Business logic and validation
- Transaction management with `@Transactional`
- DTO conversion (Entity ↔ DTO)
- Error handling

### 4. Controller Layer
- REST API endpoints
- `@RestController` and `@RequestMapping`
- HTTP method annotations (@GetMapping, @PostMapping, etc.)
- Request/Response handling
- CORS configuration with `@CrossOrigin`

### 5. JWT Utility
- Token generation with user claims
- Token validation and expiration checking
- Username extraction from token
- HMAC SHA256 signing

### 6. Security Configuration
- Password encoder (BCrypt)
- CORS configuration source
- Security filter chain (if using Spring Security filters)

---

## 🎯 Learning Outcomes

Upon completion, students will understand:

### Backend Development
1. **Spring Data JPA**
   - Entity relationships and annotations
   - Repository pattern and custom queries
   - Database migrations with Hibernate

2. **Spring Security & JWT**
   - Authentication vs Authorization
   - JWT token lifecycle
   - Password hashing with BCrypt
   - Role-based access control

3. **REST API Design**
   - Resource naming conventions
   - HTTP status codes
   - Request/Response DTOs
   - Error handling strategies

4. **Service Layer Pattern**
   - Business logic separation
   - Transaction management
   - DTO mapping patterns

### Frontend Development
1. **Modern JavaScript**
   - Async/await patterns
   - Promise handling
   - LocalStorage API
   - Event delegation

2. **Authentication Flow**
   - Token-based authentication
   - Token storage and retrieval
   - Protected route handling
   - Session management

3. **HTTP Communication**
   - Axios HTTP client
   - Request interceptors
   - Bearer token authentication
   - Error response handling

---

## ❓ Common Issues & Solutions

### Issue: Port 8080 already in use
**Solution**: Change port in `application.properties`
```properties
server.port=8081
```

### Issue: Cannot login - Invalid credentials
**Solution**: Verify default users are initialized. Check console logs for `DataInitializer` output.

### Issue: CORS errors in browser
**Solution**: Ensure `@CrossOrigin(origins = "*")` is on controllers. Check browser console for specific CORS error.

### Issue: Token expired
**Solution**: JWT tokens expire after 24 hours. Simply login again to get a new token.

### Issue: Database not initialized
**Solution**: Check `spring.jpa.hibernate.ddl-auto=create-drop` in application.properties. Ensure DataInitializer is executed.

### Issue: H2 console not accessible
**Solution**: Verify `spring.h2.console.enabled=true` in application.properties

### Issue: Employee CRUD not working
**Solution**: Ensure you're logged in and token is in Authorization header. Check browser console and backend logs.

---

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/projects/spring-data-jpa)
- [Spring Security Reference](https://spring.io/projects/spring-security)
- [JWT Introduction](https://jwt.io/introduction)
- [Hibernate ORM Documentation](https://hibernate.org/orm/documentation/)
- [Axios Documentation](https://axios-http.com/)

---

## 📊 Sample Data

### Sample Users (Auto-initialized)
- **admin** (password: admin123) - ROLE_ADMIN
- **user** (password: user123) - ROLE_USER

### Sample Employees (Auto-initialized)
- John Doe - IT, $50,000 (john@example.com)
- Jane Smith - HR, $55,000 (jane@example.com)
- Mike Johnson - Finance, $60,000 (mike@example.com)
- Alice Brown - IT, $52,000 (alice@example.com)
- Bob Wilson - Sales, $48,000 (bob@example.com)

---

## 🎁 Bonus Features Implemented

- ✅ JWT-based authentication
- ✅ User registration and login
- ✅ Role-based authorization (ADMIN/USER)
- ✅ Password encryption with BCrypt
- ✅ Database persistence with JPA
- ✅ Search and filter functionality
- ✅ Statistics calculation (count, average salary)
- ✅ Responsive modern UI
- ✅ Token expiration handling
- ✅ CORS configuration
- ✅ Error handling and validation
- ✅ Sample data initialization

---

## ✅ Completion Checklist

### Backend
- [ ] Spring Boot application runs successfully
- [ ] H2/MySQL database configured
- [ ] JPA entities created with proper annotations
- [ ] Repository interfaces extend JpaRepository
- [ ] Service layer implements business logic
- [ ] Controllers handle all REST endpoints
- [ ] JWT authentication working
- [ ] User registration and login functional
- [ ] Sample data auto-initialized
- [ ] CORS enabled for frontend

### Frontend
- [ ] Login page styled and functional
- [ ] Registration form working
- [ ] JWT token stored in localStorage
- [ ] Dashboard displays employee list
- [ ] Statistics cards show correct data
- [ ] Add employee form submits successfully
- [ ] Edit employee updates record
- [ ] Delete employee removes record
- [ ] Search filters employee list
- [ ] Logout clears session
- [ ] Responsive on mobile devices

### Testing
- [ ] All API endpoints tested with cURL/Postman
- [ ] Frontend loads without console errors
- [ ] All CRUD operations verified
- [ ] Authentication flow tested
- [ ] Error handling validated
- [ ] Database queries verified in H2 console

---

## 📞 Support

For questions or issues:
1. Review the assignment links and documentation
2. Check Spring Boot and JWT documentation
3. Inspect browser console and network tab
4. Review backend console logs
5. Test endpoints with Postman/cURL

**Support Contact**: 9172007007

---

**Created**: December 2025  
**Level**: Advanced  
**Duration**: 1 week  
**Instructor**: TCET MCA  
**Technologies**: Spring Boot, JPA, JWT, MySQL/H2, JavaScript, Axios
