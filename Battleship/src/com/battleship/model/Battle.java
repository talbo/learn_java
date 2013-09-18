package com.battleship.model;

public class Battle {

    private static final int COUNT = 2;
    private static final String[] GAME_MODES = {"Single-player", "Multi-player"};
    private static final String[] NETWORK_GAME_MODES = {"Server", "Client"};
    private int gameMode = 0;
    private int networkGameMode = -1;
    private int localPlayer = -1;
    private Battlefield[] fields;
    private Player[] players;
    private Fleet[] fleets;

    public Battle() {

        //players = new Player[COUNT];
        fields = new Battlefield[COUNT];
        fleets = new Fleet[COUNT];

        for(int i = 0; i < COUNT; i++) {
            fields[i] = new Battlefield();
            fleets[i] = new Fleet(fields[i]);
        }

    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player player1, Player player2) {
        players = new Player[COUNT];
        players[0] = player1;
        players[1] = player2;
    }

    public int getLocalPlayer() {
        return localPlayer;
    }

    public void setLocalPlayer(int localPlayer) {
        this.localPlayer = localPlayer;
    }

    public Battlefield[] getFields() {
        return fields;
    }

    public static String[] getGameModes() {
        return GAME_MODES;
    }

    public static String[] getNetworkGameModes() {
        return NETWORK_GAME_MODES;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public int getNetworkGameMode() {
        return networkGameMode;
    }

    public void setNetworkGameMode(int networkGameMode) {
        this.networkGameMode = networkGameMode;
    }

    public Fleet getLocalFleet() {
        return fleets[localPlayer];
    }
}
