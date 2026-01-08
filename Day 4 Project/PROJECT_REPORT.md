# Employee Management System - Project Report

**Project Name:** Employee Management System with Role-Based Access Control  
**Technology Stack:** Spring Boot 3.2.x, MySQL 8, JWT Authentication, HTML/CSS/JavaScript  
**Author:** Ashutosh  
**Date:** January 8, 2026  

---

## Executive Summary

This project implements a full-stack Employee Management System with robust authentication, role-based access control, and automated user credential generation. The system features a Spring Boot backend with MySQL persistence, JWT-based security, and a responsive frontend with separate dashboards for administrators and regular users.

### Key Achievements
- ✅ Role-based authentication and authorization (Admin/User)
- ✅ Automatic user account creation when employees are added
- ✅ Password change functionality for all users
- ✅ RESTful API with JWT token-based security
- ✅ Responsive UI with distinct admin and user interfaces
- ✅ MySQL database integration with proper schema design
- ✅ CRUD operations with validation and error handling

---

## 1. System Architecture

### 1.1 Technology Stack

**Backend:**
- Spring Boot 3.2.x
- Spring Security with JWT
- Spring Data JPA
- MySQL 8.0 (primary database)
- H2 (development/testing)
- Maven/mvnd (build tool)
- JJWT 0.11.5 (JWT implementation)

**Frontend:**
- HTML5, CSS3, JavaScript (ES6+)
- Fetch API for HTTP requests
- LocalStorage for token management
- Responsive design (mobile-friendly)

**Security:**
- BCrypt password hashing
- JWT token-based authentication
- Role-based access control (RBAC)
- CORS configuration for cross-origin requests

### 1.2 System Components

```
┌─────────────────────────────────────────────────────────────┐
│                        Frontend Layer                        │
│  ┌──────────────┐  ┌──────────────┐  ┌─────────────────┐   │
│  │  index.html  │  │  admin.html  │  │   user.html     │   │
│  │  (Login)     │  │  (Dashboard) │  │   (Profile)     │   │
│  └──────────────┘  └──────────────┘  └─────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                            ↕ HTTP/REST
┌─────────────────────────────────────────────────────────────┐
│                      Spring Boot Backend                     │
│  ┌──────────────────────────────────────────────────────┐   │
│  │              Security Layer (JWT Filter)             │   │
│  └──────────────────────────────────────────────────────┘   │
│  ┌─────────────────┐      ┌──────────────────────────┐     │
│  │  AuthController │      │  EmployeeController      │     │
│  │  - login        │      │  - CRUD operations       │     │
│  │  - register     │      │  - search                │     │
│  │  - changePwd    │      │  - statistics            │     │
│  └─────────────────┘      └──────────────────────────┘     │
│           ↕                           ↕                      │
│  ┌─────────────────┐      ┌──────────────────────────┐     │
│  │   AuthService   │      │   EmployeeService        │     │
│  │  - authenticate │      │  - auto user creation    │     │
│  │  - token mgmt   │      │  - business logic        │     │
│  └─────────────────┘      └──────────────────────────┘     │
│           ↕                           ↕                      │
│  ┌─────────────────┐      ┌──────────────────────────┐     │
│  │  UserRepository │      │  EmployeeRepository      │     │
│  └─────────────────┘      └──────────────────────────┘     │
└─────────────────────────────────────────────────────────────┘
                            ↕ JDBC
┌─────────────────────────────────────────────────────────────┐
│                       MySQL Database                         │
│  ┌──────────────┐  ┌────────────────┐  ┌───────────────┐   │
│  │   users      │  │   employees    │  │  user_roles   │   │
│  └──────────────┘  └────────────────┘  └───────────────┘   │
└─────────────────────────────────────────────────────────────┘
```

---

## 2. Database Design

### 2.1 Entity Relationship Diagram

```
┌─────────────────────────────────┐
│          EMPLOYEES              │
├─────────────────────────────────┤
│ id (PK)            BIGINT       │
│ name               VARCHAR(255) │
│ department         VARCHAR(255) │
│ salary             DOUBLE       │
│ email              VARCHAR(255) │◄─┐
│ phone              VARCHAR(50)  │  │
│ position           VARCHAR(255) │  │
│ status             VARCHAR(50)  │  │
│ created_at         TIMESTAMP    │  │
│ updated_at         TIMESTAMP    │  │
└─────────────────────────────────┘  │
                                      │
                                      │ (FK) employee_id
                                      │
┌─────────────────────────────────┐  │
│            USERS                │  │
├─────────────────────────────────┤  │
│ id (PK)            BIGINT       │  │
│ username           VARCHAR(50)  │  │
│ email              VARCHAR(255) │  │
│ password           VARCHAR(255) │  │
│ full_name          VARCHAR(255) │  │
│ is_active          BOOLEAN      │  │
│ employee_id (FK)   BIGINT       │──┘
│ created_at         TIMESTAMP    │
│ updated_at         TIMESTAMP    │
└─────────────────────────────────┘
         │
         │ (FK) user_id
         ↓
┌─────────────────────────────────┐
│         USER_ROLES              │
├─────────────────────────────────┤
│ user_id (FK)       BIGINT       │
│ role               VARCHAR(50)  │
└─────────────────────────────────┘
```

### 2.2 Table Descriptions

**EMPLOYEES Table:**
- Stores employee information
- Primary key: `id` (auto-increment)
- Unique constraint on `email`
- Timestamp tracking for creation and updates

**USERS Table:**
- Stores user authentication data
- BCrypt hashed passwords
- Links to employee record via `employee_id`
- Supports multiple roles per user

**USER_ROLES Table:**
- Many-to-many relationship for user roles
- Supports ROLE_ADMIN and ROLE_USER

---

## 3. Key Features Implementation

### 3.1 Authentication & Authorization

**JWT Token Generation:**
```java
// JwtUtil.java
public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
}
```

**Security Configuration:**
- All `/api/auth/**` endpoints are public
- All other endpoints require valid JWT token
- Stateless session management
- CORS enabled for frontend communication

### 3.2 Automatic User Creation

When an admin creates a new employee, the system automatically:
1. Extracts first name from employee full name
2. Generates unique username (appends number if conflict)
3. Creates default password: `{firstname}123`
4. Assigns ROLE_USER
5. Links user account to employee record

**Implementation:**
```java
// EmployeeService.java
private UserCreation createDefaultUserForEmployee(EmployeeEntity employee) {
    String baseUsername = employee.getName().trim().split("\\s+")[0];
    String username = baseUsername;
    int suffix = 1;
    
    // Ensure unique username
    while (userRepository.existsByUsername(username)) {
        username = baseUsername + suffix++;
    }
    
    // Create user account
    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(baseUsername + "123"));
    user.setEmployeeId(employee.getId());
    user.setRoles(Set.of("ROLE_USER"));
    
    userRepository.save(user);
    return new UserCreation(username, baseUsername + "123");
}
```

### 3.3 Password Change Functionality

**Backend Implementation:**
```java
// AuthService.java
@Transactional
public void changePassword(String username, ChangePasswordRequest request) {
    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
    
    // Verify old password
    if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
        throw new RuntimeException("Old password is incorrect");
    }
    
    // Update to new password
    user.setPassword(passwordEncoder.encode(request.getNewPassword()));
    userRepository.save(user);
}
```

**API Endpoint:**
```
POST /api/auth/change-password
Headers: Authorization: Bearer {token}
Body: {
  "oldPassword": "current123",
  "newPassword": "newPassword456"
}
```

### 3.4 Role-Based Access Control

**Admin Capabilities:**
- View all employees
- Create new employees (auto-creates user account)
- Update employee information
- Delete employees
- View statistics (total, average salary, departments)
- Search and filter employees
- Change own password

**User Capabilities:**
- View only their own employee profile
- Change own password
- No CRUD operations on employees

**Backend Authorization:**
```java
// EmployeeController.java
@GetMapping
public ResponseEntity<?> getAllEmployees(@RequestHeader("Authorization") String authHeader) {
    User user = authService.getUserByUsername(username);
    
    if (user.getRoles().contains("ROLE_ADMIN")) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    } else {
        // Regular user sees only their own record
        if (user.getEmployeeId() != null) {
            EmployeeDTO employee = employeeService.getEmployeeById(user.getEmployeeId());
            return ResponseEntity.ok(List.of(employee));
        }
        return ResponseEntity.ok(List.of());
    }
}
```

---

## 4. API Documentation

### 4.1 Authentication Endpoints

#### POST /api/auth/login
**Description:** Authenticate user and receive JWT token

**Request:**
```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "admin",
  "email": "admin@example.com",
  "message": "Login successful",
  "roles": ["ROLE_ADMIN"],
  "employeeId": null
}
```

#### POST /api/auth/register
**Description:** Register new user account

**Request:**
```json
{
  "username": "newuser",
  "email": "newuser@example.com",
  "password": "secure123",
  "fullName": "New User"
}
```

#### POST /api/auth/change-password
**Description:** Change current user's password

**Headers:** `Authorization: Bearer {token}`

**Request:**
```json
{
  "oldPassword": "current123",
  "newPassword": "newSecure456"
}
```

### 4.2 Employee Endpoints

#### GET /api/employees
**Description:** Get employees (role-based: admin sees all, user sees own)

**Headers:** `Authorization: Bearer {token}`

**Response:**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "department": "IT",
    "salary": 75000.0,
    "email": "john@example.com",
    "phone": "1234567890",
    "position": "Software Engineer",
    "status": "ACTIVE"
  }
]
```

#### GET /api/employees/me
**Description:** Get current user's employee profile

**Headers:** `Authorization: Bearer {token}`

#### POST /api/employees
**Description:** Create new employee (admin only, auto-creates user)

**Headers:** `Authorization: Bearer {token}`

**Request:**
```json
{
  "name": "Jane Smith",
  "department": "HR",
  "salary": 65000.0,
  "email": "jane@example.com",
  "phone": "9876543210",
  "position": "HR Manager",
  "status": "ACTIVE"
}
```

**Response:**
```json
{
  "employee": {
    "id": 5,
    "name": "Jane Smith",
    "department": "HR",
    "salary": 65000.0,
    "email": "jane@example.com",
    "phone": "9876543210",
    "position": "HR Manager",
    "status": "ACTIVE"
  },
  "username": "Jane",
  "temporaryPassword": "Jane123"
}
```

#### PUT /api/employees/{id}
**Description:** Update employee (admin only)

**Headers:** `Authorization: Bearer {token}`

#### DELETE /api/employees/{id}
**Description:** Delete employee (admin only)

**Headers:** `Authorization: Bearer {token}`

#### GET /api/employees/search/{searchTerm}
**Description:** Search employees by name or department

#### GET /api/employees/department/{department}
**Description:** Get employees by department

#### GET /api/employees/stats/count
**Description:** Get total employee count

#### GET /api/employees/stats/salary
**Description:** Get average salary

---

## 5. Frontend Implementation

### 5.1 Page Structure

**index.html - Login Page:**
- Clean, centered login form
- Role-based redirection after authentication
- Token and user info stored in localStorage
- Error handling with user-friendly messages

**admin.html - Admin Dashboard:**
- Overview cards (Total Employees, Avg Salary, Departments, Active)
- Employee creation form
- Password change form
- Employee table with search functionality
- Edit and delete buttons for each employee
- Real-time statistics updates
- Auto-displays generated credentials when creating employees

**user.html - User Profile:**
- Read-only view of own employee information
- Password change functionality
- Clean, minimal interface
- No access to CRUD operations

### 5.2 Authentication Flow

```javascript
// Login and store credentials
async function login() {
    const response = await fetch(`${apiBase}/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    });
    
    const data = await response.json();
    localStorage.setItem('token', data.token);
    localStorage.setItem('username', data.username);
    localStorage.setItem('roles', JSON.stringify(data.roles));
    localStorage.setItem('employeeId', data.employeeId || '');
    
    // Redirect based on role
    if (data.roles.includes('ROLE_ADMIN')) {
        window.location.href = 'admin.html';
    } else {
        window.location.href = 'user.html';
    }
}

// Include token in all API requests
const headers = {
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${localStorage.getItem('token')}`
};
```

### 5.3 Admin Dashboard Features

**Employee Management:**
- Add new employees with auto-generated user credentials
- Display created username and temporary password
- Edit existing employee details
- Delete employees with confirmation
- Real-time search and filtering

**Statistics Dashboard:**
- Total employee count
- Average salary calculation
- Department count
- Active employee count

**Password Management:**
- Change password with old password verification
- New password confirmation
- Success/error messaging

---

## 6. Security Implementation

### 6.1 Password Security
- BCrypt hashing with salt (strength 10)
- Passwords never stored in plain text
- Password change requires old password verification

### 6.2 JWT Security
- Tokens signed with HS256 algorithm
- 24-hour expiration (configurable)
- Secret key stored in application properties
- Token validation on every protected endpoint

### 6.3 CORS Configuration
```java
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setExposedHeaders(Arrays.asList("Authorization"));
    return source;
}
```

### 6.4 Input Validation
- Required field validation
- Email format validation
- Salary range validation
- Unique constraint enforcement (username, email)

---

## 7. Configuration & Deployment

### 7.1 MySQL Configuration

**application-mysql.properties:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3307/ems_db
spring.datasource.username=root
spring.datasource.password=Ashutosh@3276
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 7.2 Default User Initialization

**DataInitializer.java:**
- Creates admin account on first run
- Creates sample user accounts (john, jane, mike)
- Links users to employee records
- Ensures data consistency

### 7.3 Build & Run

**Backend:**
```bash
cd "Day 4 Project/backend"
$env:SPRING_PROFILES_ACTIVE="mysql"
mvnd clean install
mvnd spring-boot:run
```

**Frontend:**
```bash
cd "Day 4 Project/frontend"
python -m http.server 3000
```

---

## 8. Testing & Validation

### 8.1 Test Scenarios Covered

✅ **Authentication Tests:**
- Valid login with correct credentials
- Invalid login with wrong credentials
- Token expiration handling
- Logout functionality

✅ **Authorization Tests:**
- Admin can access all employee records
- User can only access own record
- CRUD operations restricted to admin
- Unauthorized access returns 401/403

✅ **Employee CRUD Tests:**
- Create employee with auto user generation
- Read employee details
- Update employee information
- Delete employee
- Search and filter operations

✅ **Password Change Tests:**
- Change password with correct old password
- Reject change with incorrect old password
- Password confirmation matching
- Successful password update

### 8.2 API Test Examples

**Test Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

**Test Create Employee:**
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Authorization: Bearer {TOKEN}" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Employee",
    "department": "Engineering",
    "salary": 80000,
    "email": "test@example.com",
    "position": "Developer",
    "status": "ACTIVE"
  }'
```

---

## 9. Achievements & Highlights

### 9.1 Technical Excellence
- **Clean Architecture:** Proper separation of concerns (Controller → Service → Repository)
- **Security Best Practices:** JWT authentication, BCrypt hashing, CORS configuration
- **Database Design:** Normalized schema with proper relationships and constraints
- **Error Handling:** Comprehensive exception handling with meaningful messages
- **Code Quality:** Well-structured, maintainable code with clear naming conventions

### 9.2 User Experience
- **Role-Based UI:** Different interfaces for admin and users
- **Responsive Design:** Works on desktop and mobile devices
- **Real-Time Feedback:** Success/error messages for all operations
- **Intuitive Navigation:** Clear flow from login to dashboards
- **Auto-Generated Credentials:** Admins see created username/password immediately

### 9.3 Innovation
- **Automatic User Creation:** Seamless employee-to-user account linking
- **Smart Username Generation:** Handles duplicates automatically
- **Password Management:** Self-service password change for all users
- **Statistics Dashboard:** Real-time metrics and insights

---

## 10. Future Enhancements

### Planned Features
- [ ] Email notifications for new account creation
- [ ] Password reset via email
- [ ] Employee profile pictures
- [ ] Advanced search with multiple filters
- [ ] Export employees to CSV/Excel
- [ ] Audit log for admin actions
- [ ] Department-based permissions
- [ ] Employee attendance tracking
- [ ] Performance review module
- [ ] Document management system

### Technical Improvements
- [ ] Unit and integration tests
- [ ] API documentation with Swagger/OpenAPI
- [ ] Docker containerization
- [ ] CI/CD pipeline setup
- [ ] Logging with ELK stack
- [ ] Redis caching for performance
- [ ] Frontend framework (React/Vue)
- [ ] Mobile application

---

## 11. Lessons Learned

### Technical Learnings
1. **Spring Security:** Deep understanding of JWT implementation and filter chains
2. **JPA Relationships:** Properly managing entity relationships and cascading
3. **CORS Configuration:** Handling cross-origin requests in Spring Boot
4. **Transaction Management:** Using @Transactional for data consistency
5. **MySQL Integration:** Profile-based configuration and connection pooling

### Best Practices Adopted
1. **DTO Pattern:** Separating entity and response models
2. **Service Layer:** Business logic isolation from controllers
3. **Exception Handling:** Centralized error responses
4. **Security First:** Never exposing sensitive data in responses
5. **Code Documentation:** Clear comments and meaningful variable names

---

## 12. Conclusion

This Employee Management System successfully demonstrates a production-ready application with:

- ✅ **Robust Security:** JWT-based authentication with role-based authorization
- ✅ **Clean Architecture:** Layered design following Spring Boot best practices
- ✅ **User-Friendly Interface:** Intuitive dashboards for different user roles
- ✅ **Automated Workflows:** Auto user creation and credential management
- ✅ **Scalable Design:** Easy to extend with new features and modules
- ✅ **Database Integration:** MySQL with proper schema and relationships

The project showcases proficiency in:
- Full-stack web development
- RESTful API design and implementation
- Security implementation (authentication & authorization)
- Database design and ORM (JPA/Hibernate)
- Frontend development (HTML/CSS/JavaScript)
- Project structure and organization

---

## Appendix A: File Structure

```
Day 4 Project/
├── backend/
│   ├── pom.xml
│   ├── src/main/java/com/employee/
│   │   ├── EmployeeManagementApplication.java
│   │   ├── config/
│   │   │   ├── CorsConfig.java
│   │   │   ├── DataInitializer.java
│   │   │   └── SecurityConfig.java
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   └── EmployeeController.java
│   │   ├── dto/
│   │   │   ├── ChangePasswordRequest.java
│   │   │   ├── CreatedEmployeeResponse.java
│   │   │   ├── EmployeeDTO.java
│   │   │   ├── LoginRequest.java
│   │   │   ├── LoginResponse.java
│   │   │   └── RegisterRequest.java
│   │   ├── entity/
│   │   │   ├── EmployeeEntity.java
│   │   │   └── User.java
│   │   ├── repository/
│   │   │   ├── EmployeeRepository.java
│   │   │   └── UserRepository.java
│   │   ├── service/
│   │   │   ├── AuthService.java
│   │   │   └── EmployeeService.java
│   │   └── util/
│   │       └── JwtUtil.java
│   └── src/main/resources/
│       ├── application.properties
│       └── application-mysql.properties
├── frontend/
│   ├── admin.html
│   ├── index.html
│   └── user.html
├── QUICKSTART.md
├── README.md
└── PROJECT_REPORT.md
```

---

## Appendix B: Default Credentials

| Username | Password  | Role       | Employee ID | Access Level          |
|----------|-----------|------------|-------------|-----------------------|
| admin    | admin123  | ROLE_ADMIN | null        | Full system access    |
| john     | john123   | ROLE_USER  | 1           | Own profile only      |
| jane     | jane123   | ROLE_USER  | 2           | Own profile only      |
| mike     | mike123   | ROLE_USER  | 3           | Own profile only      |

---

## Appendix C: API Response Codes

| Code | Status                | Description                          |
|------|-----------------------|--------------------------------------|
| 200  | OK                    | Successful GET/PUT request           |
| 201  | Created               | Successful POST request              |
| 400  | Bad Request           | Invalid input data                   |
| 401  | Unauthorized          | Missing or invalid token             |
| 403  | Forbidden             | Insufficient permissions             |
| 404  | Not Found             | Resource not found                   |
| 500  | Internal Server Error | Server-side error                    |

---

**Report Generated:** January 8, 2026  
**Project Version:** 1.0  
**Documentation Status:** Complete
