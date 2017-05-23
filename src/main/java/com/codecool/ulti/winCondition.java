package com.codecool.ulti;

/**
 * Created by peter on 2017.05.22..
 */
public class winCondition {

    private static String winRules;

    public winCondition(int num) {
        switch (num) {
            case 1: winRules = "pass";
        }
    }

    private boolean winCheck() {
        for (Player player: Player.players) {
                 if (winRules == "pass") {
                    return checkPass();
                }
            }
        return false;
    }

    private boolean checkPass() {
        int winTreshold = 0;
        for (Player player: Player.players) {
            for (Card card: player.getSelfDeck()) {
                player.setPoints(card.getGameValue());
            }
            if (player.getRole() == Player.Role.SOLOIST) {winTreshold = player.getPoints();}
        }
        for (Player player: Player.players) {
            if (player.getRole() == Player.Role.PLAYER && player.getPoints() > winTreshold) {return false;}
        }
        return true;
    }
}
