package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    private ArrayList players = new ArrayList();
    private int currentPlayer;
    private int[] places = new int[6];
    private int[] purses  = new int[6];
    private boolean[] inPenaltyBox  = new boolean[6];


    int playersSize() {
        return players.size();
    }

    boolean addPlayer(String playerName) {
        return players.add(playerName);
    }

    Object currentPlayer() {
        return players.get(currentPlayer);
    }

    int getCurrentPlayerPlace() {
        return places[currentPlayer];
    }

    private void movePlayer(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;
    }

    private void wrapPlayerPlaceIfNecessary() {
        if (places[currentPlayer] > 11) {
            places[currentPlayer] = places[currentPlayer] - 12;
        }
    }

    public void advancePlace(int roll) {
        movePlayer(roll);
        wrapPlayerPlaceIfNecessary();
    }

    public int getCurrentPlayerCoins() {
        return purses[currentPlayer];
    }

    public void currentPlayerWonACoin() {
        purses[currentPlayer]++;
    }

    public boolean isCurrentPlayerinPenaltyBox() {
        return inPenaltyBox[currentPlayer];
    }

    public void sendCurrentPlayertoPenaltyBox() {
        inPenaltyBox[currentPlayer] = true;
    }

    boolean didPlayerWin() {
        return !(getCurrentPlayerCoins() == 6);
    }

    public void next() {
        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }
}
