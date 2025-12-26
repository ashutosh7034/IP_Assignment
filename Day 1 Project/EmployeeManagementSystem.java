import java.util.ArrayList;
import java.util.Scanner;

// Employee class to store employee information
class Employee {
    // Employee properties (data members)
    int id;
    String name;
    String department;
    double salary;

    // Constructor - used to create new Employee object
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Method to display employee details
    public void displayEmployee() {
        System.out.println("ID: " + id + " | Name: " + name + " | Department: " + department + " | Salary: $" + salary);
    }
}

public class EmployeeManagementSystem {
    
    public static void main(String[] args) {
        // Create ArrayList to store employees
        ArrayList<Employee> employees = new ArrayList<Employee>();
        
        // Create Scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Variable to track next employee ID (auto-increment)
        int nextId = 1;
        
        // Add some sample employees
        employees.add(new Employee(nextId++, "John Doe", "IT", 75000.00));
        employees.add(new Employee(nextId++, "Jane Smith", "HR", 65000.00));
        employees.add(new Employee(nextId++, "Mike Johnson", "Finance", 80000.00));
        
        // Main program loop
        boolean exit = false;
        
        while (!exit) {
            // Display menu
            System.out.println("\n========================================");
            System.out.println("   EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. View Employees");
            System.out.println("2. Add Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("0. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character
            
            // Process user choice using if-else
            if (choice == 1) {
                // View all employees
                System.out.println("\n========================================");
                System.out.println("           EMPLOYEE LIST");
                System.out.println("========================================");
                
                if (employees.size() == 0) {
                    System.out.println("No employees found.");
                } else {
                    // Loop through all employees and display them
                    for (int i = 0; i < employees.size(); i++) {
                        employees.get(i).displayEmployee();
                    }
                    System.out.println("\nTotal Employees: " + employees.size());
                }
                
            } else if (choice == 2) {
                // Add new employee
                System.out.println("\n========================================");
                System.out.println("           ADD NEW EMPLOYEE");
                System.out.println("========================================");
                
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                
                System.out.print("Enter Department: ");
                String department = scanner.nextLine();
                
                System.out.print("Enter Salary: ");
                double salary = scanner.nextDouble();
                scanner.nextLine(); // Clear newline
                
                // Create new employee and add to ArrayList
                Employee newEmployee = new Employee(nextId++, name, department, salary);
                employees.add(newEmployee);
                
                System.out.println("\nEmployee added successfully!");
                newEmployee.displayEmployee();
                
            } else if (choice == 3) {
                // Update employee
                System.out.println("\n========================================");
                System.out.println("          UPDATE EMPLOYEE");
                System.out.println("========================================");
                
                System.out.print("Enter Employee ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Clear newline
                
                // Search for employee by ID
                Employee foundEmployee = null;
                for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i).id == id) {
                        foundEmployee = employees.get(i);
                        break;
                    }
                }
                
                if (foundEmployee == null) {
                    System.out.println("Employee with ID " + id + " not found!");
                } else {
                    System.out.println("\nCurrent Employee Details:");
                    foundEmployee.displayEmployee();
                    
                    System.out.println("\nEnter new details:");
                    
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    foundEmployee.name = name;
                    
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    foundEmployee.department = department;
                    
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    scanner.nextLine(); // Clear newline
                    foundEmployee.salary = salary;
                    
                    System.out.println("\nEmployee updated successfully!");
                    foundEmployee.displayEmployee();
                }
                
            } else if (choice == 4) {
                // Delete employee
                System.out.println("\n========================================");
                System.out.println("          DELETE EMPLOYEE");
                System.out.println("========================================");
                
                System.out.print("Enter Employee ID to delete: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Clear newline
                
                // Search for employee by ID
                Employee foundEmployee = null;
                int foundIndex = -1;
                for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i).id == id) {
                        foundEmployee = employees.get(i);
                        foundIndex = i;
                        break;
                    }
                }
                
                if (foundEmployee == null) {
                    System.out.println("Employee with ID " + id + " not found!");
                } else {
                    System.out.println("\nEmployee to be deleted:");
                    foundEmployee.displayEmployee();
                    
                    System.out.print("\nAre you sure? (yes/no): ");
                    String confirm = scanner.nextLine();
                    
                    if (confirm.equalsIgnoreCase("yes")) {
                        employees.remove(foundIndex);
                        System.out.println("\nEmployee deleted successfully!");
                    } else {
                        System.out.println("\nDeletion cancelled.");
                    }
                }
                
            } else if (choice == 0) {
                // Exit program
                exit = true;
                System.out.println("\n========================================");
                System.out.println("Thank you for using Employee Management System!");
                System.out.println("Goodbye!");
                System.out.println("========================================");
                
            } else {
                System.out.println("\nInvalid choice! Please select a valid option.");
            }
            
            // Pause before showing menu again
            if (!exit) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
}
