package com.codecool.ulti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by matyi on 2017.05.22..
 */
public class CardHolder {
    private static final Logger logger = LoggerFactory.getLogger(CardHolder.class);

    public static Deck.Color trump = null;
    public Map<Integer, Card> hand = new HashMap<Integer, Card>();
    private Map<Integer, Card> printer = new HashMap<Integer, Card>();
    private List<String> colors = new LinkedList<>();
    private List<Integer> powers = new LinkedList<>();
    private String coloring;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    /**
     * Prints the hand of the player with super fancy graphics.
     */
    public void printCards(Map<Integer, Card> cards) {
        if(cards.size()>0){
            int filler = 0;

            System.out.println();
            for (Map.Entry<Integer, Card> players: cards.entrySet()) {
                System.out.print(players.getKey()+"[]        ");
            }
            System.out.println();
            for (Card card: cards.values()) {

                System.out.print(coloring+ " _ _ _ _ _ "+ANSI_RESET);
            }
            System.out.println();
            for (Card card: cards.values()) {
                System.out.print("|         |"+ANSI_RESET);
            }
            System.out.println();
            for (Card card: cards.values()) {
                switch (card.getName().length()) {
                    case 1: filler = 5; break;
                    case 2: filler = 5; break;
                    case 3: filler = 4; break;
                    case 4: filler = 4; break;
                    case 5: filler = 3; break;
                    case 6: filler = 3; break;
                    case 7: filler = 2; break;
                }
                fill(filler, card.getName());
            }
            System.out.println();
            for (Card card: cards.values()) {
                System.out.print("|         |"+ANSI_RESET);
            }
            System.out.println();
            for (Card card: cards.values()) {
                System.out.print("|    of   |"+ANSI_RESET);
            }
            System.out.println();
            for (Card card: cards.values()) {
                System.out.print("|         |");
            }
            System.out.println();
            for (Card card: cards.values()) {
                switch (card.getColor().length()) {
                    case 5: filler = 3; break;
                    case 6: filler = 3; break;
                    case 7: filler = 2; break;
                }
                fill(filler, card.getColor());
            }
            System.out.println();
            for (Card card: cards.values()) {
                System.out.print("| _ _ _ _ |");
            }
            System.out.println();
            for (Card card : cards.values()) {
                System.out.print(card.getAbsoluteValue()+"|"+card.getGameValue()+"|      |");
            }
        }

    }


    /**
     * Method used for whitespace counting.
     * @param filler The amount of whitespace to be left before the string.
     * @param string The name or color string for the card.
     */
    private void fill(int filler, String string) {
        System.out.print("|");
        for (int i = 0; i < filler-1; i++) {
            System.out.print(" ");
        }
        System.out.print(string);
        for (int i = 0; i < 10-filler-string.length(); i++) {
            System.out.print(" ");
        }
        System.out.print("|");
    }

    public static void setTrump(String string) {
        for (Deck.Color color: Deck.Color.values()) {
            if (color.name().equals(string)) {
                trump = color;
            }
        }
    }

    public Map<Integer, Card> placeHits(ArrayList<Card> hits) {
        printer.clear();
        for (Card card: hits) {
            printer.put(printer.size()+1, card);
        }
        return printer;
    }
}