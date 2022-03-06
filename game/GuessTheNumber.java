package game;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static Random random = new Random();
    
    public static void guessTheNumber(Player player) {
        
        int guesses = 3;
        int range = player.getLevel()*10;
        int answer = random.nextInt(range) + 1;
        
        System.out.print("High/Low Game! Guess the number between 1 and " + range + " in 3 or less guesses: ");
        Scanner in3 = new Scanner(System.in);
        int guess = in3.nextInt();
        boolean failed = true;
        while (guesses > 0){
            if (guess == answer) {
                System.out.println("That's it!");
                player.changePoints(1);
                failed = false;
                break;
            } else if (guess < answer) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }
            
            if (guesses > 1) {
                System.out.print("Guess again: ");
                guess = in3.nextInt();
            }
            guesses--;
        }
        if (failed) {
            System.out.println("Sorry, the number was " + answer);
            System.out.println(player.getName() + " lost a point!");
            player.changePoints(-1);
        }
        // in3.close();
    }
}
