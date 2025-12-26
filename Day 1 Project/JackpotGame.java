import java.util.Random;
import java.util.Scanner;

public class JackpotGame {
    
    public static void main(String[] args) {
        // Create Scanner for user input
        Scanner scanner = new Scanner(System.in);
        // Create Random for generating random number
        Random random = new Random();
        
        // Display game title
        System.out.println("==================================");
        System.out.println("  JACKPOT NUMBER GUESSING GAME");
        System.out.println("==================================");
        System.out.println("Guess a number between 1 and 100");
        System.out.println();
        
        // Step 1: Select difficulty level
        System.out.println("Select Difficulty Level:");
        System.out.println("1. Easy (20 attempts)");
        System.out.println("2. Medium (10 attempts)");
        System.out.println("3. Difficult (5 attempts)");
        System.out.print("\nEnter your choice (1-3): ");
        
        int choice = scanner.nextInt();
        int maxAttempts = 0;
        
        // Set maximum attempts based on difficulty
        if (choice == 1) {
            maxAttempts = 20;
            System.out.println("\nDifficulty: EASY");
        } else if (choice == 2) {
            maxAttempts = 10;
            System.out.println("\nDifficulty: MEDIUM");
        } else if (choice == 3) {
            maxAttempts = 5;
            System.out.println("\nDifficulty: DIFFICULT");
        } else {
            System.out.println("\nInvalid choice! Setting to EASY.");
            maxAttempts = 20;
        }
        
        // Step 2: Generate random jackpot number between 1 and 100
        int jackpotNumber = random.nextInt(100) + 1;
        
        // Step 3: Start the game
        System.out.println("\nGame started! You have " + maxAttempts + " attempts.");
        System.out.println("Good luck!\n");
        
        int attempts = 0;  // Count how many attempts used
        boolean won = false;  // Track if player won
        
        // Step 4: Game loop - keep playing until attempts run out or player wins
        while (attempts < maxAttempts) {
            // Display current attempt number
            System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + " - Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;  // Increase attempt counter
            
            // Check if guess is correct
            if (guess == jackpotNumber) {
                won = true;
                System.out.println("\nJACKPOT! Congratulations!");
                System.out.println("You guessed the correct number: " + jackpotNumber);
                System.out.println("Attempts used: " + attempts + "/" + maxAttempts);
                break;  // Exit the loop because player won
            } 
            // If guess is too low
            else if (guess < jackpotNumber) {
                System.out.println("Too low! Try a higher number.");
                System.out.println("Remaining attempts: " + (maxAttempts - attempts));
                System.out.println();
            } 
            // If guess is too high
            else {
                System.out.println("Too high! Try a lower number.");
                System.out.println("Remaining attempts: " + (maxAttempts - attempts));
                System.out.println();
            }
        }
        
        // Step 5: Game over - check if player won or lost
        if (!won) {
            System.out.println("==========================================");
            System.out.println("Game Over! You've run out of attempts.");
            System.out.println("The jackpot number was: " + jackpotNumber);
            System.out.println("==========================================");
        }
        
        System.out.println("\nThank you for playing!");
        scanner.close();
    }
}
