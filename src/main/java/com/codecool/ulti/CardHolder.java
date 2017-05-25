package com.codecool.ulti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by matyi on 2017.05.22..
 */
public class CardHolder {

    public static Deck.Color trump = null;
    public Map<Integer, Card> hand = new HashMap<Integer, Card>();
    private Map<Integer, Card> printer = new HashMap<Integer, Card>();

    /**
     * Prints the hand of the player with super fancy graphics.
     */
    public void printCards(Map<Integer, Card> cards) {
        if (cards.size() > 0) {
            int filler = 0;

            System.out.println();
            for (Map.Entry<Integer, Card> players : cards.entrySet()) {
                System.out.print(players.getKey() + "[]        ");
            }
            System.out.println();
            for (Card card : cards.values()) {
                System.out.print(" _ _ _ _ _ ");
            }
            System.out.println();
            for (Card card : cards.values()) {
                System.out.print("|         |");
            }
            System.out.println();
            for (Card card : cards.values()) {
                switch (card.getName().length()) {
                    case 1:
                        filler = 5;
                        break;
                    case 2:
                        filler = 5;
                        break;
                    case 3:
                        filler = 4;
                        break;
                    case 4:
                        filler = 4;
                        break;
                    case 5:
                        filler = 3;
                        break;
                    case 6:
                        filler = 3;
                        break;
                    case 7:
                        filler = 2;
                        break;
                }
                fill(filler, card.getName());
            }
            System.out.println();
            for (Card card : cards.values()) {
                System.out.print("|         |");
            }
            System.out.println();
            for (Card card : cards.values()) {
                System.out.print("|    of   |");
            }
            System.out.println();
            for (Card card : cards.values()) {
                System.out.print("|         |");
            }
            System.out.println();
            for (Card card : cards.values()) {
                switch (card.getColor().length()) {
                    case 5:
                        filler = 3;
                        break;
                    case 6:
                        filler = 3;
                        break;
                    case 7:
                        filler = 2;
                        break;
                }
                fill(filler, card.getColor());
            }
            System.out.println();
            for (Card card : cards.values()) {
                System.out.print("| _ _ _ _ |");
            }
            System.out.println();
            for (Card card : cards.values()) {
                System.out.print(card.getAbsoluteValue() + "|" + card.getGameValue() + "||");
            }
        }

    }


    /**
     * Method used for whitespace counting.
     *
     * @param filler The amount of whitespace to be left before the string.
     * @param string The name or color string for the card.
     */
    private void fill(int filler, String string) {
        System.out.print("|");
        for (int i = 0; i < filler - 1; i++) {
            System.out.print(" ");
        }
        System.out.print(string);
        for (int i = 0; i < 10 - filler - string.length(); i++) {
            System.out.print(" ");
        }
        System.out.print("|");
    }

    public static void setTrump(String string) {
        for (Deck.Color color : Deck.Color.values()) {
            if (color.name().equals(string)) {
                trump = color;
            }
        }
    }

    private void setPoinsPass(ArrayList<Card> hits) {
        for (Card card : hand.values()) {
            card.setGameValue(0);
            if (card.getColor().equals(trump.name())) {
                card.setGameValue(card.getAbsoluteValue() + 20);
            }
            if (!hits.isEmpty() && card.getColor().equals(hits.get(0).getColor())) {
                card.setGameValue(card.getAbsoluteValue() + 10);
            } else {
                card.setGameValue(card.getAbsoluteValue());
            }
        }
    }

    public void setPoints(ArrayList<Card> hits) {
        String winCondition = GeneralRules.getRules();
        switch (winCondition) {
            case "pass":
                setPoinsPass(hits);
                break;
        }
    }

    public Map<Integer, Card> placeHits(ArrayList<Card> hits) {
        printer.clear();
        for (Card card : hits) {
            printer.put(printer.size() + 1, card);
        }
        return printer;
    }
}
