package com.codecool.ulti;

import java.util.*;

/**
 * Created by peter on 2017.05.22..
 */
public class Deck {

    private static List<Card> deck = new ArrayList<Card>();
    Random random = new Random();

    public Deck() {

        for (Color color : Color.values()) {
            for (Power power : Power.values()) {
                deck.add(new Card(color, power));
            }
        }
        Collections.shuffle(deck);
    }



    public void printDeck() {
        for (Card card : deck) {
            System.out.print(card.toString() + ", ");
        }
    }

    public void deal() {
        int count = 0;
        if (!Arrays.asList(Player.players).contains(null)) {
            for (Player player: Player.players) {
                for (int i = 0; i < 10; i++) {
                    int pick = random.nextInt(deck.size());
                    player.getHand().put(count, deck.get(pick));
                    deck.remove(pick);
                    count++;
                }
                player.orderHand();
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

    public static List<Card> getDeck() {
        return deck;
    }


}
