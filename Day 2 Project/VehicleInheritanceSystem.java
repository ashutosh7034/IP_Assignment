// Assignment 9: Vehicle Inheritance System

public class VehicleInheritanceSystem {
    
    public static void main(String[] args) {
        // Create 2 cars
        Car car1 = new Car("Toyota", "Camry", 2023, 4);
        Car car2 = new Car("Honda", "Civic", 2024, 2);
        
        // Create 2 bikes
        Bike bike1 = new Bike("Yamaha", "R15", 2023, true);
        Bike bike2 = new Bike("Honda", "CBR", 2024, false);
        
        // Display information for all vehicles
        System.out.println("=== Cars ===");
        car1.displayInfo();
        System.out.println();
        car2.displayInfo();
        
        System.out.println("\n=== Bikes ===");
        bike1.displayInfo();
        System.out.println();
        bike2.displayInfo();
        
        // Demonstrate instanceof
        System.out.println("\n=== Type Checking ===");
        Vehicle[] vehicles = {car1, car2, bike1, bike2};
        
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] instanceof Car) {
                System.out.println("Vehicle " + (i+1) + " is a Car");
            } else if (vehicles[i] instanceof Bike) {
                System.out.println("Vehicle " + (i+1) + " is a Bike");
            }
        }
    }
}

// Base class Vehicle
class Vehicle {
    String brand;
    String model;
    int year;
    
    // Constructor
    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    
    // Method to display vehicle information
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
    }
}

// Car class extends Vehicle
class Car extends Vehicle {
    int numDoors;
    
    // Constructor - use super() to call parent constructor
    public Car(String brand, String model, int year, int numDoors) {
        super(brand, model, year);  // Call parent constructor
        this.numDoors = numDoors;
    }
    
    // Override displayInfo method
    @Override
    public void displayInfo() {
        System.out.println("--- Car Information ---");
        super.displayInfo();  // Call parent method
        System.out.println("Number of Doors: " + numDoors);
    }
}

// Bike class extends Vehicle
class Bike extends Vehicle {
    boolean hasCarrier;
    
    // Constructor - use super() to call parent constructor
    public Bike(String brand, String model, int year, boolean hasCarrier) {
        super(brand, model, year);  // Call parent constructor
        this.hasCarrier = hasCarrier;
    }
    
    // Override displayInfo method
    @Override
    public void displayInfo() {
        System.out.println("--- Bike Information ---");
        super.displayInfo();  // Call parent method
        System.out.println("Has Carrier: " + (hasCarrier ? "Yes" : "No"));
    }
}
