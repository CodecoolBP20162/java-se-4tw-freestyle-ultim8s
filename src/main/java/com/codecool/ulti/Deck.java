package com.codecool.ulti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by peter on 2017.05.22..
 */
public class Deck extends CardHolder {
    private static final Logger logger = LoggerFactory.getLogger(Deck.class);

    private static Stack shuffledDeck = new Stack();
    Random random = new Random();
    private boolean isDealed;

    /**
     * Deck constructor. Uses the set enums to create a deck of 32 card objects, then shuffles it.
     */
    public Deck() {
        List<Card> deck = new ArrayList<Card>();
        for (Color color : Color.values()) {
            for (Power power : Power.values()) {
                deck.add(new Card(color, power));
            }
        }
        Collections.shuffle(deck);
        for (Card card:deck){
            shuffledDeck.push(card);
        }
    }


    /**
     * Prints the total deck.
     */
    public Map<Integer, Card> getTalon() {
        return hand;
    }

    /**
     * Deals the deck from top to all players by one card.
     */
    public void deal() {
        if (!isDealed) {
            isDealed = true;
            if (!Arrays.asList(Player.players).contains(null)) {
                for (Player player: Player.players) {
                    for (int i = 0; i < 10; i++) {
                        player.getHand().put(i+1, (Card) shuffledDeck.pop());
                    }
                    player.orderHand();
                }
            }
        }
        hand.put(1,(Card) shuffledDeck.pop());
        hand.put(2,(Card) shuffledDeck.pop());
    }

    /**
     * Color of cards.
     */
    protected enum Color {
        TÖK,
        MAKK,
        PIROS,
        ZÖLD
    }

    /**
     * Original order value of cards.
     */
    protected enum Power {
        HETES(1),
        NYOLCAS(2),
        KILENCES(3),
        ALSÓ(4),
        FELSŐ(5),
        KIRÁLY(6),
        TIZES(7),
        ÁSZ(8);

        private final int value;

        /**
         * Sets the ordering value for cards.
         * @param value Ordering value of the card.
         */
        private Power(int value) {this.value = value;}

        /**
         *
         * @return Returns the ordering value of the card.
         */
        public int getIntValue() {return value;}

    }

    /**
     *
     * @return Returns the shuffled deck.
     */
    public static Stack getDeck() {
        return shuffledDeck;
    }


}
