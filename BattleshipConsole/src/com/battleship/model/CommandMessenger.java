package com.battleship.model;

public class CommandMessenger {

    private Connector connectorRead;
    private Connector connectorWrite;

    CommandMessenger(int mode) {
        connectorRead = new Connector(mode, 0, this);
        connectorWrite = new Connector(mode, 1, this);

        waitConnect();
    }



    public void waitConnect() {

        System.out.println("Connecting...");
        while (!connectorRead.isConnected() || !connectorWrite.isConnected()) {
            // do nothing
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {}
        }
        System.out.println("Connected!");

    }

    public String sendMessage(String msg) {
        return connectorWrite.sendMessage(msg);
    }

    public String parseMessage(String msg) {
        return "[" + msg + "]";
    }


}
