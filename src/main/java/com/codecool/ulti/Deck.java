package com.codecool.ulti;

import java.util.*;

/**
 * Created by peter on 2017.05.22..
 */
public class Deck {

    private static Stack shuffledDeck = new Stack();
    Random random = new Random();
    private boolean isDealed;

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



    public void printDeck() {
        System.out.println(Arrays.toString(shuffledDeck.toArray()));
    }

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
    }

    protected enum Color {
        BELLS,
        ACORNS,
        HEARTS,
        LEAVES
    }

    protected enum Power {
        SEVEN(1),
        EIGHT(2),
        NINE(3),
        UNDER(4),
        OVER(5),
        KING(6),
        TEN(7),
        ACE(8);

        private int value;

        Power(int newValue) {
            int value = newValue;
        }

        public int getIntValue() {return value;}

    }

    public static Stack getDeck() {
        return shuffledDeck;
    }


}
