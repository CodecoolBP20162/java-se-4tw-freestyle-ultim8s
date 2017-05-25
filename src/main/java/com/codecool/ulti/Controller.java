package com.codecool.ulti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.codecool.ulti.CardHolder.trump;
import static com.codecool.ulti.Player.Role.NOT_SET;
import static com.codecool.ulti.Player.Role.PLAYER;
import static com.codecool.ulti.Player.Role.SOLOIST;

/**
 * Created by peter on 2017.05.23..
 */
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private static LinkedList<Player> players = new LinkedList<Player>();
    private static Scanner scanner = new Scanner(System.in);
    private int whosePlayerTurn = 0;
    private String bid = "";
    private Deck deck = new Deck();
    private Talon talon = new Talon();
    private Player currentPlayer;
    private GeneralRules generalRules;


    public void play() {
        initGame();
        //biding();
        logger.info("Predefined biding phase for testing done.");
        setTestBiding();
        resetPoints();
        handleTalon();
        playGame();
    }

    private void resetPoints() {
        for (Player player : players) {
            player.addPoints(0);
        }
    }

    private void initGame() {
        Welcome.printWelcome();
        getPlayerNames();
//        setPlayerNamesForTest();
        deck.deal();
        for (Player player : players) {
            System.out.println("\n\nPlayer "+player.getName()+"'s cards: ");
            player.printCards(player.getHand());
        }
    }

    private void setPlayerNamesForTest() {
        players.add(new Player("1) "));
        players.add(new Player("2) "));
        players.add(new Player("3) "));
    }

    private void setTestBiding() {
        players.get(0).setRole(SOLOIST);
        players.get(1).setRole(PLAYER);
        players.get(2).setRole(PLAYER);
        bid = "pass";
    }

    private void getPlayerNames() {
        System.out.println("Please enter the first players name: ");
        players.add(new Player(scanner.nextLine()));
        System.out.println("Please enter the second players name: ");
        players.add(new Player(scanner.nextLine()));
        System.out.println("Please enter the third players name: ");
        players.add(new Player(scanner.nextLine()));
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
//        System.out.println("\n\nCards in the Talon: ");
//        talon.printCards(talon.cards);
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
        System.out.println("\n\nPlayer "+currentPlayer.getName()+"'s cards.");
        currentPlayer.printCards(currentPlayer.getHand());
        putTalon(currentPlayer);
        System.out.println("\n\nCards in the Talon: ");
        talon.printCards(talon.cards);

    }

    private void putTalon(Player soloist) {
        System.out.println("\n\nPlayer " + soloist.getName() + " please enter the first card to put into the 'talon:'");
        String first = scanner.nextLine();
        System.out.println("\n\nPlayer " + soloist.getName() + " please enter the second card to put into the 'talon:'");
        String second = scanner.nextLine();
        talon.cards.put(1, soloist.hand.remove(Integer.parseInt(first)));
        talon.cards.put(2, soloist.hand.remove(Integer.parseInt(second)));
        soloist.orderHand();
        System.out.println("\n\nPlayer "+soloist.getName()+"'s cards: ");
        soloist.printCards(soloist.getHand());

    }

    private void playGame() {

        int cardToPlayNum = 0;
        CardHolder table = new CardHolder();
        ArrayList<Card> hits = new ArrayList<>();
        int turnStartingPlayerIndex = players.indexOf(currentPlayer);
        System.out.println("\n\nPlayer " + players.get(turnStartingPlayerIndex).getName() + ", please enter the trump color: ");
        CardHolder.setTrump(scanner.nextLine().toUpperCase());
        logger.debug("General rules seted with bid: {}", bid);
        generalRules = new GeneralRules(bid);

        showPlayersExtraPoints();

        for (int turn = 1; turn < 11; turn++) {
            for (int playerNumber = turnStartingPlayerIndex; playerNumber < turnStartingPlayerIndex + 3; playerNumber++) {
                logger.debug("This is {}. turn and player {} is playing", turn, players.get(playerNumber).getName());
                if (hits.isEmpty()) {
                    printEmptyTable();
                } else {
                    System.out.println("\n\nCards on the table: ");
                    table.printCards(table.placeHits(hits));
                }
                currentPlayer = players.get(playerNumber % 3);
                currentPlayer.orderHand();
                System.out.println("\n\nPlayer "+currentPlayer.getName()+"'s cards: ");
                currentPlayer.printCards(currentPlayer.getHand());
                boolean canPlay = false;
                while (!canPlay) {
                    System.out.println("\n\nPlayer " + currentPlayer.getName() + " please enter the card number you want to play:'");
                    cardToPlayNum = scanner.nextInt();
                    if (!hits.isEmpty()) {
                        canPlay = validateCardToPlay(hits, cardToPlayNum);
                    } else {
                        canPlay = true;
                    }
                }
                hits.add(currentPlayer.hand.remove(cardToPlayNum));
            }
            Player hitWinner = decideHitWinner(hits);
            logger.debug("Hit winning player: {}", hitWinner.getName());
            turnStartingPlayerIndex = players.indexOf(hitWinner);
            if (turn == 10) {
                logger.info("Added 10 point to player {} for the last hit.", hitWinner.getName());
                hitWinner.addPoints(10);
            }
            addHitedCardsToWinner(hits, hitWinner);
            System.out.println("Winner: " + hitWinner.getName());
            hits.clear();
        }
        generalRules.winCheck();
    }

    private void addHitedCardsToWinner(ArrayList<Card> hits, Player hitWinner) {
        for (Card card : hits) {
            hitWinner.addCardToHitedCards(card);
        }
    }

    private Player decideHitWinner(ArrayList<Card> hits) {
        Player hitWinner = null;
        for (Card hit : hits) {
            hit.resetGameValue();
            hit.setGameValue(hit.getAbsoluteValue());
            if (hit.getColor().equals(trump.name())) {
                hit.setGameValue(20);
            }
            if (hit.getColor().equals(hits.get(0).getColor())) {
                hit.setGameValue(10);
            }
        }
        if (hits.get(0).getGameValue() >= hits.get(1).getGameValue() && hits.get(0).getGameValue() >= hits.get(2).getGameValue()) {
            int playerIndex = players.indexOf(currentPlayer);
            hitWinner = players.get(((playerIndex - 2) + 3) % 3);
        }
        if (hits.get(1).getGameValue() > hits.get(0).getGameValue() && hits.get(1).getGameValue() > hits.get(2).getGameValue()) {
            int playerIndex = players.indexOf(currentPlayer);
            hitWinner = players.get(((playerIndex - 1) + 3) % 3);
        }
        if (hits.get(2).getGameValue() > hits.get(0).getGameValue() && hits.get(2).getGameValue() > hits.get(1).getGameValue()) {
            hitWinner = currentPlayer;
        }
        return hitWinner;
    }

    private void printEmptyTable() {
        System.out.println();
        System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _  ");
        System.out.println("|                        |");
        System.out.println("| No cards on the table  |");
        System.out.println("|_ _ _ _ _ _ _ _ _ _ _ _ |");
    }

    private void showPlayersExtraPoints() {
        for (Player player : players) {
            logger.debug("Player {} has {}", player.getName(), player.getPoints());
//            System.out.println(player.getPoints());
        }
    }

    private boolean validateCardToPlay(ArrayList<Card> hits, int cardToPlayNum) {
        boolean canPlay = false;
        Card bottomCard = hits.get(0);
        Card topCard = hits.get(hits.size() - 1);
        Card cardToPlay = currentPlayer.getHand().get(cardToPlayNum);
        if (!currentPlayer.hasColorInHand(bottomCard.getColor())) {
            if (cardToPlay.getColor().equals(trump.name())) {
                canPlay = true;
            }
            if (!currentPlayer.hasColorInHand(trump.name())) {
                canPlay = true;
            }
        }
        if (bottomCard.getColor().equals(cardToPlay.getColor())) {
            if (topCard.getColor().equals(bottomCard.getColor())) {
                if (topCard.getAbsoluteValue() < cardToPlay.getAbsoluteValue()) {
                    canPlay = true;
                } else if (!currentPlayer.hasBiggerInHand(topCard.getAbsoluteValue())) {
                    canPlay = true;
                }
            } else if (topCard.getColor().equals(trump.name()) || !topCard.getColor().equals(bottomCard.getColor())) {
                if (bottomCard.getAbsoluteValue() < cardToPlay.getAbsoluteValue()) {
                    canPlay = true;
                } else if (!currentPlayer.hasBiggerInHand(bottomCard.getAbsoluteValue())) {
                    canPlay = true;
                }
            }
        }
        return canPlay;
    }
}
