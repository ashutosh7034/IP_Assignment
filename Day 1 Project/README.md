# Day 1 Project - Java Programming Fundamentals

## Overview
Day 1 projects focus on core Java programming concepts including object-oriented programming, data structures, and console-based applications. These assignments strengthen fundamental programming skills and understanding of Java syntax.

## 📚 Assignment Links
- **Assignment Details**: [Day 1 Assignment](https://shaileshsonareg.github.io/tcet/docs/assignment_day1.html)
- **MCQ Test**: [Day 1 Test](https://cquiz.ciacloud.in/take_test.php?test_id=MTEz)
- **Reference Code**: [GitHub - TCET Day 1](https://github.com/shaileshsonareg/tcet.git)

---

## 🚀 How to Run

### Quick Start - Project 1 (Jackpot Game)
```bash
# Navigate to Day 1 Project folder
cd "Day 1 Project"

# Compile the program
javac JackpotGame.java

# Run the game
java JackpotGame
```

### Quick Start - Project 2 (Employee Management)
```bash
# Navigate to Day 1 Project folder
cd "Day 1 Project"

# Compile the program
javac EmployeeManagementSystem.java

# Run the application
java EmployeeManagementSystem
```

### Prerequisites
- Java Development Kit (JDK) 8 or higher installed
- Command Prompt or Terminal access
- Text editor or IDE (optional but recommended)

### Verify Java Installation
```bash
java -version
javac -version
```

If Java is not installed, download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/).

---

## 📋 Project 1: Jackpot Number Guessing Game

### Objective
Create an interactive random number guessing game with multiple difficulty levels.

### Features
- **Easy Mode**: 20 attempts to guess the number
- **Medium Mode**: 10 attempts to guess the number
- **Hard Mode**: 5 attempts to guess the number
- Random number generation between 1-100
- Hints (too high/too low) after each guess
- Input validation
- Play again option
- Score tracking

### Technologies
- Java 8+
- Scanner for input
- Random class
- Loop control structures

### Key Concepts
- Random number generation
- User input validation
- Control flow (if-else, loops)
- Game logic implementation

### How to Run
```bash
javac JackpotGame.java
java JackpotGame
```

### Sample Gameplay
```
Welcome to Jackpot Guessing Game!
Select Difficulty:
========== Employee Management System ==========
1. View Employees
2. Add Employee
3. Update Employee
4. Delete Employee
0. Exit

Enter choice: 1

========== Employee List ==========
ID    Name           Department    Salary
1     John Doe       IT            50000.0
2     Jane Smith     HR            55000.0
3     Mike Johnson   Finance       60000.0
```

### Sample Operations

#### Adding an Employee
```
Enter Employee ID: 4
Enter Employee Name: Alice Brown
Enter Department: IT
Enter Salary: 52000
✓ Employee added successfully!
```

#### Updating an Employee
```
Enter Employee ID to update: 2
Enter New Name: Jane Doe
Enter New Department: HR
Enter New Salary: 56000
✓ Employee updated successfully!
```

#### Deleting an Employee
```
Enter Employee ID to delete: 3
✓ Employee deleted successfully!
```

### Requirements Checklist
- ✅ Use ArrayList<Employee> for storage
- ✅ Handle invalid input gracefully
- ✅ Maintain unique employee IDs
- ✅ Terminal-based user interface
- ✅ All CRUD operations working
- ✅ Input validation

---

## 🎯 Learning Outcomes

After completing Day 1 projects, you will understand:

1. **Object-Oriented Programming**
   - Classes and objects
   - Constructors and methods
   - Encapsulation
   - Data validation

2. **Collections**
   - ArrayList data structure
   - Adding, removing, searching elements
   - Iterating through collections

3. **Control Flow**
   - Loops (for, while, do-while)
   - Conditional statements (if-else, switch)
   - Game logic implementation

4. **User Input/Output**
   - Scanner class for input
   - Formatted output
   - Menu-driven interfaces

5. **Program Design**
   - Algorithm development
   - Code organization
   - Error handling

---

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Text editor or IDE (VS Code, IntelliJ IDEA, Eclipse)
- Command line/Terminal

### Steps
1. Open the project folder in your IDE
2. Review the assignment requirements via links above
3. Study the code structure
4. Run each project
5. Test all functionalities
6. Enhance features as needed

---

## 💡 Tips for Success

1. **Start Simple**: Understand basic logic before adding features
2. **Test Thoroughly**: Test all menu options and edge cases
3. **Handle Errors**: Add validation for invalid inputs
4. **Code Comments**: Add comments explaining logic
5. **Practice**: Try modifying programs to add new features

---

## 🔧 Common Issues & Solutions

### Issue: "public class X is public, should be declared in a file named X.java"
**Solution**: Ensure the class name matches the filename exactly (case-sensitive)

### Issue: NoSuchElementException
**Solution**: Add input validation before using Scanner

### Issue: ArrayList operations not working
**Solution**: Ensure proper syntax - `new ArrayList<>()`

### Issue: Menu keeps repeating
**Solution**: Check loop conditions and break statements

---

## 📚 Additional Resources

- [Java ArrayList Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
- [Scanner Class Tutorial](https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html)
- [Java Random Class](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)

---

## ✅ Submission Checklist

- [ ] Both projects compile without errors
- [ ] All menu options work correctly
- [ ] Input validation is implemented
- [ ] Code is properly commented
- [ ] README file is complete
- [ ] All CRUD operations functional
- [ ] Game logic working correctly
- [ ] Tested on different scenarios

---

## 📞 Support

For questions or issues:
1. Review the assignment links provided
2. Check the reference code on GitHub
3. Consult the Java documentation
4. Debug using print statements

---

**Created**: December 2025  
**Level**: Beginner  
**Duration**: 1 week  
**Instructor**: TCET MCA

## 📋 Project 2: Employee CRUD Console App

### Objective
Build a console-based employee management system using ArrayList for data persistence.

### Features
- **View Employees**: Display all employees in a formatted table
- **Add Employee**: Add new employee with validation
- **Update Employee**: Update existing employee details
- **Delete Employee**: Remove employee with confirmation
- Input validation and error handling
- Unique ID management
- Sample data included

### Employee Fields
- `int id` - Unique employee identifier
- `String name` - Employee name
- `String department` - Department name (IT, HR, Finance, etc.)
- `double salary` - Employee salary

### Technologies
- Java 8+
- ArrayList<Employee>
- Scanner for user input
- Switch-case menu system

### Key Concepts
- ArrayList data structure
- Object-oriented programming
- Encapsulation and data validation
- Menu-driven application design
- CRUD operations

### How to Run
```bash
javac EmployeeManagementSystem.java
java EmployeeManagementSystem
```

### Menu Options
```
1. View Employees
2. Add Employee
3. Update Employee
4. Delete Employee
0. Exit
```

## Requirements
- Java Development Kit (JDK) 8 or higher
- Command line terminal

## Notes
- Both programs use terminal-based interfaces
- Input validation is implemented for all user inputs
- Employee IDs are auto-generated and unique
- Sample employee data is pre-loaded in the Employee Management System
