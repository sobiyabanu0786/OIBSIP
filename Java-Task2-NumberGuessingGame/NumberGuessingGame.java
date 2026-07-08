import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int roundNumber = 1;
        boolean playAgain = true;

        System.out.println("=== Welcome to the Number Guessing Game! ===");

        while (playAgain) {
            System.out.println("\nSelect Difficulty: 1. Easy (1-50) | 2. Medium (1-100) | 3. Hard (1-200)");
            int choice = scanner.nextInt();
            int maxNumber = 100; 
            int maxAttempts = 7;

            if (choice == 1) { maxNumber = 50; maxAttempts = 10; }
            else if (choice == 3) { maxNumber = 200; maxAttempts = 5; }

            int secretNumber = random.nextInt(maxNumber) + 1;
            int attemptsTaken = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("I have chosen a number between 1 and " + maxNumber + ". Can you guess it?");

            while (attemptsTaken < maxAttempts && !hasGuessedCorrectly) {
                attemptsTaken++;
                System.out.print("Attempt " + attemptsTaken + "/" + maxAttempts + " - Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == secretNumber) {
                    System.out.println("Correct! You've guessed the number.");
                    hasGuessedCorrectly = true;
                } else if (userGuess > secretNumber) {
                    System.out.println("Too High!");
                } else {
                    System.out.println("Too Low!");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("You Lost! The correct number was: " + secretNumber);
            } else {
                System.out.println("Round " + roundNumber + " Summary: Guessed in " + attemptsTaken + " attempts.");
            }

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
            if (playAgain) roundNumber++;
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}