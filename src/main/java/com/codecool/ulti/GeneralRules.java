package com.codecool.ulti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static com.codecool.ulti.Player.players;

/**
 * Created by matyi on 2017.05.23..
 */
public class GeneralRules {
    private static String rules;

    public GeneralRules(String bid) {
        switch (bid) {
            case "pass":
                rules = "pass";
                buildRules();
                break;
            default:
                System.out.println("No sutch bid option");
                rules = "pass";
                break;
        }
    }

    public static String getRules() {
        return rules;
    }

    public void buildRules() {
        switch (rules) {
            case "pass":
                count2040();
                break;
            default:
                System.out.println("No sutch bid option");
                break;
        }
    }

    public boolean winCheck() {
        for (Player player : players) {
            if (rules.equals("pass")) {
                return winCheckPass();
            }
        }
        return false;
    }

    private boolean winCheckPass() {
        int defenderTotal = 0;
        int winTreshold = 0;

        for (Player player : players) {
            for (Card card : player.hits()) {
                player.setPoints(card.getGameValue());
            }
            if (player.getRole() == Player.Role.PLAYER) {
                defenderTotal += player.getPoints();
            } else if (player.getRole() == Player.Role.SOLOIST) {
                winTreshold = player.getPoints();
            }
        }
        if (winTreshold > defenderTotal) {
            return true;
        }
        return false;
    }

    private void count2040() {
        for (Player player : players) {
            Map<Integer, Card> cards = player.getHand();
            int acornsResult = 0;
            int leavesResult = 0;
            int bellsResult = 0;
            int heartsResult = 0;
            for (Card card : cards.values()) {
                if (card.getColor().equals(Deck.Color.ACORNS.toString()) && (card.getName().equals(Deck.Power.OVER.toString()))) {
                    acornsResult += 1;
                }
                if (card.getColor().equals(Deck.Color.ACORNS.toString()) && (card.getName().equals(Deck.Power.KING.toString()))) {
                    acornsResult += 1;
                }
                if (card.getColor().equals(Deck.Color.LEAVES.toString()) && (card.getName().equals(Deck.Power.OVER.toString()))) {
                    leavesResult += 1;
                }
                if (card.getColor().equals(Deck.Color.LEAVES.toString()) && (card.getName().equals(Deck.Power.KING.toString()))) {
                    leavesResult += 1;
                }
                if (card.getColor().equals(Deck.Color.BELLS.toString()) && (card.getName().equals(Deck.Power.OVER.toString()))) {
                    bellsResult += 1;
                }
                if (card.getColor().equals(Deck.Color.BELLS.toString()) && (card.getName().equals(Deck.Power.KING.toString()))) {
                    bellsResult += 1;
                }
                if (card.getColor().equals(Deck.Color.HEARTS.toString()) && (card.getName().equals(Deck.Power.OVER.toString()))) {
                    heartsResult += 1;
                }
                if (card.getColor().equals(Deck.Color.HEARTS.toString()) && (card.getName().equals(Deck.Power.KING.toString()))) {
                    heartsResult += 1;
                }
            }
            if (acornsResult == 2) {
                if (player.trump.equals(Deck.Color.ACORNS)) {
                    player.addPoints(40);
                } else {
                    player.addPoints(20);
                }
            }
            if (bellsResult == 2) {
                if (player.trump.equals(Deck.Color.BELLS)) {
                    player.addPoints(40);
                } else {
                    player.addPoints(20);
                }
            }
            if (leavesResult == 2) {
                if (player.trump.equals(Deck.Color.LEAVES)) {
                    player.addPoints(40);
                } else {
                    player.addPoints(20);
                }
            }
            if (heartsResult == 2) {
                if (player.trump.equals(Deck.Color.HEARTS)) {
                    player.addPoints(40);
                } else {
                    player.addPoints(20);
                }
            }
        }
    }
}
