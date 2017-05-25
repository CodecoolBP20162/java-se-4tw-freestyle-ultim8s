package com.codecool.ulti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.codecool.ulti.Player.players;

/**
 * Created by matyi on 2017.05.23..
 */
public class GeneralRules {
    private static final Logger logger = LoggerFactory.getLogger(GeneralRules.class);
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

    public void winCheck() {
        for (Player player : players) {
            if (rules.equals("pass")) {
                winCheckPass();
            }
        }
    }

    private void winCheckPass() {
        int defenderTotal = 0;
        int winTreshold = 0;
        Player soloist = null;
        Player player1 = null;
        Player player2 = null;

        for (Player player : players) {
            for (Card card : player.hits()) {
                card.resetGameValue();
                if (card.getName().equals("TIZES") || card.getName().equals("ÁSZ")) {
                    player.addPoints(10);
                }
                ;
            }
            if (player.getRole() == Player.Role.PLAYER) {
                defenderTotal += player.getPoints();
                if (player1 == null) {
                    player1 = player;
                } else {
                    player2 = player;
                }
            } else if (player.getRole() == Player.Role.SOLOIST) {
                soloist = player;
                winTreshold = player.getPoints();
            }
        }
        if (winTreshold > defenderTotal) {
            System.out.println("SOLOIST: "+winTreshold+"\nDEFENDERS: "+defenderTotal+"\n\n"+ soloist.getName()+" WON!");
        } else {
            System.out.println("SOLOIST: "+winTreshold+"\nDEFENDERS: "+defenderTotal+"\n\n"+player1.getName()+" & "+player2.getName()+" WON!");
        };
    }


    private void count2040() {
        for (Player player : players) {
            Map<Integer, Card> cards = player.getHand();
            int acornsResult = 0;
            int leavesResult = 0;
            int bellsResult = 0;
            int heartsResult = 0;
            for (Card card : cards.values()) {
                if (card.getColor().equals(Deck.Color.MAKK.toString()) && (card.getName().equals(Deck.Power.FELSŐ.toString()))) {
                    acornsResult += 1;
                }
                if (card.getColor().equals(Deck.Color.MAKK.toString()) && (card.getName().equals(Deck.Power.KIRÁLY.toString()))) {
                    acornsResult += 1;
                }
                if (card.getColor().equals(Deck.Color.ZÖLD.toString()) && (card.getName().equals(Deck.Power.FELSŐ.toString()))) {
                    leavesResult += 1;
                }
                if (card.getColor().equals(Deck.Color.ZÖLD.toString()) && (card.getName().equals(Deck.Power.KIRÁLY.toString()))) {
                    leavesResult += 1;
                }
                if (card.getColor().equals(Deck.Color.TÖK.toString()) && (card.getName().equals(Deck.Power.FELSŐ.toString()))) {
                    bellsResult += 1;
                }
                if (card.getColor().equals(Deck.Color.TÖK.toString()) && (card.getName().equals(Deck.Power.KIRÁLY.toString()))) {
                    bellsResult += 1;
                }
                if (card.getColor().equals(Deck.Color.PIROS.toString()) && (card.getName().equals(Deck.Power.FELSŐ.toString()))) {
                    heartsResult += 1;
                }
                if (card.getColor().equals(Deck.Color.PIROS.toString()) && (card.getName().equals(Deck.Power.KIRÁLY.toString()))) {
                    heartsResult += 1;
                }
            }
            if (acornsResult == 2) {
                if (player.trump.equals(Deck.Color.MAKK)) {
                    player.addPoints(40);
                } else {
                    player.addPoints(20);
                }
            }
            if (bellsResult == 2) {
                if (player.trump.equals(Deck.Color.TÖK)) {
                    player.addPoints(40);
                } else {
                    player.addPoints(20);
                }
            }
            if (leavesResult == 2) {
                if (player.trump.equals(Deck.Color.ZÖLD)) {
                    player.addPoints(40);
                } else {
                    player.addPoints(20);
                }
            }
            if (heartsResult == 2) {
                if (player.trump.equals(Deck.Color.PIROS)) {
                    player.addPoints(40);
                } else {
                    player.addPoints(20);
                }
            }
        }
    }
}
