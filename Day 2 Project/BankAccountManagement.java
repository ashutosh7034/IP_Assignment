// Assignment 3: Bank Account Management System

public class BankAccountManagement {
    
    public static void main(String[] args) {
        // Create 2 bank accounts
        BankAccount account1 = new BankAccount("ACC001", "Priya Sharma", 5000.00);
        BankAccount account2 = new BankAccount("ACC002", "Rahul Kumar", 3000.00);
        
        // Perform transactions on account 1
        System.out.println("=== Account 1 Transactions ===");
        account1.checkBalance();
        account1.deposit(2000);
        account1.withdraw(1500);
        account1.withdraw(7000); // This should fail
        account1.checkBalance();
        
        System.out.println("\n=== Account 2 Transactions ===");
        account2.checkBalance();
        account2.deposit(5000);
        account2.withdraw(2000);
        account2.checkBalance();
    }
}

// BankAccount class
class BankAccount {
    String accountNumber;
    String accountHolder;
    private double balance;  // Private for encapsulation
    
    // Constructor
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }
    
    // Getter method for balance
    public double getBalance() {
        return balance;
    }
    
    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.println("Deposited: $" + amount);
            System.out.println("Transaction successful!");
            System.out.println("New Balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
    
    // Method to withdraw money - returns boolean for success/failure
    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Withdrawal Failed: Insufficient balance!");
            System.out.println("Current Balance: $" + balance);
            return false;
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount!");
            return false;
        } else {
            balance = balance - amount;
            System.out.println("Withdrawn: $" + amount);
            System.out.println("Transaction successful!");
            System.out.println("New Balance: $" + balance);
            return true;
        }
    }
    
    // Method to check balance
    public void checkBalance() {
        System.out.println("Account: " + accountNumber);
        System.out.println("Holder: " + accountHolder);
        System.out.println("Current Balance: $" + balance);
    }
}
