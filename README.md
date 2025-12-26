# IP Assignment - Complete Summary

## 📚 Overview
This repository contains all Internet Programming assignments organized into 4 comprehensive projects covering Java fundamentals, OOP, web development, and Spring Boot.

---

## 🗂️ Project Structure

```
IP assignment/
├── Day 1 Project/          # Java Basics & Console Apps
├── Day 2 Project/          # OOP Concepts & Web Development
├── Day 3 Project/          # Spring Boot REST API
└── Day 4 Project/          # Spring Boot + JWT + JPA
```

---

## 📋 Assignment Summary

### **Day 1 Project** - Java Fundamentals
**Topics:** Java basics, loops, conditionals, ArrayList, CRUD operations

**Programs:**
1. **JackpotGame.java** - Random number guessing game with scoring
2. **EmployeeManagementSystem.java** - Console-based CRUD for employee records

**How to Run:**
```bash
cd "Day 1 Project"
javac JackpotGame.java
java JackpotGame

javac EmployeeManagementSystem.java
java EmployeeManagementSystem
```

---

### **Day 2 Project** - Object-Oriented Programming & Web
**Topics:** Inheritance, polymorphism, interfaces, HTML5, CSS3, JavaScript

**Java Programs (6):**
1. **StudentGradeCard.java** - Grade calculation with inheritance
2. **BankAccountManagement.java** - Account types with OOP
3. **ShapeHierarchy.java** - Abstract classes and polymorphism
4. **EmployeeSalaryCalculator.java** - Salary computation with bonuses
5. **VehicleInheritanceSystem.java** - Multi-level inheritance
6. **LibraryManagementSystem.java** - Interface implementation

**Web Applications (4):**
1. **ToDoList.html** - Task management app
2. **Calculator.html** - Basic arithmetic calculator
3. **TemperatureConverter.html** - C/F conversion
4. **DigitalClock.html** - Real-time clock display

**How to Run:**
```bash
# Java programs
cd "Day 2 Project"
javac StudentGradeCard.java
java StudentGradeCard

# Web apps - open HTML files in browser
start ToDoList.html
```

---

### **Day 3 Project** - Spring Boot REST API
**Topics:** Spring Boot, REST API, CRUD operations, CORS, Maven

**Architecture:**
- Backend: Spring Boot 3.2.0 with in-memory storage
- Frontend: Single-page application with Axios
- API Endpoints: GET, POST, PUT, DELETE for employees

**Tech Stack:**
- Spring Boot Web
- Maven
- Axios (frontend)
- No database (ArrayList storage)

**How to Run:**
```bash
cd "Day 3 Project"

# Backend
mvn clean install
mvn spring-boot:run
# Server runs on http://localhost:8080

# Frontend
# Open frontend/index.html in browser
```

---

### **Day 4 Project** - Advanced Spring Boot with Security
**Topics:** Spring Security, JWT authentication, JPA, H2 database, BCrypt

**Architecture:**
- Backend: Spring Boot 3.2.0 + Security + JPA
- Database: H2 (in-memory) / MySQL support
- Authentication: JWT tokens with refresh
- Security: BCrypt password hashing

**Features:**
- User registration & login
- JWT-based authentication
- Role-based access control (USER, ADMIN)
- Employee CRUD with authorization
- Secure API endpoints

**Tech Stack:**
- Spring Boot 3.2.0
- Spring Security
- Spring Data JPA
- JWT (jjwt 0.12.3)
- H2 Database
- Lombok

**Default Credentials:**
- User: `user` / `user123`
- Admin: `admin` / `admin123`

**How to Run:**
```bash
cd "Day 4 Project"

# Backend
cd backend
mvn clean install
mvn spring-boot:run
# Server runs on http://localhost:8080

# Frontend
# Open frontend/index.html in browser
```

**API Endpoints:**
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login and get JWT token
- `GET /api/employees` - Get all employees (authenticated)
- `POST /api/employees` - Create employee (authenticated)
- `PUT /api/employees/{id}` - Update employee (authenticated)
- `DELETE /api/employees/{id}` - Delete employee (authenticated)

---

## 🔧 Prerequisites

**Required Software:**
- **JDK 17+** - For all Java programs
- **Maven 3.6+** - For Spring Boot projects (Day 3 & 4)
- **Modern Browser** - For web applications
- **IDE** - IntelliJ IDEA, Eclipse, or VS Code (recommended)

**Verify Installation:**
```bash
java -version          # Should show Java 17+
mvn -version          # Should show Maven 3.6+
```

---

## 🎯 Learning Outcomes

### Day 1
✅ Java syntax and control structures  
✅ ArrayList and collections  
✅ Scanner for user input  
✅ CRUD operations in console apps

### Day 2
✅ Object-oriented programming principles  
✅ Inheritance and polymorphism  
✅ Abstract classes and interfaces  
✅ HTML5, CSS3, JavaScript fundamentals  
✅ DOM manipulation and event handling

### Day 3
✅ Spring Boot framework basics  
✅ RESTful API design  
✅ HTTP methods (GET, POST, PUT, DELETE)  
✅ CORS configuration  
✅ Maven project structure  
✅ Frontend-backend integration with Axios

### Day 4
✅ Spring Security configuration  
✅ JWT authentication and authorization  
✅ Spring Data JPA and Hibernate  
✅ Database integration (H2/MySQL)  
✅ Password encryption with BCrypt  
✅ Role-based access control  
✅ Secure REST API development

---

## 📖 Assignment Resources

**Assignment Pages:**
- [Day 1 Assignment](https://shaileshsonareg.github.io/tcet/docs/ip/day1/assignment/)
- [Day 2 Assignment](https://shaileshsonareg.github.io/tcet/docs/ip/day2/assignment/)
- [Day 3 Assignment](https://shaileshsonareg.github.io/tcet/docs/ip/day3/assignment/)
- [Day 4 Assignment](https://shaileshsonareg.github.io/tcet/docs/ip/day4/assignment/)

**MCQ Tests:**
- [Online Quiz Portal](https://cquiz.ciacloud.in/take_test.php)

**Reference Code:**
- [TCET Repository](https://github.com/shaileshsonareg/tcet.git)
- [Spring Boot Demo](https://github.com/ciaindia/spring-boot-demo.git)

---

## 🚀 Quick Start Guide

**For Simple Java Programs (Day 1-2):**
```bash
cd "Day 1 Project"      # or "Day 2 Project"
javac ProgramName.java
java ProgramName
```

**For Web Applications (Day 2):**
```bash
cd "Day 2 Project"
# Double-click any .html file or
start Calculator.html
```

**For Spring Boot Projects (Day 3-4):**
```bash
cd "Day 3 Project"      # or "Day 4 Project"
cd backend              # Day 4 only
mvn spring-boot:run
# Then open frontend/index.html in browser
```

---

## ✅ Testing Status

| Project | Status | Details |
|---------|--------|---------|
| Day 1 | ✅ PASSED | Both Java files compiled successfully |
| Day 2 | ✅ PASSED | 6/6 Java + 4/4 HTML verified |
| Day 3 | ✅ VERIFIED | 5 Java files + Maven structure intact |
| Day 4 | ✅ VERIFIED | 16 Java files + Spring Boot structure intact |

---

## 📝 Notes

- Each project folder contains its own detailed README.md
- Day 3 and Day 4 include QUICKSTART.md for setup
- All projects follow assignment requirements exactly
- Code is well-commented and follows best practices

---

## 👨‍💻 Author
**Ashutosh**  
Internet Programming Course  
December 2025
