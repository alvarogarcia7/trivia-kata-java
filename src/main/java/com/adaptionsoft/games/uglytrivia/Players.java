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

    Object currentPlayer(int currentPlayer) {
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
        getPlaces()[playersSize() - 1] = 0;
    }

    int getCurrentPlayerPlace() {
        return getPlaces()[getCurrentPlayer()];
    }

    void movePlayer(int roll) {
        getPlaces()[getCurrentPlayer()] = getCurrentPlayerPlace() + roll;
    }

    void wrapPlayerPlace() {
        getPlaces()[getCurrentPlayer()] = getCurrentPlayerPlace() - 12;
    }

    void advancePlace(int roll) {
        movePlayer(roll);
        if (getCurrentPlayerPlace() > 11) wrapPlayerPlace();
    }
}
