package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private List<Player> players = new ArrayList<>();
    private int currentPlayer;
    private int[] places = new int[100];
    private int[] purses  = new int[100];
    private boolean[] inPenaltyBox  = new boolean[100];


    int playersSize() {
        return players.size();
    }

    boolean addPlayer(String playerName) {
        return players.add(new Player(playerName));
    }

    String currentPlayerName() {
        return players.get(currentPlayer).name();
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
