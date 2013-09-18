package com.battleship.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkPlayer extends Player {

    public static final int SERVER = 0;
    public static final String DEFAULT_ADDRESS = "localhost";
    public static final int CLIENT = 1;
    public static final int PORT = 8001;


    private ObjectInputStream in;
    private ObjectOutputStream out;
    //private boolean firstTurn = false;

    public NetworkPlayer(int type, String serverAddress) throws IOException {

        super();

        switch (type) {
            case SERVER:
                ServerSocket listener = new ServerSocket(PORT);
                Socket s = listener.accept();
                in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
                out = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));
                out.flush();
                break;
            case CLIENT:
                if(serverAddress.equals("")) {
                    serverAddress = DEFAULT_ADDRESS;
                }
                InetAddress ipAddress = InetAddress.getByName(serverAddress);
                s = new Socket(ipAddress, PORT);
                out = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));
                out.flush();
                in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
                break;
        }




    }

    public void setShip(int shipId, int[][] positions) {

        System.out.println("Try set remote ship");

    };

}
