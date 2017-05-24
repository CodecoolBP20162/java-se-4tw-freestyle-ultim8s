package com.codecool.ulti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import static com.codecool.ulti.Player.Role.NOT_SET;
import static com.codecool.ulti.Player.Role.PLAYER;
import static com.codecool.ulti.Player.Role.SOLOIST;

/**
 * Created by peter on 2017.05.23..
 */
public class Controller {
    private static LinkedList<Player> players = new LinkedList<Player>();
    private static Scanner scanner = new Scanner(System.in);
    int whosePlayerTurn = 0;
    String bid = "";
    Deck deck = new Deck();
    Talon talon = new Talon();


    public void play() {
        initGame();
        //biding();
        setTestBiding();
        handleTalon();
        playGame();
    }

    private void initGame() {
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

    private void setTestBiding() {
        players.get(0).setRole(PLAYER);
        players.get(1).setRole(PLAYER);
        players.get(2).setRole(SOLOIST);
    }

    private void biding() {
        int witchPlayerIsBidding = whosePlayerTurn;
        int turnsWithoutBid = 0;
        while (turnsWithoutBid < 3) {
            Player currentBidingPlayer = players.get(witchPlayerIsBidding % 3);
            System.out.println("\n\nPlayer " + currentBidingPlayer.getName() + " please place your bid.");
            bid = scanner.nextLine();
            switch (bid) {
                case "pass" :
                    turnsWithoutBid++;
                    break;
                case "contra":
                    turnsWithoutBid=1;
                    break;
                case "recontra":
                    turnsWithoutBid=1;
                    break;
                case "subcontra":
                    turnsWithoutBid=1;
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
    }

    private void handleTalon() {
        talon.cards.put(1,deck.getTalon().get(1));
        talon.cards.put(2,deck.getTalon().get(2));
        talon.printHand();
        int i = 0;
        Player soloist = null;
        while (soloist==null) {
            if (players.get(i).getRole().equals(SOLOIST)) {
                soloist=players.get(i);
            }
            i++;
        }
        soloist.hand.put(11,talon.cards.remove(1));
        soloist.hand.put(12,talon.cards.remove(2));
        soloist.orderHand();
        soloist.printHand();
        putTalon(soloist);
        talon.printHand();

    }

    private void putTalon(Player soloist) {
        System.out.println("\n\nPlayer " + soloist.getName() + " please enter the first card to put into the 'talon:'");
        String first = scanner.nextLine();
        System.out.println("\n\nPlayer " + soloist.getName() + " please enter the second card to put into the 'talon:'");
        String second = scanner.nextLine();
        talon.cards.put(1,soloist.hand.remove(Integer.parseInt(first)));
        talon.cards.put(2,soloist.hand.remove(Integer.parseInt(second)));
        soloist.orderHand();
        soloist.printHand();

    }

    private void playGame() {
        ArrayList<Card> hits = new ArrayList<>();
        int gameStartingPlayerIndex = getGameStartingPlayer();
        for (int turn=1; turn < 11; turn ++) {
            for (int playerNumber=gameStartingPlayerIndex; playerNumber<gameStartingPlayerIndex+3; playerNumber++) {
                Player currentPlayer = players.get(playerNumber % 3);
                System.out.println("\n\nPlayer " + currentPlayer.getName() + " please enter the card number you want to play:'");
                String cardToPlay = scanner.nextLine();
                hits.add(currentPlayer.hand.remove(Integer.parseInt(cardToPlay)));
            }
            decideHitWinner(hits);
            hits.clear();
        }

    }

    private void decideHitWinner(ArrayList<Card> hits) {

    }

    private int getGameStartingPlayer() {
        int startingPlayerIndex=0;
        for (Player player:players) {
            if(player.getRole().equals(SOLOIST)) {
                startingPlayerIndex = players.indexOf(player);
            }
        }
        return startingPlayerIndex;
    }
}
