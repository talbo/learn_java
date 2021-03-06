package com.battleship.model;

public class Battlefield {

    private static final int DEFAULT_FIELD_SIZE = 10;
    private int fieldSize;
    private int[][] field;

    public Battlefield() {
        this(DEFAULT_FIELD_SIZE);
    }

    public Battlefield(int size) {
        fieldSize = size;
        field = new int[fieldSize][fieldSize];
        for(int i=0; i<fieldSize; i++) {
            for(int j=0; j<fieldSize; j++) {
                field[i][j] = 0;
            }
        }
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int[][] getField() {
        return field;
    }

    public void setShip(Ship ship) {
        int[][] positions = ship.getPositions();
        for(int i=0; i<positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];
            field[x][y] = 2;
        }
    }

}
