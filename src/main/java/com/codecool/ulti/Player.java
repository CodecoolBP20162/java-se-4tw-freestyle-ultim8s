package com.codecool.ulti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by peter on 2017.05.22..
 */
public class Player {

    private static final Player[] players = new Player[3];

    private String name;
    private int points;
    private Role role;
    private List<Card> hand = new ArrayList<Card>();
    private List<Card> deck = new ArrayList<Card>();

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

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public static Player[] getPlayers() {
        return players;
    }

    enum Role {
        NOT_SET,
        SOLOIST,
        PLAYER;
    }
}
