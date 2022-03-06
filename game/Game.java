package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player p1;
    private Player p2;
    private Random random;
    private Map<Integer, String> countries;
    private boolean over;
    private Scanner in;

    public Game(Player p1, Player p2) throws IOException {
        this.p1 = p1;
        this.p2 = p2;
        this.random = new Random();
        readFile("game/countries.txt");
        this.over = false;
        this.in = new Scanner(System.in);
    }

    private int rollDice() {
        return random.nextInt(6) + 1;
    }

    private void guessTheNumber(Player player) {
        
        int guesses = 3;
        int range = player.getLevel()*10;
        int answer = random.nextInt(range) + 1;
        System.out.print("High/Low Game! Guess the number between 1 and " + range + " in 3 or less guesses: ");
        int guess = in.nextInt();
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
                guess = in.nextInt();
            }
            guesses--;
        }
        if (failed) {
            System.out.println("Sorry, the number was " + answer);
            System.out.println(player.getName() + " lost a point!");
            player.changePoints(-1);
        }
        
    }

    private void readFile (String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader reader = new BufferedReader(fileReader);

        Map<Integer, String> countries = new HashMap<>();
        String line = reader.readLine();
        int counter = 0;
        while (line != null) {
            countries.put(counter, line.toLowerCase());
            line = reader.readLine();
            counter++;
        }

        this.countries = countries;
        reader.close();
    }

    private String scramble(String word) {
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters) {
            shuffled += letter;
        }
        return shuffled;
    }

    private void unscramble(Player player) {
        int index = random.nextInt(countries.size());
        while (!countries.containsKey(index)) {
            index = random.nextInt(countries.size());
        }
        String country = countries.remove(index + 1);
        String scrambled = scramble(country);

        System.out.println("Unscramble the letters to get the name of a country:");
        System.out.println(scrambled);
        String guess = in.nextLine().toLowerCase();
        if (guess.equals(country)) {
            System.out.println("That's right!");
            player.changePoints(1);
        } else {
            System.out.println("Sorry...the correct answer was " + country);
            player.changePoints(-1);
        }

    }

    public void takeTurn(Player player) {
        int roll = rollDice();
        System.out.println(player.getName() + "'s roll: " + roll);

        if (roll == 1 || roll == 4) {
            guessTheNumber(player);
        } else if (roll == 2) {
            unscramble(player);
        } else if (roll == 3) {
            System.out.println("Sorry, you rolled a 3. Lose a point!");
            player.changePoints(-1);
        } else if (roll == 5) {
            System.out.println("Congrats! You get a free point!");
            player.changePoints(1);
        } else {
            System.out.println("Choose your game! Type 'U' for unscramble, and anything else for high/low");
            if (in.nextLine().toLowerCase().equals("u")){
                unscramble(player);
            } else {
                guessTheNumber(player);
            }
        }

        if (player.getPoints() >= 3) {
            player.changePoints(-3);
            player.advanceLevel();
            System.out.println("Congrats " + player.getName() + ", you used 3 points to advance a level!");
            System.out.println("Here is current information for you: ");
            System.out.println(player);
            
        }
        if (player.getLevel() > 5) {
            over = true;
            in.close();
        }
    }

    public boolean isOver() {
        return over;
    }

    public Player getP1() {
        return p1;
    }
    
    public Player getP2() {
        return p2;
    }

    public void displayRolls() {
        System.out.println("\n1: High/Low");
        System.out.println("2: Country Unscramble");
        System.out.println("3: Lose a point");
        System.out.println("4: Country Unscramble");
        System.out.println("5: Free point");
        System.out.println("6: Pick your poison\n");
    }
}
