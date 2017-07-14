package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private List<Player> players;
    private int currentPlayer;
    private int[] purses;
    private boolean[] inPenaltyBox;
    private Player currentPlayerObject = Player.nullObject();

    public Players() {
        inPenaltyBox = new boolean[100];
        purses = new int[100];
        players = new ArrayList<>();
    }

    int playersSize() {
        return players.size();
    }

    boolean addPlayer(String playerName) {
        Player player = new Player(playerName);
        if(currentPlayerObject.isNullObject()){
            currentPlayerObject = player;
        }
        return players.add(player);
    }

    String currentPlayerName() {
        return players.get(currentPlayer).name();
    }

    int getCurrentPlayerPlace() {
        return currentPlayerObject.place();
    }

    private void movePlayer(int roll) {
        currentPlayerObject = currentPlayerObject.move(roll);
    }

    private void wrapPlayerPlaceIfNecessary() {
        currentPlayerObject.wrapPlaceIfNecessary();
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
        int index = players.indexOf(currentPlayerObject);
        index++;
        if (index == players.size()) {
            index = 0;
        }
        currentPlayerObject = players.get(index);

        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }
}
