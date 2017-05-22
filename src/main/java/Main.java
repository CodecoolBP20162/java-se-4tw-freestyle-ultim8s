import com.codecool.ulti.Deck;
import com.codecool.ulti.Player;

import java.util.Scanner;

/**
 * Created by peter on 2017.05.22..
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Deck deck = new Deck();
        System.out.println("Please enter the first players name: ");
        Player player1 = new Player(scanner.nextLine());
        System.out.println("Please enter the second players name: ");
        Player player2 = new Player(scanner.nextLine());
        System.out.println("Please enter the third players name: ");
        Player player3 = new Player(scanner.nextLine());
        deck.deal();
        player1.printHand();
        System.out.println("\n\nPlayer "+player1.getName()+" please place your bid.");
        scanner.nextLine();
        player2.printHand();
        player3.printHand();
    }
}
