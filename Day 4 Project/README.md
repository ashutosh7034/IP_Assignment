# Day 4 Project - Employee Management (Spring Boot + JWT + MySQL)

## Overview
Full-stack employee management with JWT auth, role-based dashboards, and MySQL persistence. Admins can manage all employees; regular users see only their own profile.

---

## Quick Start

### Prerequisites
- Java 17+
- Maven or mvnd (Maven Daemon)
- MySQL running locally on port 3307 with database `ems_db` and user `root` / password `Ashutosh@3276`
- Node not required; frontend is plain HTML/JS

Create the database (once):
```sql
CREATE DATABASE IF NOT EXISTS ems_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
Optional: run the schema file if you want explicit DDL: `backend/db/mysql/ems_schema.sql`.

### Backend (MySQL profile)
```bash
cd "Day 4 Project/backend"
$env:SPRING_PROFILES_ACTIVE="mysql"   # PowerShell (Windows)
mvnd spring-boot:run                   # or: mvn spring-boot:run
```
The app starts on http://localhost:8080.

### Frontend
```bash
cd "Day 4 Project/frontend"
python -m http.server 3000
```
Open http://localhost:3000 and log in.

### Default Accounts
- Admin: admin / admin123 (can add/edit/delete and view all)
- Users: john/john123, jane/jane123, mike/mike123 (can only see their own profile)

### Key Endpoints (require Bearer token)
- POST /api/auth/login — returns token, roles, employeeId
- GET /api/employees — admin: all; user: only their own record
- GET /api/employees/me — current user’s employee profile
- POST /api/employees — admin only (create)
- PUT /api/employees/{id} — admin only (update)
- DELETE /api/employees/{id} — admin only (delete)

### API Smoke Test
```bash
curl -X POST http://localhost:8080/api/auth/login \ 
  -H "Content-Type: application/json" \ 
  -d '{"username":"admin","password":"admin123"}'

curl -H "Authorization: Bearer <TOKEN>" http://localhost:8080/api/employees
```

### Configuration Notes
- MySQL profile config: backend/src/main/resources/application-mysql.properties
- Default profile uses H2; for MySQL always set `SPRING_PROFILES_ACTIVE=mysql`.
- If port 8080 is busy, set `server.port=8081` in application-mysql.properties.

### Troubleshooting
- Login fails: ensure backend is running with mysql profile and Authorization header is `Bearer <token>`.
- DB connection issues: verify MySQL on port 3307, credentials match, and schema exists.
- CORS: backend allows http://localhost:3000 by default via SecurityConfig/CorsConfig.

---

## Tech Stack
- Spring Boot 3.2.x, Spring Security, Spring Data JPA, JJWT 0.11.5
- Java 17, Maven/mvnd
- MySQL 8 (primary), H2 for default profile
- Frontend: HTML/CSS/JS with Axios; tokens stored in localStorage

---

## Project Structure

```
Day 4 Project/
├── backend/
│   ├── src/main/java/com/employee/
│   │   ├── config/ (SecurityConfig, CorsConfig, DataInitializer)
│   │   ├── controller/ (AuthController, EmployeeController)
│   │   ├── dto/ (LoginRequest, LoginResponse, RegisterRequest, EmployeeDTO)
│   │   ├── entity/ (EmployeeEntity, User)
│   │   ├── service/ (AuthService, EmployeeService)
│   │   └── util/ (JwtUtil)
│   ├── src/main/resources/ (application.properties, application-mysql.properties)
│   └── db/mysql/ems_schema.sql
├── frontend/index.html
├── QUICKSTART.md
└── README.md
```

---

## Behavior by Role
- Admin: full CRUD on employees; sees all rows in dashboard.
- User: sees only their own employee profile; no add/edit/delete controls in UI.

---

## Tips
- If you switch DB credentials or port, edit application-mysql.properties.
- Tokens include roles and employeeId; the frontend stores them in localStorage for role-based UI.
- Use `mvnd clean install -DskipTests` to build faster once dependencies are cached.

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

For questions or issues:
1. Review the assignment links and documentation
2. Check Spring Boot and JWT documentation
3. Inspect browser console and network tab
4. Review backend console logs
5. Test endpoints with Postman/cURL


---

**Created**: December 2025  
**Level**: Advanced  
**Duration**: 1 week  
**Instructor**: TCET MCA  
**Technologies**: Spring Boot, JPA, JWT, MySQL/H2, JavaScript, Axios
