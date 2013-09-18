package com.battleship.model;

public class Fleet {

    static final int[] SHIP_CONF = {};

    private Ship[] ships;
    private Battlefield field;

    public Fleet(Battlefield field) {
        this.field = field;
        ships = new Ship[1+2+3+4];
    }

    public void setShip(int shipId, int[][] positions) {
        ships[shipId] = new Ship(positions);
        field.setShip(ships[shipId]);
    }

    /*
        1 ship 4 size
        2 ship 3 size
        3 ship 2 size
        4 ship 1 size
     */



}
