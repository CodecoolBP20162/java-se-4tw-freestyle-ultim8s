package com.codecool.ulti;

/**
 * Created by peter on 2017.05.22..
 */
public class Card {

    private int gameValue;
    private int absoluteValue;
    private String color;
    private String name;

    public Card(Deck.Color color, Deck.Power power) {
        this.absoluteValue = power.getIntValue();
        this.color = color.name();
        this.name = power.name();
    }

    @Override
    public String toString() {
        return name + " of " + color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAbsoluteValue() {
        return absoluteValue;
    }
}
