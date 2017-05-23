package com.codecool.ulti;

import java.util.*;

/**
 * Created by peter on 2017.05.22..
 */
public class Deck extends Hand{

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
        BELLS,
        ACORNS,
        HEARTS,
        LEAVES
    }

    /**
     * Original order value of cards.
     */
    protected enum Power {
        SEVEN(1),
        EIGHT(2),
        NINE(3),
        UNDER(4),
        OVER(5),
        KING(6),
        TEN(7),
        ACE(8);

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
