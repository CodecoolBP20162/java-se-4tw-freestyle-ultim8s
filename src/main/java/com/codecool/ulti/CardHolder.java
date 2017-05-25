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
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
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
                coloring = getColoring(card);
                System.out.print(coloring+" _ _ _ _ _ "+ANSI_RESET);
            }
            System.out.println();
            for (Card card: cards.values()) {
                coloring = getColoring(card);
                System.out.print(coloring+"|         |"+ANSI_RESET);
            }
            System.out.println();
            for (Card card: cards.values()) {
                switch (card.getColor().length()) {
                    case 3: filler = 4; break;
                    case 4: filler = 4; break;
                    case 5: filler = 3; break;
                    case 6: filler = 3; break;
                    case 7: filler = 2; break;
                    case 8: filler = 2; break;
                }
                fill(filler, card.getColor(), coloring = getColoring(card));
            }
            System.out.println();
            for (Card card: cards.values()) {
                coloring = getColoring(card);
                System.out.print(coloring+"|         |"+ANSI_RESET);
            }
            System.out.println();
            for (Card card: cards.values()) {
                coloring = getColoring(card);
                System.out.print(coloring+"|         |"+ANSI_RESET);
            }
            System.out.println();
            for (Card card: cards.values()) {
                switch (card.getName().length()) {
                    case 3: filler = 4; break;
                    case 4: filler = 4; break;
                    case 5: filler = 3; break;
                    case 6: filler = 3; break;
                    case 7: filler = 2; break;
                    case 8: filler = 2; break;
                }
                fill(filler, card.getName(), coloring = getColoring(card));
            }
            System.out.println();
            for (Card card: cards.values()) {
                coloring = getColoring(card);
                System.out.print(coloring+"| _ _ _ _ |"+ANSI_RESET);
            }
            System.out.println();
        }

    }


    /**
     * Method used for whitespace counting.
     * @param filler The amount of whitespace to be left before the string.
     * @param string The name or color string for the card.
     */
    private void fill(int filler, String string, String coloring) {
        System.out.print(coloring+"|");
        for (int i = 0; i < filler-1; i++) {
            System.out.print(" ");
        }
        System.out.print(string);
        for (int i = 0; i < 10-filler-string.length(); i++) {
            System.out.print(" ");
        }
        System.out.print("|"+ANSI_RESET);
    }

    public static void setTrump(String string) {
        for (Deck.Color color: Deck.Color.values()) {
            if (color.name().equals(string)) {
                trump = color;
            }
        }
    }

    /**
     *
     * @param card
     * @return Returns the color that needs to be used to print the cards.
     */
    private String getColoring(Card card) {
        if (card.getColor().equals("MAKK")) {return ANSI_YELLOW;}
        else if (card.getColor().equals("TÖK")) {return ANSI_BLUE;}
        else if (card.getColor().equals("PIROS")) {return ANSI_RED;}
        else if (card.getColor().equals("ZÖLD")) {return ANSI_GREEN;}
        else {return null;}
    }

    /**
     *
     * @param string
     * @return Returns the color that needs to be used to print the cards.
     */
    private String getColoring(String string) {
        if (string.equals("MAKK")) {return ANSI_YELLOW;}
        else if (string.equals("TÖK")) {return ANSI_BLUE;}
        else if (string.equals("PIROS")) {return ANSI_RED;}
        else if (string.equals("ZÖLD")) {return ANSI_GREEN;}
        else {return null;}
    }

    /**
     *
     * @param hits
     * @return Converts arraylist to a printer map for printing.
     */
    public Map<Integer, Card> placeHits(ArrayList<Card> hits) {
        printer.clear();
        for (Card card: hits) {
            printer.put(printer.size()+1, card);
        }
        return printer;
    }
}