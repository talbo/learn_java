package com.battleship.controller;

import com.battleship.model.Battle;
import com.battleship.model.NetworkPlayer;
import com.battleship.model.LocalPlayer;
import com.battleship.view.BaseView;
import java.io.IOException;

public class Controller {

    private Battle battle;
    private BaseView view;

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

    }

    private void startNetworkGame() {

        if(battle.getNetworkGameMode() == -1) {
            battle.setNetworkGameMode(view.askNetworkGameMode());
        }

        int networkGameMode = battle.getNetworkGameMode();

        if(networkGameMode == NetworkPlayer.SERVER) {
            try {
                battle.setPlayers(new NetworkPlayer(networkGameMode, null), new LocalPlayer());
            } catch (IOException e) {
                view.printLine(e.getMessage());
            }
        }
        if(networkGameMode == NetworkPlayer.CLIENT) {
            String serverAddress = view.askSreverAddress();
            try {
                battle.setPlayers(new LocalPlayer(), new NetworkPlayer(networkGameMode, serverAddress));
            } catch (IOException e) {
                view.printLine(e.getMessage());
            }
        }


    }

    private void startLocalGame() {
        // TODO
    }



}
