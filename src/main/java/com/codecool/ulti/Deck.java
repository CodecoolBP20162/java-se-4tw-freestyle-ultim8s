package com.codecool.ulti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by peter on 2017.05.22..
 */
public class Deck {

    List<Card> deck = new ArrayList<Card>();

    public Deck() {

        for (Color color : Color.values()) {
            for (Name name : Name.values()) {
                deck.add(new Card(color, name));
            }
        }
        Collections.shuffle(deck);
    }

    public void printDeck() {
        for (Card card : deck) {
            System.out.print(card.toString() + ", ");
        }
    }

    protected enum Color {
        BELLS,
        ACORNS,
        HEARTS,
        LEAVES
    }

    protected enum Name {
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        UNDER(11),
        OVER(12),
        KING(13),
        ACE(14);

        private int value;

        Name(int newValue) {
            int value = newValue;
        }

        public int getIntValue() {return value;}

    }


}
