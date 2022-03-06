package game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Unscramble {
    public static Random random = new Random();
    
    private static String scramble(String word) {
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters) {
            shuffled += letter;
        }
        return shuffled;
    }

    public static void unscramble(Player player, Map<Integer, String> countries) {
        Scanner in2 = new Scanner(System.in);
        int index = random.nextInt(countries.size());
        while (!countries.containsKey(index)) {
            index = random.nextInt(countries.size());
        }
        String country = countries.remove(index + 1);
        String scrambled = scramble(country);

        System.out.println("Unscramble the letters to get the name of a country:");
        System.out.println(scrambled);
        String guess = in2.nextLine();
        if (guess.toLowerCase().equals(country)) {
            System.out.println("That's right!");
            player.changePoints(1);
        } else {
            System.out.println("Sorry...the correct answer was " + country);
        }

    }
}
