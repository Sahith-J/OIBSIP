import java.util.Random;
import javax.swing.JOptionPane;

public class GuessTheNumber {
    public static void main(String[] args) {
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 5;
        int currentRound = 1;
        int score = 0;
        
        Random random = new Random();
        int generatedNumber = random.nextInt(maxRange - minRange + 1) + minRange;
        
        JOptionPane.showMessageDialog(null, "Welcome to Guess the Number game!");
        
        boolean playAgain = true;
        
        while (playAgain) {
            while (currentRound <= maxAttempts) {
                String userInput = JOptionPane.showInputDialog("Round " + currentRound + "/" + maxAttempts +
                        "\nGuess the number between " + minRange + " and " + maxRange + ":");
                
                int guessedNumber = Integer.parseInt(userInput);
                
                if (guessedNumber == generatedNumber) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number correctly.");
                    score += 10;
                    break;
                } else if (guessedNumber < generatedNumber) {
                    JOptionPane.showMessageDialog(null, "Wrong guess! The number is higher.");
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong guess! The number is lower.");
                }
                
                currentRound++;
            }
            
            if (currentRound > maxAttempts) {
                JOptionPane.showMessageDialog(null, "Game Over! You couldn't guess the number in " + maxAttempts + " attempts." +
                        "\nThe number was: " + generatedNumber);
            }
            
            JOptionPane.showMessageDialog(null, "Your score: " + score);
            
            int playAgainOption = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again",
                    JOptionPane.YES_NO_OPTION);
            
            if (playAgainOption == JOptionPane.YES_OPTION) {
                generatedNumber = random.nextInt(maxRange - minRange + 1) + minRange;
                currentRound = 1;
                score = 0;
            } else {
                playAgain = false;
            }
        }
        
        JOptionPane.showMessageDialog(null, "Thank you for playing Guess the Number! Goodbye!");
    }
}
