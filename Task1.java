import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = (int) (Math.random() * 20);
        int maxAttempts = 5; // Set the maximum number of attempts
        int attempts = 0;
        int score = 20; // Set an initial score
        int highscore = 0;
        System.out.println("Welcome to the Guessing Game!");
        System.out.println("Try to guess the number between 0 and 19.");
        System.out.println("You have only 5 attempts");
       
        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();

            if (guess < 0 || guess > 19) {
                System.out.println("Please enter a number between 0 and 19.");
                continue;
            }

            attempts++;

            if (number > guess) {
                System.out.println("Too low! Try again.");
                System.out.println("Remaining attempts : "+(maxAttempts-attempts));
                score--;
                System.out.println("Your score is: " + score);
            } else if (number < guess) {
                System.out.println("Too high! Try again.");
                System.out.println("Remaining attempts : "+(maxAttempts-attempts));
                score--;
                System.out.println("Your score is: " + score);
            } else {
                System.out.println("Correct answer! You guessed it.");
                System.out.println("Score = "+score);

                System.out.print("Do you want to play again? (yes/no): ");
                String playAgain = scanner.next();

                if ("no".equalsIgnoreCase(playAgain)) {
                    System.out.println("Thanks for playing. Goodbye!");
                    break;
                } else {
                    number = (int) (Math.random() * 20);
                    attempts = 0;
                    System.out.println("New round. Start guessing...");
                }
            }
        }

        if (attempts == maxAttempts) {
            System.out.println("Sorry, you ran out of attempts. The correct number was: " + number);
            System.out.println("Your final score is: " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next();

            if ("no".equalsIgnoreCase(playAgain)) {
                System.out.println("Thanks for playing. Goodbye!");
            } else {
                main(args); // Restart the game
            }
        }

        scanner.close();
    }
}