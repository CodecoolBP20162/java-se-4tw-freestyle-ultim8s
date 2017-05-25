package com.codecool.ulti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
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
    int whosePlayerTurn = 0;
    String bid = "";
    Deck deck = new Deck();
    Talon talon = new Talon();
    Player currentPlayer;
    WinCondition winCondition;


    public void play() {
        initGame();
        //biding();
        setTestBiding();
        resetPoints();
        handleTalon();
        playGame();
    }

    private void resetPoints() {
        for (Player player:players) {
            player.setPoints(0);
        }
    }

    private void initGame() {
        //getPlayerNames();
        setPlayerNamesForTest();
        deck.deal();
        for (Player player : players) {
            player.printCards(player.getHand());
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
        players.get(0).setRole(SOLOIST);
        players.get(1).setRole(PLAYER);
        players.get(2).setRole(PLAYER);
        bid = "pass";
    }

    private void biding() {
        int witchPlayerIsBidding = whosePlayerTurn;
        int turnsWithoutBid = 0;
        while (turnsWithoutBid < 3) {
            Player currentBidingPlayer = players.get(witchPlayerIsBidding % 3);
            System.out.println("\n\nPlayer " + currentBidingPlayer.getName() + " please place your bid.");
            bid = scanner.nextLine();
            switch (bid) {
                case "pass":
                    turnsWithoutBid++;
                    break;
                case "contra":
                    turnsWithoutBid = 1;
                    break;
                case "recontra":
                    turnsWithoutBid = 1;
                    break;
                case "subcontra":
                    turnsWithoutBid = 1;
                    break;
                default:
                    turnsWithoutBid = 0;
                    players.get(witchPlayerIsBidding % 3).setRole(SOLOIST);
                    players.get((witchPlayerIsBidding + 1) % 3).setRole(PLAYER);
                    players.get((witchPlayerIsBidding + 2) % 3).setRole(PLAYER);
            }
            witchPlayerIsBidding++;
        }
        if (players.get(witchPlayerIsBidding).getRole().equals(NOT_SET)) {
            System.out.println("\n\nPlayer " + players.get(witchPlayerIsBidding % 3).getName() + " must bid! Please place your bid.");
            bid = scanner.nextLine();
        }
    }


    private void handleTalon() {
        talon.cards.put(1, deck.getTalon().get(1));
        talon.cards.put(2, deck.getTalon().get(2));
        talon.printCards(talon.cards);
        int i = 0;
        Player soloist = null;
        while (soloist == null) {
            if (players.get(i).getRole().equals(SOLOIST)) {
                currentPlayer = players.get(i);
                soloist = currentPlayer;
            }
            i++;
        }
        currentPlayer.hand.put(11, talon.cards.remove(1));
        currentPlayer.hand.put(12, talon.cards.remove(2));
        currentPlayer.orderHand();
        currentPlayer.printCards(currentPlayer.getHand());
        putTalon(currentPlayer);
        talon.printCards(talon.cards);

    }

    private void putTalon(Player soloist) {
        System.out.println("\n\nPlayer " + soloist.getName() + " please enter the first card to put into the 'talon:'");
        int first = scanner.nextInt();
        System.out.println("\n\nPlayer " + soloist.getName() + " please enter the second card to put into the 'talon:'");
        int second = scanner.nextInt();
        talon.cards.put(1, soloist.hand.remove(first));
        talon.cards.put(2, soloist.hand.remove(second));
        soloist.orderHand();
        soloist.printCards(soloist.getHand());

    }

    private void count2040() {
        for (Player player:players) {
            Map<Integer, Card> cards = player.getHand();
            int acornsResult = 0;
            int leavesResult = 0;
            int bellsResult = 0;
            int heartsResult = 0;
            for (Card card : cards.values()) {
                if (card.getColor().equals(Deck.Color.ACORNS.toString()) && (card.getName().equals(Deck.Power.OVER.toString()))) {
                    acornsResult = acornsResult + 1;
                }
                if (card.getColor().equals(Deck.Color.ACORNS.toString()) && (card.getName().equals(Deck.Power.KING.toString()))) {
                    acornsResult = acornsResult + 1;
                }
                if (card.getColor().equals(Deck.Color.LEAVES.toString()) && (card.getName().equals(Deck.Power.OVER.toString()))) {
                    leavesResult = leavesResult + 1;
                }
                if (card.getColor().equals(Deck.Color.LEAVES.toString()) && (card.getName().equals(Deck.Power.KING.toString()))) {
                    leavesResult = leavesResult + 1;
                }
                if (card.getColor().equals(Deck.Color.BELLS.toString()) && (card.getName().equals(Deck.Power.OVER.toString()))) {
                    bellsResult = bellsResult + 1;
                }
                if (card.getColor().equals(Deck.Color.BELLS.toString()) && (card.getName().equals(Deck.Power.KING.toString()))) {
                    bellsResult = bellsResult + 1;
                }
                if (card.getColor().equals(Deck.Color.HEARTS.toString()) && (card.getName().equals(Deck.Power.OVER.toString()))) {
                    heartsResult = heartsResult + 1;
                }
                if (card.getColor().equals(Deck.Color.HEARTS.toString()) && (card.getName().equals(Deck.Power.KING.toString()))) {
                    heartsResult = heartsResult + 1;
                }
            }
            if (acornsResult == 2) {
                if (player.trump.equals(Deck.Color.ACORNS)){
                    player.setPoints(player.getPoints()+40);
                } else {
                    player.setPoints(player.getPoints()+20);
                }
            }
            if (bellsResult == 2) {
                if (player.trump.equals(Deck.Color.BELLS)){
                    player.setPoints(player.getPoints()+40);
                } else {
                    player.setPoints(player.getPoints()+20);
                }
            }
            if (leavesResult == 2) {
                if (player.trump.equals(Deck.Color.LEAVES)){
                    player.setPoints(player.getPoints()+40);
                } else {
                    player.setPoints(player.getPoints()+20);
                }
            }
            if (heartsResult == 2) {
                if (player.trump.equals(Deck.Color.HEARTS)){
                    player.setPoints(player.getPoints()+40);
                } else {
                    player.setPoints(player.getPoints()+20);
                }
            }
        }
    }


    private void playGame() {
        int cardToPlay = 0;
        String trumpColor = "";
        CardHolder table = new CardHolder();
        ArrayList<Card> hits = new ArrayList<>();
        int turnStartingPlayerIndex = players.indexOf(currentPlayer);
        System.out.println("\n\nPlayer " + players.get(turnStartingPlayerIndex).getName() + ", please enter the trump color: ");
        scanner.nextLine();
        trumpColor = scanner.nextLine();
        CardHolder.setTrump(trumpColor.toUpperCase());
        winCondition = new WinCondition(bid);
        count2040();
        for (int turn = 1; turn < 11; turn++) {
            for (int playerNumber = turnStartingPlayerIndex; playerNumber < turnStartingPlayerIndex + 3; playerNumber++) {
                if (hits.isEmpty()) {
                    System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _  ");
                    System.out.println("|                        |");
                    System.out.println("| No cards on the table  |");
                    System.out.println("|_ _ _ _ _ _ _ _ _ _ _ _ |");
                } else {
                    table.printCards(table.placeHits(hits));
                }
                currentPlayer = players.get(playerNumber % 3);
                currentPlayer.orderHand();
                currentPlayer.printCards(currentPlayer.getHand());
                currentPlayer.setPoints(hits);
                boolean canPlay = false;
                while (!canPlay) {
                    System.out.println("\n\nPlayer " + currentPlayer.getName() + " please enter the card number you want to play:'");
                    cardToPlay = scanner.nextInt();
                    if (!hits.isEmpty()) {
                        if (hits.size() == 1) {
                            if (hits.get(0).getGameValue() < currentPlayer.getHand().get(cardToPlay).getGameValue()) {
                                canPlay = true;
                            }
                        }
                        if (hits.size() == 2) {
                            if (hits.get(1).getGameValue() < currentPlayer.getHand().get(cardToPlay).getGameValue()) {
                                canPlay = true;
                            }
                        }
                    }
                    if (hits.isEmpty()) {
                        canPlay = true;
                    }
                }
                hits.add(currentPlayer.hand.remove(cardToPlay));
            }
            Player hitWinner = decideHitWinner(hits);
            addHitedCardsToWinner(hits,hitWinner);
            hits.clear();
        }
        winCondition.winCheck();
    }

    private void addHitedCardsToWinner(ArrayList<Card> hits,Player hitWinner) {
        for (Card card:hits) {
            hitWinner.addCardToHitedCards(card);
        }
    }

    private Player decideHitWinner(ArrayList<Card> hits) {
        Player hitWinner = null;
        if (hits.get(0).getGameValue()>=hits.get(1).getGameValue() && hits.get(0).getGameValue()>=hits.get(2).getGameValue()) {
            int playerIndex = players.indexOf(currentPlayer);
            hitWinner = players.get((playerIndex-2)+3 % 3);
        }
        if (hits.get(1).getGameValue()>hits.get(0).getGameValue() && hits.get(1).getGameValue()>hits.get(2).getGameValue()) {
            int playerIndex = players.indexOf(currentPlayer);
            hitWinner = players.get((playerIndex-1)+3 % 3);
        }
        if (hits.get(2).getGameValue()>hits.get(0).getGameValue() && hits.get(2).getGameValue()>hits.get(1).getGameValue()) {
            hitWinner = currentPlayer;
        }

        return hitWinner;
    }
}
