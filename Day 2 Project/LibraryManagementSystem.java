// Assignment 10: Library Book Management System

import java.util.ArrayList;
import java.util.Scanner;

// Interface for library operations
interface LibraryOperations {
    void issueBook(int id);
    void returnBook(int id);
    void showStatus();
}

// Book class
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;
    
    // Constructor
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;  // Initially not issued
    }
    
    // Method to display book details
    public void displayBook() {
        String status = isIssued ? "Issued" : "Available";
        System.out.println("ID: " + id + " | Title: " + title + " | Author: " + author + " | Status: " + status);
    }
}

// Library class implementing LibraryOperations interface
class Library implements LibraryOperations {
    ArrayList<Book> books;
    
    // Constructor
    public Library() {
        books = new ArrayList<Book>();
    }
    
    // Method to add book
    public void addBook(int id, String title, String author) {
        // Check if ID already exists
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).id == id) {
                System.out.println("Error: Book with ID " + id + " already exists!");
                return;
            }
        }
        
        Book newBook = new Book(id, title, author);
        books.add(newBook);
        System.out.println("Book added successfully!");
    }
    
    // Method to issue book
    @Override
    public void issueBook(int id) {
        // Search for book
        Book book = findBookById(id);
        
        if (book == null) {
            System.out.println("Error: Book with ID " + id + " not found!");
        } else if (book.isIssued) {
            System.out.println("Error: Book is already issued!");
        } else {
            book.isIssued = true;
            System.out.println("Book issued successfully!");
            book.displayBook();
        }
    }
    
    // Method to return book
    @Override
    public void returnBook(int id) {
        // Search for book
        Book book = findBookById(id);
        
        if (book == null) {
            System.out.println("Error: Book with ID " + id + " not found!");
        } else if (!book.isIssued) {
            System.out.println("Error: Book is not issued!");
        } else {
            book.isIssued = false;
            System.out.println("Book returned successfully!");
            book.displayBook();
        }
    }
    
    // Method to show all books
    @Override
    public void showStatus() {
        System.out.println("\n========================================");
        System.out.println("           ALL BOOKS");
        System.out.println("========================================");
        
        if (books.size() == 0) {
            System.out.println("No books in library.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                books.get(i).displayBook();
            }
            System.out.println("\nTotal Books: " + books.size());
        }
    }
    
    // Helper method to find book by ID
    private Book findBookById(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).id == id) {
                return books.get(i);
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        
        // Add some sample books
        library.addBook(1, "Java Programming", "James Gosling");
        library.addBook(2, "Python Basics", "Guido van Rossum");
        library.addBook(3, "Web Development", "Tim Berners-Lee");
        
        boolean exit = false;
        
        while (!exit) {
            // Display menu
            System.out.println("\n========================================");
            System.out.println("   LIBRARY MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Show All Books");
            System.out.println("0. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline
            
            // Process choice using switch-case
            switch (choice) {
                case 1:
                    // Add Book
                    System.out.println("\n--- Add New Book ---");
                    System.out.print("Enter Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Clear newline
                    
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    
                    library.addBook(id, title, author);
                    break;
                    
                case 2:
                    // Issue Book
                    System.out.println("\n--- Issue Book ---");
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = scanner.nextInt();
                    library.issueBook(issueId);
                    break;
                    
                case 3:
                    // Return Book
                    System.out.println("\n--- Return Book ---");
                    System.out.print("Enter Book ID to return: ");
                    int returnId = scanner.nextInt();
                    library.returnBook(returnId);
                    break;
                    
                case 4:
                    // Show All Books
                    library.showStatus();
                    break;
                    
                case 0:
                    // Exit
                    exit = true;
                    System.out.println("\n========================================");
                    System.out.println("Thank you for using Library System!");
                    System.out.println("========================================");
                    break;
                    
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
            
            // Pause before showing menu again
            if (!exit && choice >= 1 && choice <= 4) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
}
