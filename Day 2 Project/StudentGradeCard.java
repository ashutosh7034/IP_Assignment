// Assignment 1: Student Grade Card Generator

public class StudentGradeCard {
    
    public static void main(String[] args) {
        // Create 2 students
        Student student1 = new Student(101, "Priya", 85, 78, 90);
        Student student2 = new Student(102, "Rahul", 65, 72, 68);
        
        // Print their grade cards
        System.out.println(student1);
        System.out.println(student2);
    }
}

// Student class
class Student {
    int rollNo;
    String name;
    int[] marks;  // Array to store marks of 3 subjects
    
    // Constructor - use 'this' keyword
    public Student(int rollNo, String name, int marks1, int marks2, int marks3) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = new int[3];
        this.marks[0] = marks1;
        this.marks[1] = marks2;
        this.marks[2] = marks3;
    }
    
    // Method to calculate grade based on average
    public char calculateGrade() {
        // Calculate average marks
        int total = marks[0] + marks[1] + marks[2];
        double average = total / 3.0;
        
        // Determine grade based on average
        if (average >= 80) {
            return 'A';
        } else if (average >= 60) {
            return 'B';
        } else if (average >= 40) {
            return 'C';
        } else {
            return 'F';
        }
    }
    
    // Override toString() to display student details
    @Override
    public String toString() {
        return "Student [rollNo=" + rollNo + ", name=" + name + 
               ", marks=[" + marks[0] + "," + marks[1] + "," + marks[2] + 
               "], grade=" + calculateGrade() + "]";
    }
}
