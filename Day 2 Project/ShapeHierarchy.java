// Assignment 4: Shape Hierarchy with Area Calculation

public class ShapeHierarchy {
    
    public static void main(String[] args) {
        // Create array of Shape references - polymorphism
        Shape[] shapes = new Shape[4];
        
        shapes[0] = new Rectangle(5, 10);
        shapes[1] = new Triangle(6, 8);
        shapes[2] = new Square(7);
        shapes[3] = new Rectangle(12, 8);
        
        // Print area of each shape using polymorphism
        for (int i = 0; i < shapes.length; i++) {
            System.out.print("Shape " + (i + 1) + " - ");
            System.out.printf("Area: %.2f\n", shapes[i].area());
        }
    }
}

// Abstract class Shape
abstract class Shape {
    // Abstract method - must be implemented by subclasses
    public abstract double area();
}

// Rectangle class
class Rectangle extends Shape {
    double length;
    double width;
    
    // Constructor
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    // Implement area method
    @Override
    public double area() {
        return length * width;
    }
}

// Triangle class
class Triangle extends Shape {
    double base;
    double height;
    
    // Constructor
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    // Implement area method
    // Triangle area = 0.5 × base × height
    @Override
    public double area() {
        return 0.5 * base * height;
    }
}

// Square class
class Square extends Shape {
    double side;
    
    // Constructor
    public Square(double side) {
        this.side = side;
    }
    
    // Implement area method
    @Override
    public double area() {
        return side * side;
    }
}
