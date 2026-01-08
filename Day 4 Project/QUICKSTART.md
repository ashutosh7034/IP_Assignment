# Day 4 Project - Quick Start Guide (MySQL profile)

## Prerequisites
- Java 17+
- Maven or mvnd
- MySQL running on localhost:3307 with database `ems_db`, user `root`, password `Ashutosh@3276`

## Backend (MySQL profile)

### Step 1: Navigate to backend
```bash
cd "Day 4 Project/backend"
```

### Step 2: Build
```bash
mvnd clean install
```

### Step 3: Run with MySQL profile
```bash
$env:SPRING_PROFILES_ACTIVE="mysql"   # PowerShell
mvnd spring-boot:run
```

App runs at http://localhost:8080

## Frontend (split pages)
```bash
cd "Day 4 Project/frontend"
python -m http.server 3000
```
Open http://localhost:3000 then:
- Login page: index.html
- Admin dashboard: admin.html (opens after admin login)
- User profile: user.html (opens after user login)

## Default accounts
- Admin: admin / admin123
- Users: john/john123, jane/jane123, mike/mike123

## Key endpoints
- POST /api/auth/login — returns token, roles, employeeId
- GET /api/employees — admin: all; user: own record
- GET /api/employees/me — current user profile
- POST/PUT/DELETE /api/employees — admin only

## Quick API test
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'

curl -H "Authorization: Bearer <TOKEN>" http://localhost:8080/api/employees
```

## Config notes
- MySQL settings: backend/src/main/resources/application-mysql.properties
- If port 8080 is busy: set server.port=8081 in application-mysql.properties

## Troubleshooting
- Login fails: ensure mysql profile is active and Authorization header uses `Bearer <token>`
- DB connection: verify MySQL at 3307 with correct creds and schema `ems_db`
- CORS: backend allows http://localhost:3000

## Testing the API

### Get JWT Token (Login)
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"user123"}'
```

Copy the token from response.

### Use Token for API Requests
Replace `<TOKEN>` with the JWT token from login response:

**Get all employees:**
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/employees
```

**Get specific employee:**
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/employees/1
```

**Add new employee:**
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "name":"Charlie White",
    "department":"Marketing",
    "salary":48000,
    "email":"charlie@example.com",
    "phone":"9988776655",
    "position":"Marketing Manager"
  }'
```

**Update employee:**
```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "name":"John Doe Updated",
    "department":"IT",
    "salary":52000,
    "email":"john.updated@example.com",
    "phone":"9876543210",
    "position":"Senior Developer"
  }'
```

**Delete employee:**
```bash
curl -X DELETE http://localhost:8080/api/employees/1 \
  -H "Authorization: Bearer <TOKEN>"
```

**Search employees:**
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/employees/search/John
```

**Get employees by department:**
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/employees/department/IT
```

## Using Frontend UI

1. Open `frontend/index.html` in your web browser
2. Login with credentials:
   - Username: `user`
   - Password: `user123`
3. After login, you'll see the employee dashboard
4. Add new employees using the form
5. Search employees using the search bar
6. Edit or delete employees from the table

## Database Console (H2)

1. Navigate to: http://localhost:8080/h2-console
2. Keep default settings:
   - Driver Class: org.h2.Driver
   - JDBC URL: jdbc:h2:mem:testdb
   - User Name: sa
   - Password: (leave empty)
3. Click "Connect"
4. View tables: EMPLOYEES, USERS, USER_ROLES

## Configuration Changes

### Change Server Port
Edit `backend/src/main/resources/application.properties`:
```properties
server.port=8081
```

### Use MySQL Instead of H2
1. Add MySQL dependency to pom.xml
2. Update application.properties:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

### Change JWT Secret
Edit `backend/src/main/resources/application.properties`:
```properties
jwt.secret=your_new_secret_key_at_least_32_characters
jwt.expiration=86400000
```

## Troubleshooting

### Port 8080 Already in Use
Change the port in `application.properties`:
```properties
server.port=8081
```

### Cannot Login
1. Ensure the application is running
2. Check that you're using correct credentials:
   - Username: `user`, Password: `user123`
   - Or Username: `admin`, Password: `admin123`
3. Check browser console for error messages

### CORS Errors
CORS is already enabled in the application. If you still get CORS errors:
1. Make sure you're accessing from localhost
2. Check that Authorization header includes "Bearer " prefix

### Token Expired
JWT tokens expire after 24 hours. Simply login again to get a new token.

### Database Not Initialized
The H2 database auto-initializes with sample data on first run. If data is missing:
1. Delete the database files
2. Restart the application

## Project Files

**Backend Structure:**
- `src/main/java/com/employee/entity/` - JPA entities (EmployeeEntity, User)
- `src/main/java/com/employee/repository/` - Data access layer
- `src/main/java/com/employee/service/` - Business logic (EmployeeService, AuthService)
- `src/main/java/com/employee/controller/` - REST API endpoints
- `src/main/java/com/employee/dto/` - Data transfer objects
- `src/main/java/com/employee/util/` - JWT utility
- `src/main/java/com/employee/config/` - Spring configuration
- `src/main/resources/application.properties` - Application settings
- `pom.xml` - Maven dependencies

**Frontend Files:**
- `frontend/index.html` - Login page and main dashboard
- Uses Axios for HTTP requests
- JWT token stored in localStorage
- Responsive design with CSS Grid

## Key Features Implemented

✅ JWT Authentication & Authorization
✅ User Registration & Login
✅ JPA Entity Management with Hibernate
✅ H2 In-Memory Database
✅ Complete CRUD Operations
✅ Search & Filter Functionality
✅ Statistics Endpoints (Count, Average Salary)
✅ Role-Based Access Control
✅ CORS Configuration
✅ Password Hashing with BCrypt
✅ Responsive Frontend UI
✅ Axios HTTP Client Integration
✅ Automatic Data Initialization

## Learning Outcomes

By completing this project, you will understand:
- Spring Boot with Spring Security
- JWT token-based authentication
- JPA and Hibernate ORM
- Spring Data repositories
- Service layer pattern
- DTO pattern for data transfer
- REST API best practices
- Frontend-Backend integration
- CORS configuration
- Database persistence
- Password encryption
- Role-based authorization

## Support

For questions or issues:
1. Check the main [README.md](README.md)
2. Review Spring Boot documentation: https://spring.io/projects/spring-boot
3. Check Spring Security docs: https://spring.io/projects/spring-security
4. JWT documentation: https://jwt.io

## Next Steps

1. Explore the database using H2 Console
2. Test all API endpoints using cURL or Postman
3. Try the frontend UI after login
4. Try adding, updating, and deleting employees
5. Experiment with search and filter features
6. Try different user roles (admin vs regular user)
