package com.adaptionsoft.games.uglytrivia;

public class Player {
    private final String name;
    private int place;
    private boolean nullObject;

    public Player(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public Player move(int roll) {
        this.place += roll;
        return this;
    }

    public int place() {
        return place;
    }

    public static Player nullObject() {
        Player player = new Player("no player");
        player.nullObject = true;
        return player;
    }

    public boolean isNullObject() {
        return nullObject;
    }

    public void wrapPlaceIfNecessary() {
        if (place > 11) {
            place -= 12;
        }
    }
}
