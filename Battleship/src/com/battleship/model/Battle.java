package com.battleship.model;

public class Battle {

    private static final int COUNT = 2;
    private Battlefield[] fields;
    private Player[] players;

    public Battle() {

        players = new Player[COUNT];
        fields = new Battlefield[COUNT];

        for(int i = 0; i < COUNT; i++) {
            fields[i] = new Battlefield();
            players[i] = new Player();
        }

    }

    public Battlefield[] getFields() {
        return fields;
    }

}
