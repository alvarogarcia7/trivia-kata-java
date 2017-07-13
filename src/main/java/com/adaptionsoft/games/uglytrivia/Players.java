package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    private ArrayList players = new ArrayList();
    private int currentPlayer;
    private int[] places = new int[6];

    int playersSize() {
        return players.size();
    }

    boolean addPlayer(String playerName) {
        return players.add(playerName);
    }

    Object currentPlayer() {
        return players.get(currentPlayer);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int[] getPlaces() {
        return places;
    }

    public void setPlaces(int[] places) {
        this.places = places;
    }

    void initializePlace() {
        places[playersSize() - 1] = 0;
    }

    int getCurrentPlayerPlace() {
        return places[currentPlayer];
    }

    private void movePlayer(int roll) {
        getPlaces()[currentPlayer] = places[currentPlayer] + roll;
    }

    private void wrapPlayerPlaceIfNecessary() {
        if (places[currentPlayer] > 11) {
            getPlaces()[currentPlayer] = places[currentPlayer] - 12;
        }
    }

    public void advancePlace(int roll) {
        movePlayer(roll);
        wrapPlayerPlaceIfNecessary();
    }
}
