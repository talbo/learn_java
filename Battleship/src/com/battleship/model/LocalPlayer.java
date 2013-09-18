package com.battleship.model;

public class LocalPlayer extends Player {

    private Fleet fleet;

    public LocalPlayer(Fleet fleet) {
        super();
        this.fleet = fleet;
    }

    public void setShip(int shipId, int[][] positions) {

        fleet.setShip(shipId, positions);

    };

}
