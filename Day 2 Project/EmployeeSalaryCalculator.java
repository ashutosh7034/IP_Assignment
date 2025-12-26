// Assignment 7: Employee Salary Calculator

public class EmployeeSalaryCalculator {
    
    public static void main(String[] args) {
        // Create array of 3 employees
        Employee[] employees = new Employee[3];
        
        employees[0] = new Employee(101, "Amit Sharma", 50000);
        employees[1] = new Employee(102, "Priya Singh", 60000);
        employees[2] = new Employee(103, "Rahul Verma", 45000);
        
        // Display payslip for each employee
        for (int i = 0; i < employees.length; i++) {
            employees[i].displayPayslip();
            System.out.println();
        }
    }
}

// Employee class
class Employee {
    int empId;
    String name;
    double basicSalary;
    
    // Constructor
    public Employee(int empId, String name, double basicSalary) {
        this.empId = empId;
        this.name = name;
        this.basicSalary = basicSalary;
    }
    
    // Method to calculate HRA (20% of basic salary)
    public double calculateHRA() {
        return basicSalary * 0.20;
    }
    
    // Method to calculate DA (10% of basic salary)
    public double calculateDA() {
        return basicSalary * 0.10;
    }
    
    // Method to calculate gross salary
    public double calculateGrossSalary() {
        return basicSalary + calculateHRA() + calculateDA();
    }
    
    // Method to calculate tax (5% of gross salary)
    public double calculateTax() {
        return calculateGrossSalary() * 0.05;
    }
    
    // Method to calculate net salary
    public double calculateNetSalary() {
        return calculateGrossSalary() - calculateTax();
    }
    
    // Method to display payslip
    public void displayPayslip() {
        System.out.println("========================================");
        System.out.println("           EMPLOYEE PAYSLIP");
        System.out.println("========================================");
        System.out.println("Employee ID   : " + empId);
        System.out.println("Employee Name : " + name);
        System.out.println("----------------------------------------");
        System.out.printf("Basic Salary  : $%.2f\n", basicSalary);
        System.out.printf("HRA (20%%)     : $%.2f\n", calculateHRA());
        System.out.printf("DA (10%%)      : $%.2f\n", calculateDA());
        System.out.println("----------------------------------------");
        System.out.printf("Gross Salary  : $%.2f\n", calculateGrossSalary());
        System.out.printf("Tax (5%%)      : $%.2f\n", calculateTax());
        System.out.println("----------------------------------------");
        System.out.printf("Net Salary    : $%.2f\n", calculateNetSalary());
        System.out.println("========================================");
    }
}
