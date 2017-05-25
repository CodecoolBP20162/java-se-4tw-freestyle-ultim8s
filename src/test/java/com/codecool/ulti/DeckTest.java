package com.codecool.ulti;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by peter on 2017.05.25..
 */
class DeckTest {

    @Test
    public void testIsDealingWorks() {
        LinkedList<Player> players = new LinkedList<Player>();
        Deck deck = new Deck();
        players.add(new Player("1) "));
        players.add(new Player("2) "));
        players.add(new Player("3) "));
        deck.deal();
        assertEquals(10,players.get(1).getHand().size());
    }

    @Test
    public void testIsTalonCreated() {
        Deck deck = new Deck();
        deck.deal();
        assertEquals(2,deck.getTalon().size());
    }

}