# Day 2 Project - Object-Oriented Programming & Web Development

## Overview
Day 2 projects combine Java object-oriented programming with web technologies. These assignments expand on Day 1 fundamentals by introducing inheritance, polymorphism, interfaces, and interactive web applications using HTML, CSS, and JavaScript.

## 📚 Assignment Links
- **Assignment Details**: [Day 2 Assignment](https://shaileshsonareg.github.io/tcet/docs/assignment_day2.html)
- **MCQ Test**: [Day 2 Test](https://cquiz.ciacloud.in/take_test.php?test_id=MTE0)
- **Reference Code**: [GitHub - TCET Day 2](https://github.com/shaileshsonareg/tcet.git)

---

## 🚀 How to Run

### For Java Projects (1-6)

```bash
# Navigate to Day 2 Project folder
cd "Day 2 Project"

# Compile any Java program
javac StudentGradeCard.java
javac BankAccountManagement.java
javac ShapeHierarchy.java
javac EmployeeSalaryCalculator.java
javac VehicleInheritanceSystem.java
javac LibraryManagementSystem.java

# Run the compiled program (replace with actual class name)
java StudentGradeCard
java BankAccountManagement
java ShapeHierarchy
java EmployeeSalaryCalculator
java VehicleInheritanceSystem
java LibraryManagementSystem
```

### For Web Projects (7-10)

**Method 1: Direct Browser Opening**
```bash
# Simply double-click any HTML file or right-click and "Open with Browser"
ToDoList.html
Calculator.html
TemperatureConverter.html
DigitalClock.html
```

**Method 2: Using VS Code Live Server**
1. Install "Live Server" extension in VS Code
2. Right-click on HTML file
3. Select "Open with Live Server"
4. Browser opens automatically with live reload

**Method 3: Python HTTP Server (Optional)**
```bash
# Navigate to Day 2 Project folder
cd "Day 2 Project"

# Start simple HTTP server
python -m http.server 8000

# Open browser to:
# http://localhost:8000/ToDoList.html
# http://localhost:8000/Calculator.html
# http://localhost:8000/TemperatureConverter.html
# http://localhost:8000/DigitalClock.html
```

### Prerequisites

**For Java Projects:**
- Java Development Kit (JDK) 8 or higher
- Command Prompt/Terminal

**For Web Projects:**
- Modern web browser (Chrome, Firefox, Safari, Edge)
- No compilation needed - just open HTML files!

### Verify Installation
```bash
java -version
javac -version
```

---

## 📋 Java Projects (1-6)

### Project 1: Student Grade Card Generator

**Objective**: Learn class design, method calculation, and string formatting.

**Features**:
- Student class with fields: rollNo, name, marks (array of 3 subjects)
- calculateGrade() method returning letter grades
- toString() method for formatted output
- Grading system: A (≥80), B (≥60), C (≥40), F (<40)

**Key Concepts**:
- Class design and constructors
- Arrays and calculations
- Method overriding (toString)
- String formatting

**Run**:
```bash
javac StudentGradeCard.java
java StudentGradeCard
```

**Sample Output**:
```
Student [rollNo=101, name=Priya, marks=[85,78,90], grade=A]
Student [rollNo=102, name=Arun, marks=[72,68,75], grade=B]
```

---

### Project 2: Bank Account Management System

**Objective**: Implement encapsulation and data validation principles.

**Features**:
- BankAccount class with private balance
- deposit(amount) - Add money to account
- withdraw(amount) - Remove money with validation
- checkBalance() - View current balance
- Transaction history and validation

**Key Concepts**:
- Encapsulation (private/public)
- Getter/Setter methods
- Data validation
- Boolean return types for success/failure

**Run**:
```bash
javac BankAccountManagement.java
java BankAccountManagement
```

**Sample Output**:
```
Account: 1001, Holder: John Doe, Balance: $50000.00
Deposited: $5000
New Balance: $55000.00
```

---

### Project 3: Shape Hierarchy with Area Calculation

**Objective**: Understand abstract classes and polymorphism.

**Features**:
- Abstract Shape class with area() method
- Subclasses: Rectangle, Triangle, Square
- Polymorphic area calculation
- Array of Shape references

**Key Concepts**:
- Abstract classes and methods
- Method overriding
- Polymorphism
- Inheritance hierarchy

**Run**:
```bash
javac ShapeHierarchy.java
java ShapeHierarchy
```

**Sample Output**:
```
Rectangle (L=5, W=3): Area = 15.00
Triangle (B=4, H=6): Area = 12.00
Square (S=5): Area = 25.00
```

---

### Project 4: Employee Salary Calculator

**Objective**: Practice method design and financial calculations.

**Features**:
- Employee class with empId, name, basicSalary
- Calculate HRA (20% of basic)
- Calculate DA (10% of basic)
- Calculate Tax (5% of gross salary)
- Display formatted payslip

**Key Concepts**:
- Separate calculation methods
- Financial calculations
- Number formatting
- Array of objects

**Run**:
```bash
javac EmployeeSalaryCalculator.java
java EmployeeSalaryCalculator
```

**Sample Output**:
```
===== PAYSLIP =====
Employee ID: 101
Name: John Doe
Basic Salary: $50,000.00
HRA (20%): $10,000.00
DA (10%): $5,000.00
Gross Salary: $65,000.00
Net Salary: $61,750.00
```

---

### Project 5: Vehicle Inheritance System

**Objective**: Demonstrate inheritance and method overriding.

**Features**:
- Base Vehicle class with brand, model, year
- Car subclass with numDoors
- Bike subclass with hasCarrier
- Overridden displayInfo() methods
- instanceof operator usage

**Key Concepts**:
- Single inheritance
- super() keyword
- Method overriding
- instanceof operator

**Run**:
```bash
javac VehicleInheritanceSystem.java
java VehicleInheritanceSystem
```

---

### Project 6: Library Management System

**Objective**: Learn interfaces, ArrayList, and menu-driven applications.

**Features**:
- Book class with id, title, author, isIssued flag
- LibraryOperations interface
- Library class implementing interface
- Menu: Add Book, Issue, Return, Show All, Exit
- Search and validation

**Key Concepts**:
- Interface implementation
- ArrayList for storage
- Menu-driven system
- Input validation

**Run**:
```bash
javac LibraryManagementSystem.java
java LibraryManagementSystem
```

**Menu Options**:
```
1. Add Book
2. Issue Book
3. Return Book
4. Show All Books
0. Exit
```

---

## 🌐 Web Projects (7-10)

### Project 7: Interactive To-Do List Web App

**Objective**: Learn DOM manipulation and event handling.

**Features**:
- Add tasks to a dynamic list
- Delete tasks individually
- Local storage persistence
- Responsive design

**Technologies**:
- HTML5, CSS3, JavaScript

**Run**:
```bash
Open ToDoList.html in browser
```

---

### Project 8: Simple Calculator Web App

**Objective**: Learn form handling and calculations.

**Features**:
- Two number inputs
- Operations: +, -, ×, ÷
- Division by zero handling
- Clean UI

**Run**:
```bash
Open Calculator.html in browser
```

---

### Project 9: Temperature Converter Web Page

**Objective**: Implement conditional logic and user input validation.

**Features**:
- Temperature input field
- Radio buttons for conversion type
- Celsius ↔ Fahrenheit conversion
- Input validation

**Formulas**:
- F = (C × 9/5) + 32
- C = (F - 32) × 5/9

**Run**:
```bash
Open TemperatureConverter.html in browser
```

---

### Project 10: Live Digital Clock in Browser

**Objective**: Learn time handling and dynamic updates.

**Features**:
- Real-time clock display
- 12-hour format with AM/PM
- Updates every second
- Professional styling

**Run**:
```bash
Open DigitalClock.html in browser
```

---

## 🎯 Learning Outcomes

### Java OOP Concepts:
1. **Encapsulation** - Private variables with public getters/setters
2. **Inheritance** - Extending parent classes with super()
3. **Polymorphism** - Method overriding and dynamic dispatch
4. **Abstraction** - Abstract classes and interfaces
5. **Collections** - ArrayList for storage

### Web Development:
1. **DOM Manipulation** - Creating and modifying HTML elements
2. **Event Handling** - Click, submit, change events
3. **Form Handling** - Input validation and submission
4. **CSS Styling** - Responsive layout and aesthetics
5. **JavaScript** - Functions, conditions, loops, Date objects

---

## 📂 Project Structure

```
Day 2 Project/
├── StudentGradeCard.java
├── BankAccountManagement.java
├── ShapeHierarchy.java
├── EmployeeSalaryCalculator.java
├── VehicleInheritanceSystem.java
├── LibraryManagementSystem.java
├── ToDoList.html
├── Calculator.html
├── TemperatureConverter.html
├── DigitalClock.html
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Web browser (Chrome, Firefox, Safari)
- Text editor or IDE

### For Java Projects:
```bash
cd "Day 2 Project"
javac [filename].java
java [classname]
```

### For Web Projects:
1. Open HTML file directly in browser
2. Or use VS Code Live Server extension

---

## ✅ Submission Checklist

### Java Projects:
- [ ] All 6 programs compile without errors
- [ ] OOP principles properly implemented
- [ ] Input validation working
- [ ] Output formatted correctly
- [ ] Code commented

### Web Projects:
- [ ] All 4 HTML files work in browser
- [ ] Forms validate input
- [ ] Calculations accurate
- [ ] UI responsive and styled

---

**Created**: December 2025  
**Level**: Intermediate  
**Duration**: 1 week  
**Instructor**: TCET MCA
