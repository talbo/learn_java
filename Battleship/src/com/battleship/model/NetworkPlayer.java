package com.battleship.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkPlayer extends Player {

    public static final int SERVER = 0;
    public static final int CLIENT = 1;
    public static final int PORT = 8001;

    public NetworkPlayer(int type, String addr) throws IOException {
        super();

    }

}
