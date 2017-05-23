package com.codecool.ulti;

import java.util.LinkedList;
import java.util.Scanner;

import static com.codecool.ulti.Player.Role.NOT_SET;
import static com.codecool.ulti.Player.Role.PLAYER;
import static com.codecool.ulti.Player.Role.SOLOIST;

/**
 * Created by peter on 2017.05.23..
 */
public class Controller {
    private static LinkedList<Player> players = new LinkedList<Player>();
    private static Scanner scanner = new Scanner(System.in);
    int whoseTurn = 0;


    public void initGame() {
        Deck deck = new Deck();
        //getPlayerNames();
        setPlayerNamesForTest();
        deck.deal();
        for (Player player : players) {
            player.printHand();
        }
    }

    private void setPlayerNamesForTest() {
        players.add(new Player("1) "));
        players.add(new Player("2) "));
        players.add(new Player("3) "));
    }

    private void getPlayerNames() {
        System.out.println("Please enter the first players name: ");
        players.add(new Player(scanner.nextLine()));
        System.out.println("Please enter the second players name: ");
        players.add(new Player(scanner.nextLine()));
        System.out.println("Please enter the third players name: ");
        players.add(new Player(scanner.nextLine()));
    }

    public void biding() {
        String bid = "";
        int witchPlayerIsBidding = whoseTurn;
        int turnsWithoutBid = 0;
        while (turnsWithoutBid < 3) {
            System.out.println("\n\nPlayer " + players.get(witchPlayerIsBidding % 3).getName() + " please place your bid.");
            bid = scanner.nextLine();
            switch (bid) {
                case "pass" :
                    turnsWithoutBid++;
                    break;
                case "contra":
                    witchPlayerIsBidding = whoseTurn;
                    turnsWithoutBid=2;
                    break;
                case "recontra":
                    witchPlayerIsBidding = whoseTurn+1;
                    turnsWithoutBid=1;
                    break;
                case "subcontra":
                    witchPlayerIsBidding = whoseTurn;
                    turnsWithoutBid=2;
                    break;
                default:
                    turnsWithoutBid = 0;
                    players.get(witchPlayerIsBidding % 3).setRole(SOLOIST);
                    players.get((witchPlayerIsBidding+1) % 3).setRole(PLAYER);
                    players.get((witchPlayerIsBidding+2) % 3).setRole(PLAYER);
            }
            witchPlayerIsBidding++;
        }
        if (players.get(witchPlayerIsBidding).getRole().equals(NOT_SET)) {
            System.out.println("\n\nPlayer " + players.get(witchPlayerIsBidding % 3).getName() + " must bid! Please place your bid.");
            bid = scanner.nextLine();
        }
        System.out.println(bid);
        for(Player player:players) {
            System.out.println(player.getRole());
        }
    }


}
