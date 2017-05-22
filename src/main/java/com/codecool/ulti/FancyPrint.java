package com.codecool.ulti;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matyi on 2017.05.22..
 */
public class FancyPrint {

    public void printHand(Player player) {

        int filler = 0;

        System.out.println();
        for (Map.Entry<Integer, Card> players: player.getHand().entrySet()) {
            System.out.print(players.getKey()+"          ");
        }
        System.out.println();
        for (Card card: player.getHand().values()) {
            System.out.print(" _ _ _ _ _ ");
        }
        System.out.println();
        for (Card card: player.getHand().values()) {
            System.out.print("|         |");
        }
        System.out.println();
        for (Card card: player.getHand().values()) {
            switch (card.getName().length()) {
                case 1: filler = 5; break;
                case 2: filler = 5; break;
                case 3: filler = 4; break;
                case 4: filler = 4; break;
                case 5: filler = 3; break;
                case 6: filler = 3; break;
                case 7: filler = 2; break;
            }
            fill(filler, card.getName());
        }
        System.out.println();
        for (Card card: player.getHand().values()) {
            System.out.print("|         |");
        }
        System.out.println();
        for (Card card: player.getHand().values()) {
            System.out.print("     of    ");
        }
        System.out.println();
        for (Card card: player.getHand().values()) {
            System.out.print("|         |");
        }
        System.out.println();
        for (Card card: player.getHand().values()) {
            switch (card.getColor().length()) {
                case 5: filler = 3; break;
                case 6: filler = 3; break;
                case 7: filler = 2; break;
            }
            fill(filler, card.getColor());
        }
        System.out.println();
        for (Card card: player.getHand().values()) {
            System.out.print("|_________|");
        }

    }

    private void fill(int filler, String string) {
        for (int i = 0; i < filler; i++) {
            System.out.print(" ");
        }
        System.out.print(string);
        for (int i = 0; i < 11-filler-string.length(); i++) {
            System.out.print(" ");
        }
    }
}
