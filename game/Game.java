package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player p1;
    private Player p2;
    private Random random;
    private Map<Integer, String> countries;
    private boolean over;

    public Game(Player p1, Player p2) throws IOException {
        this.p1 = p1;
        this.p2 = p2;
        this.random = new Random();
        readFile("game/countries.txt");
        this.over = false;
    }

    private int rollDice() {
        return random.nextInt(6) + 1;
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

    private void risk(Player player) {
        int result = rollDice();
        System.out.println(player.getName() + "'s roll: " + result);
        if (result == 1) {
            System.out.println("+1 point");
            player.changePoints(1);
        } else if (result == 2 || result == 6) {
            System.out.println("you lost all points");
            player.changePoints(-4);
        } else if (result == 3) {
            System.out.println("YOU HIT THE JACKPOT. AUTO ADVANCE (and points don't reset)!");
            player.advanceLevel();
        } else if (result == 4) {
            GuessTheNumber.guessTheNumber(player);
        } else {
            System.out.println("-1 point");
            player.changePoints(-1);
        }
    }


    public void takeTurn(Player player) {
        Scanner in4 = new Scanner(System.in);
        int roll = rollDice();
        System.out.println(player.getName() + "'s roll: " + roll);

        if (roll == 1 || roll == 4) {
            GuessTheNumber.guessTheNumber(player);
        } else if (roll == 2) {
            Unscramble.unscramble(player, countries);
        } else if (roll == 3) {
            System.out.println("Sorry, you rolled a 3. Lose a point!");
            player.changePoints(-1);
        } else if (roll == 5) {
            System.out.println("Congrats! You get a free point!");
            player.changePoints(1);
        } else {
            System.out.println("Choose your game! Type 'U' for unscramble, 'R' to take a risk, or anything else to play high/low");
            String choice = in4.nextLine().toLowerCase();
            if (choice.equals("u")){
                Unscramble.unscramble(player, countries);
            } else if (choice.equals("r")){
                System.out.println("\nSo you decided to take a risk...");
                System.out.println("You're going to roll the die again");
                System.out.println("Here is the breakdown of what could happen: ");
                System.out.println("1: Gain a point");
                System.out.println("2: Lose all points");
                System.out.println("3: Advance a level");
                System.out.println("4: Play high/low");
                System.out.println("5: Lose a point");
                System.out.println("6: Lose all points");
                System.out.println("Press enter to see your result...");
                in4.nextLine();
                risk(player);
            } else {
                GuessTheNumber.guessTheNumber(player);
            }
        }

        if (player.getPoints() >= 3) {
            player.changePoints(-3);
            player.advanceLevel();
            System.out.println("Congrats " + player.getName() + ", you used 3 points to advance a level!");
            System.out.println("Here is current information for you: ");
            System.out.println(player);
            
        }
        if (player.getLevel() > 3) {
            over = true;
            
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
        System.out.println("\nPoints are awarded for various dice rolls");
        System.out.println("Some rolls give you points, some lose you points, and some result in mini games");
        System.out.println("Here are the rolls broken down (we are using a 6-sided die): ");

        System.out.println("\n1: High/Low (Either get a point or lose a point)");
        System.out.println("2: Country Unscramble (Either get a point or nothing happens)");
        System.out.println("3: Lose a point");
        System.out.println("4: Country Unscramble (Either get a point or nothing happens)");
        System.out.println("5: Free point");
        System.out.println("6: Pick your poison");
        System.out.println();
        System.out.println("Once you get 3 points, you trade in this points to move up a level");
        System.out.println("You cannot move down a level");
        System.out.println("First to reach and complete 3 levels wins!");
    }
}
