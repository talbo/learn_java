package com.battleship.controller;

import com.battleship.model.Battle;
import com.battleship.model.NetworkPlayer;
import com.battleship.model.LocalPlayer;
import com.battleship.model.Fleet;
import com.battleship.view.BaseView;
import java.io.IOException;

public class Controller {

    private Battle battle;
    private BaseView view;
    private NetworkPlayer networkPlayer;
    private LocalPlayer localPlayer;

    private Fleet localFleet;

    public Controller(Battle battle, BaseView view) {

        this.battle = battle;
        this.view = view;

        //view.printFields();
        view.gameHello();
        battle.setGameMode(view.askGameMode());

        if(battle.getGameMode() == 1) {
            startNetworkGame();
        }
        else {
            startLocalGame();
        }



        setupFleet();

    }

    private void startNetworkGame() {

        if(battle.getNetworkGameMode() == -1) {
            battle.setNetworkGameMode(view.askNetworkGameMode());
        }

        int networkGameMode = battle.getNetworkGameMode();
        battle.setLocalPlayer(networkGameMode);

        localFleet = battle.getLocalFleet();

        if(networkGameMode == NetworkPlayer.SERVER) {
            try {
                networkPlayer = new NetworkPlayer(networkGameMode, null);
                localPlayer = new LocalPlayer(localFleet);
                battle.setPlayers(localPlayer, networkPlayer);
            } catch (IOException e) {
                view.printLine(e.getMessage());
            }
        }
        if(networkGameMode == NetworkPlayer.CLIENT) {
            String serverAddress = view.askSreverAddress();
            try {
                networkPlayer = new NetworkPlayer(networkGameMode, serverAddress);
                localPlayer = new LocalPlayer(localFleet);
                battle.setPlayers(networkPlayer, localPlayer);
            } catch (IOException e) {
                view.printLine(e.getMessage());
            }
        }




    }

    private void startLocalGame() {
        // TODO
    }

    private void setupFleet() {



        view.printFields();

        int shipId = 0;

        for(int shipType=0; shipType<4; shipType++) {

            int shipCount = shipType+1;
            int shipSize = 4 - shipType;

            for(int i=0; i<shipCount; i++) {
                int[][] positions = new int[shipSize][2];
                for(int j=0; j<shipSize; j++) {
                    positions[j] = view.askShipPosition(shipType, shipSize, j+1);
                }
                //fleet.setShip(shipId, positions);
                localPlayer.setShip(shipId, positions);
                networkPlayer.setShip(shipId, positions);



                view.printFields();
                shipId++;
            }

        }

    }



}
