package com.codecool.ulti;

import java.util.HashMap;

/**
 * Created by peter on 2017.05.22..
 */
public class Card {

    private int gameValue;
    private int absoluteValue;
    private String color;
    private String name;

    public Card(Deck.Color color, Deck.Name name) {
        this.absoluteValue = name.getIntValue();
        this.color = color.name();
        this.name = name.name();
    }

    @Override
    public String toString() {
        return name + " of " + color;
    }
}
