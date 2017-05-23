package com.codecool.ulti;

import java.util.*;

/**
 * Created by peter on 2017.05.22..
 */
public class Player {

    static final Player[] players = new Player[3];

    private String name;
    private int points;
    private Role role;
    private Map<Integer, Card> hand = new HashMap<Integer, Card>();
    private List<Card> selfDeck = new ArrayList<Card>();

    public Player(String name) {
        if (Arrays.asList(players).contains(null)) {
            this.name = name;
            this.points = 0;
            this.role = Role.NOT_SET;
            for (int i = 0; i < players.length; i++) {
                if (players[i] == null) {players[i] = this;break;}
            }
        }
    }

    public void printHand() {
        System.out.println("\n\nPlayer " + this.name + "'s cards are: ");
        FancyPrint fancyPrint = new FancyPrint();
        fancyPrint.printHand(this);
    }

    public void orderHand() {
        final List<Card> values = new ArrayList<Card>(this.hand.values());
        Collections.sort(values, new Comparator<Card>() {
            public int compare(Card o1, Card o2) {
                int value1 = o1.getColor().compareTo(o2.getColor());
                if (value1 == 0) {
//                    return o1.getAbsoluteValue() > o2.getAbsoluteValue() ? 1
//                            :o1.getAbsoluteValue() < o2.getAbsoluteValue() ? -1
//                            : 0;
                    int value2 = Integer.compare(o1.getAbsoluteValue(), o2.getAbsoluteValue());
                    return value2;
                }
                return value1;
            }
        });
        this.hand.clear();
        for (Card card: values) {
            this.hand.put(this.hand.size()+1, card);
        }
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Card> getDeck() {
        return selfDeck;
    }

    public void setDeck(List<Card> deck) {
        this.selfDeck = deck;
    }

    public static Player[] getPlayers() {
        return players;
    }

    public Map<Integer, Card> getHand() {
        return hand;
    }

    public void setHand(Map<Integer, Card> hand) {
        this.hand = hand;
    }

    enum Role {
        NOT_SET,
        SOLOIST,
        PLAYER;
    }
}
