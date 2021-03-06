package com.codecool.ulti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by peter on 2017.05.22..
 */
public class Player extends CardHolder {
    private static final Logger logger = LoggerFactory.getLogger(Player.class);

    static final Player[] players = new Player[3];

    private String name;
    private int points;
    private Role role;
    private List<Card> hitedCards = new ArrayList<Card>();

    /**
     * Player constructor. It takes name as a parameter and sets the points to 0 and player role to not_set. The constructor can be called only 3 times.
     *
     * @param name String name of the player.
     */
    public Player(String name) {
        if (Arrays.asList(players).contains(null)) {
            this.name = name;
            this.points = 0;
            this.role = Role.NOT_SET;
            for (int i = 0; i < players.length; i++) {
                if (players[i] == null) {
                    players[i] = this;
                    break;
                }
            }
        }
    }

    /**
     * Orders cards in players hand by color and ordering value.
     */
    public void orderHand() {
        final List<Card> values = new ArrayList<Card>(this.hand.values());
        List<Card> orderer = new ArrayList<Card>();
        this.hand.clear();
        for (Deck.Color color : Deck.Color.values()) {
            orderer.clear();
            for (Card card : values) {
                if (card.getColor().equals(color.name())) {
                    orderer.add(card);
                }
            }
            Collections.sort(orderer, new Comparator<Card>() {
                public int compare(Card o1, Card o2) {
                    return o1.getAbsoluteValue() - o2.getAbsoluteValue();
                }
            });
            for (Card card : orderer) {
                this.hand.put(this.hand.size() + 1, card);
            }
        }
    }

    public boolean hasColorInHand(String color) {
        for (Card card: hand.values()) {
            if (card.getColor().equals(color)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasBiggerInHand(int num, String color) {
        for (Card card : hand.values()) {
            if (card.getAbsoluteValue() > num && card.getColor().equals(color)) {
                return true;
            }
        } return false;
    }

    /**
     * @return Returns name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the points of the player.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points of the player.
     *
     * @param points Takes an int as a parameter.
     */
    public void addPoints(int points) {
        this.points += points;
    }

    public void resetPoints(int points) {
        this.points = 0;
    }

    /**
     * @return Returns the role of the player.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the player.
     *
     * @param role Takes a Role enum as a parameter.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @return Returns the array of players.
     */
    public static Player[] getPlayers() {
        return players;
    }

    /**
     * @return Returns the player's hand as a hashmap.
     */
    public Map<Integer, Card> getHand() {
        return hand;
    }

    public void addCardToHitedCards(Card card) {
        hitedCards.add(card);
    }

    public List<Card> hits() {
        return hitedCards;
    }

    /**
     * The possible roles of the players.
     */
    enum Role {
        NOT_SET,
        SOLOIST,
        PLAYER;
    }
}
