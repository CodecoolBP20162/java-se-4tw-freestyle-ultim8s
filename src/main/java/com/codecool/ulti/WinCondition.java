package com.codecool.ulti;

import java.util.Objects;

/**
 * Created by peter on 2017.05.22..
 */
public class WinCondition {

    private static String winRules;

    public static String getWinRules() {
        return winRules;
    }

    public WinCondition(String bid) {
        switch (bid) {
            case "pass": winRules = "pass";
        }
    }

    public boolean winCheck() {
        for (Player player: Player.players) {
                 if (winRules.equals("pass")) {
                    return winCheckPass();
                }
            }
        return false;
    }

    private boolean winCheckPass() {
        int defenderTotal = 0;
        int winTreshold = 0;

        for (Player player : Player.players) {
            for (Card card : player.hits()) {
                player.setPoints(card.getGameValue());
            }
            if (player.getRole() == Player.Role.PLAYER) {
                defenderTotal += player.getPoints();
            } else if (player.getRole() == Player.Role.SOLOIST) {
                winTreshold = player.getPoints();
            }
        }
        if (winTreshold > defenderTotal) {
            return true;
        }
        return false;
    }
}
