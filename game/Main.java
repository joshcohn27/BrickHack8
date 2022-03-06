package game;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the game!");
        System.out.print("Player 1, enter your name: ");
        Player player1 = new Player(in.nextLine());
        System.out.print("Player 2, enter your name: ");
        Player player2 = new Player(in.nextLine());

        Game game = new Game(player1, player2);
        game.displayRolls();
        
        while (!game.isOver()) {
            game.takeTurn(player1);
            System.out.println();
            game.takeTurn(player2);
            System.out.println();

            System.out.println("Current board: ");
            System.out.println(player1);
            System.out.println(player2);
            System.out.println();
        }

        if (player1.getLevel() == 6 && player2.getLevel() == 6){
            System.out.println("It's a tie!");
        } else if (player1.getLevel() == 6){
            System.out.println(player1 + " wins! Thanks for playing");
        } else {
            System.out.println(player2 + " wins! Thanks for playing");
        }
        
        in.close();

    }
}
