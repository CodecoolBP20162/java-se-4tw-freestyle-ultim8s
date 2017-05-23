package com.codecool.ulti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by peter on 2017.05.23..
 */
public class Controller {
    LinkedList<Player> players = new LinkedList<Player>();


    public void initGame() {
        Deck deck = new Deck();
        getPlayerNames();
        deck.deal();
        for (Player player: players){
            player.printHand();
        }
        deck.printHand();
    }

    public LinkedList<Player> getPlayerNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the first players name: ");
        players.add(new Player(scanner.nextLine()));
        System.out.println("Please enter the second players name: ");
        players.add(new Player(scanner.nextLine()));
        System.out.println("Please enter the third players name: ");
        players.add(new Player(scanner.nextLine()));
        return players;
    }


}
