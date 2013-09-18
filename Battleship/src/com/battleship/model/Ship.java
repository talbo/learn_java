package com.battleship.model;

public class Ship {

    private int[][] positions;

    public Ship(int[][] positions) {
        this.positions = positions;
    }

    public int[][] getPositions() {
        return positions;
    }

}
