import java.util.Random;
import java.util.Scanner;

class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int totalScore = 0;
        int roundsPlayed = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (true) {
            roundsPlayed++;
            System.out.println("\n--- Round " + roundsPlayed + " ---");

            int lowerBound = 1;
            int upperBound = 100;
            int maxAttempts = 7;
            int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("Guess the number between " + lowerBound + " and " + upperBound + ". You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {
                attempts++;
                System.out.print("Attempt " + attempts + "/" + maxAttempts + ": Enter your guess: ");

                int userGuess;
                try {
                    userGuess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    attempts--;
                    continue;
                }

                if (userGuess < lowerBound || userGuess > upperBound) {
                    System.out.println("Please guess a number within the range " + lowerBound + "-" + upperBound + ".");
                    attempts--;
                    continue;
                }

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number " + randomNumber + " in " + attempts + " attempts.");
                    totalScore += (maxAttempts - attempts + 1); // Score based on remaining attempts
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The correct number was " + randomNumber + ".");
            }

            System.out.println("Your score for this round: " + (guessedCorrectly ? (maxAttempts - attempts + 1) : 0));
            System.out.println("Total score after " + roundsPlayed + " round(s): " + totalScore);

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.nextLine().trim().toLowerCase();
            if (!playAgain.equals("yes") && !playAgain.equals("y")) {
                System.out.println("Thank you for playing! Goodbye.");
                break;
            }
        }

        scanner.close();
    }
}

