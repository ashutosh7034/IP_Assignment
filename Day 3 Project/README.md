# Day 3 Project - Spring Boot REST API & Frontend Integration

## Overview
Day 3 focuses on building a complete full-stack application combining Spring Boot REST API with modern frontend development. Students create a professional Employee Management System demonstrating CRUD operations, asynchronous API calls, and responsive web design.

## 📚 Assignment Links
- **Assignment Details**: [Day 3 Assignment](https://shaileshsonareg.github.io/tcet/docs/assignment_day3.html)
- **Assignment Demo**: [Day 3 Demo](https://shaileshsonareg.github.io/tcet/docs/assignment_day3_demo.html)
- **MCQ Test**: [Day 3 Test](https://cquiz.ciacloud.in/take_test.php?test_id=MTE2)
- **Reference Code**: [GitHub - TCET Day 3](https://github.com/shaileshsonareg/tcet/tree/main/day3)

---

## 🚀 How to Run - Quick Start

### Step 1: Start the Backend (Spring Boot)

```bash
# Navigate to backend folder
cd "Day 3 Project"

# Build and run using Maven
mvn clean install
mvn spring-boot:run
```

**Alternative (if Maven wrapper exists):**
```bash
# Windows
.\mvnw spring-boot:run

# Mac/Linux
./mvnw spring-boot:run
```

✅ **Backend is ready when you see:**
```
Started EmployeeManagementApp in X.XXX seconds
```

🌐 **Backend URL:** http://localhost:8080

### Step 2: Open the Frontend

```bash
# Simply open the HTML file in your browser
Open "Day 3 Project/frontend/index.html"
```

**OR** double-click `index.html` in File Explorer

### Step 3: Test the Application

1. Browser opens with Employee Management interface
2. Click "Add Employee" to create new records
3. Use Edit/Delete buttons to modify data
4. Try the search functionality

### Prerequisites

✅ **Required:**
- Java 17 or higher - [Download](https://www.oracle.com/java/technologies/downloads/)
- Maven 3.6+ - [Download](https://maven.apache.org/download.cgi)
- Modern web browser

✅ **Verify Installation:**
```bash
java -version    # Should show Java 17+
mvn -version     # Should show Maven 3.6+
```

### Troubleshooting

**Port 8080 already in use?**
```properties
# Edit: src/main/resources/application.properties
server.port=8081
```

**Maven not found?**
- Download and install Maven from official website
- Add Maven to PATH environment variable

**Backend not starting?**
```bash
# Clean and rebuild
mvn clean
mvn install
mvn spring-boot:run
```

For detailed instructions, see [QUICKSTART.md](QUICKSTART.md)

---

## 📋 Project: Employee Management System (CRUD)

### Objective
Implement a complete CRUD application with REST API integration demonstrating full-stack web development.

### Project Overview
The Employee Management System is a full-stack web application that enables users to perform Create, Read, Update, and Delete (CRUD) operations on employee records. This mini project demonstrates the integration of a modern frontend interface with a RESTful Spring Boot backend.

### Key Learning Outcomes
- ✅ Implement a complete CRUD application with REST API integration
- ✅ Demonstrate understanding of frontend-backend communication using Axios
- ✅ Create an intuitive user interface with modal dialogs and confirmations
- ✅ Handle asynchronous operations and error management
- ✅ Apply modern web design principles and responsive layout

---

## 🏗️ Technology Stack

### Backend
- **Spring Boot 3.2.0** - Application framework
- **Java 17** - Programming language
- **Maven** - Build tool
- **REST API** - JSON over HTTP

### Frontend
- **HTML5** - Structure
- **CSS3** - Styling and responsive design
- **JavaScript ES6** - Dynamic functionality
- **Axios** - HTTP client library (via CDN)

### Data Management
- **ArrayList<Employee>** - In-memory storage (no database)
- **CORS** - Cross-Origin Resource Sharing

---

## 📊 Data Model

### Employee Entity
```java
public class Employee {
    private int id;           // Unique identifier (auto-generated)
    private String name;      // Employee full name
    private String department;// Department (IT, HR, Finance, etc.)
    private int salary;       // Annual salary
}
```

---

## 🔌 API Endpoints

### 1. Get All Employees
```
GET /api/employees
Returns: List of all employees
Response: [
  { "id": 1, "name": "John Doe", "department": "IT", "salary": 50000 },
  { "id": 2, "name": "Jane Smith", "department": "HR", "salary": 55000 }
]
```

### 2. Get Employee by ID
```
GET /api/employees/{id}
Returns: Single employee object
Response: { "id": 1, "name": "John Doe", "department": "IT", "salary": 50000 }
```

### 3. Create New Employee
```
POST /api/employees
Request Body: { "name": "Alice Brown", "department": "IT", "salary": 52000 }
Returns: Created employee with generated ID
```

### 4. Update Employee
```
PUT /api/employees/{id}
Request Body: { "name": "John Doe", "department": "IT", "salary": 55000 }
Returns: Updated employee object
```

### 5. Delete Employee
```
DELETE /api/employees/{id}
Returns: Success message or error
```

---

## 🎨 Frontend Features

### Feature 1: Display Employee List
- ✅ Show all employees in a professional HTML table
- ✅ Display columns: ID, Name, Department, Salary, Actions
- ✅ Include total employee count
- ✅ Show appropriate message when no employees exist
- ✅ Implement loading indicator during data fetch

### Feature 2: Add New Employee
- ✅ Open a modal popup when "Add Employee" button is clicked
- ✅ Form fields: Name, Department, Salary (all required)
- ✅ Form validation before submission
- ✅ Use Axios POST request to /api/employees endpoint
- ✅ Close modal and refresh list after successful creation

### Feature 3: Edit Employee
- ✅ Each employee row has an "Edit" button
- ✅ Open the same modal popup with pre-filled data
- ✅ Use Axios PUT request to /api/employees/{id} endpoint
- ✅ Update the list after successful edit

### Feature 4: Delete Employee
- ✅ Each employee row has a "Delete" button
- ✅ Show confirmation dialog with employee name before deletion
- ✅ Proceed with deletion only after user confirms
- ✅ Use Axios DELETE request to /api/employees/{id} endpoint
- ✅ Refresh list after successful deletion

### UI/UX Requirements
- ✅ Clean and modern design with professional color scheme
- ✅ Responsive layout that works on different screen sizes
- ✅ Modal dialogs for add/edit operations (not separate pages)
- ✅ Confirmation dialog for delete operations
- ✅ Smooth animations and transitions
- ✅ Clear action buttons with appropriate labels
- ✅ Error handling and user feedback messages

---

## 📂 Project Structure

```
Day 3 Project/
├── backend/
│   ├── src/main/java/com/employee/
│   │   ├── model/
│   │   │   └── Employee.java
│   │   ├── service/
│   │   │   └── EmployeeService.java
│   │   ├── controller/
│   │   │   └── EmployeeController.java
│   │   ├── config/
│   │   │   └── CorsConfig.java
│   │   └── EmployeeManagementApp.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
├── frontend/
│   └── index.html
├── QUICKSTART.md
└── README.md
```

---

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- Modern web browser (Chrome, Firefox, Safari)
- Terminal/Command Prompt

### Backend Setup
```bash
cd "Day 3 Project/backend"
mvn clean install
mvn spring-boot:run
```

Application starts on http://localhost:8080

### Frontend Setup
```bash
cd "Day 3 Project/frontend"
Open index.html in web browser
```

---

## 🧪 Testing the Application

### Test Checklist
- [ ] Verify employee list loads on page refresh
- [ ] Test adding new employee with valid data
- [ ] Test form validation with empty fields
- [ ] Test editing existing employee
- [ ] Test delete with confirmation and cancellation
- [ ] Test UI responsiveness on different screen sizes
- [ ] Verify error handling when backend is down
- [ ] Test all CRUD operations

### Using cURL for API Testing

**Get all employees:**
```bash
curl http://localhost:8080/api/employees
```

**Add new employee:**
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "name":"Charlie White",
    "department":"Marketing",
    "salary":48000
  }'
```

**Update employee:**
```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name":"John Doe",
    "department":"IT",
    "salary":55000
  }'
```

**Delete employee:**
```bash
curl -X DELETE http://localhost:8080/api/employees/1
```

---

## 🔧 Backend Configuration

### application.properties
```properties
server.port=8080
spring.application.name=Day3-EmployeeManagement
logging.level.root=INFO
logging.level.com.employee=DEBUG
```

### CORS Configuration
CORS is enabled in Spring Boot to allow frontend communication:
```java
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController { ... }
```

---

## 📝 Implementation Details

### REST Controller
```java
@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() { ... }
    
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) { ... }
    
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, ...) { ... }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) { ... }
}
```

### Axios API Calls (Frontend)
```javascript
const API_URL = 'http://localhost:8080/api/employees';

// Get all employees
axios.get(API_URL).then(response => { ... });

// Create new employee
axios.post(API_URL, employeeData).then(response => { ... });

// Update employee
axios.put(`${API_URL}/${id}`, updateData).then(response => { ... });

// Delete employee
axios.delete(`${API_URL}/${id}`).then(response => { ... });
```

---

## 🎯 Learning Outcomes

Upon completion, students will be able to:

1. **Design RESTful APIs**
   - Understand HTTP methods (GET, POST, PUT, DELETE)
   - Implement proper REST architectural principles
   - Handle JSON request/response

2. **Build Spring Boot Applications**
   - Create REST controllers
   - Implement service layer pattern
   - Configure CORS for frontend integration

3. **Develop Modern Web Interfaces**
   - Create responsive layouts with CSS3
   - Implement modal dialogs and confirmations
   - Use Axios for asynchronous API calls

4. **Handle Full-Stack Integration**
   - Connect frontend with backend APIs
   - Implement proper error handling
   - Display user feedback messages

5. **Debug and Troubleshoot**
   - Identify CORS and API errors
   - Use browser DevTools and console
   - Test endpoints with cURL/Postman

---

## ❓ Common Issues & Solutions

### Issue: CORS Error
**Solution**: Verify @CrossOrigin annotation is on controller

### Issue: 404 Not Found
**Solution**: Check backend is running and API_URL is correct

### Issue: Data not refreshing
**Solution**: Ensure loadEmployees() is called after each operation

### Issue: Modal not closing
**Solution**: Check modal HTML ID and JavaScript selector

### Issue: Backend connection refused
**Solution**: Verify Spring Boot is running on port 8080

---

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Axios Documentation](https://axios-http.com/docs/intro)
- [RESTful API Design](https://restfulapi.net/)
- [JavaScript Async/Await](https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Asynchronous)

---

## 📊 Evaluation Criteria

| Component | Points | Details |
|-----------|--------|---------|
| Backend Implementation | 25 | REST API endpoints, proper HTTP methods, CORS configuration |
| CRUD Operations | 25 | All operations working correctly (Create, Read, Update, Delete) |
| User Interface | 20 | Professional design, modal popups, confirmation dialogs |
| Error Handling | 15 | Proper error messages, validation, loading states |
| Code Quality | 10 | Clean code, proper comments, organized structure |
| Documentation | 5 | Clear setup instructions and comments |

---

## 🎁 Bonus Features (Optional)

- Search/Filter functionality in employee list
- Sorting by different columns
- Pagination for large datasets
- Export employee data to CSV
- Input validation with specific rules
- Dark mode toggle
- Employee count by department
- Statistics dashboard

---

## ✅ Final Checklist

- [ ] Backend compiles and runs without errors
- [ ] Frontend loads in browser without console errors
- [ ] All CRUD operations work correctly
- [ ] Form validation is implemented
- [ ] Modal dialogs open and close properly
- [ ] Confirmation dialog appears for delete
- [ ] Error messages display appropriately
- [ ] UI is responsive on mobile devices
- [ ] Code is properly commented
- [ ] README documentation is complete

---

**Created**: December 2025  
**Level**: Intermediate  
**Duration**: 1 week  
**Instructor**: TCET MCA  
**Reference**: [Day 3 Demo](https://shaileshsonareg.github.io/tcet/docs/assignment_day3_demo.html)
