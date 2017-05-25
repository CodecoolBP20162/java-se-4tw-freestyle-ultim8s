package com.codecool.ulti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter on 2017.05.22..
 */
public class Card {
    private static final Logger logger = LoggerFactory.getLogger(Card.class);

    /**
     * The value of the card according to the game mode.
     */
    private int gameValue;
    /**
     * The actual value of the card, for ordering purposes.
     */
    private int absoluteValue;
    /**
     * Color of the card.
     */
    private String color;
    /**
     * Name of the card in strings.
     */
    private String name;

    /**
     * Card constructor, it takes two enum parameters.
     *
     * @param color The color of the card (Acorns, Bells, Leaves, Hearts)
     * @param power The cards absolute value for ordering purposes.
     */
    public Card(Deck.Color color, Deck.Power power) {
        this.absoluteValue = power.getIntValue();
        this.color = color.name();
        this.name = power.name();
        this.gameValue = 0;
    }

    /**
     * @return Returns the name and color of the card in a string.
     */
    @Override
    public String toString() {
        return name + " of " + color;
    }

    /**
     * @return Returns the name of the card.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the color of the card.
     */
    public String getColor() {
        return color;
    }

    /**
     * @return Returns the absolute value of the card, for ordering purposes.
     */
    public int getAbsoluteValue() {
        return absoluteValue;
    }

    public int getGameValue() {
        return gameValue;
    }

    public void setGameValue(int gameValue) {
        this.gameValue += gameValue;
    }

    public void resetGameValue() {
        this.gameValue = 0;
    }
}
