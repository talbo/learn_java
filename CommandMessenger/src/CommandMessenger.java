public class CommandMessenger {

    private int mode;
    private Connector connectorRead;
    private Connector connectorWrite;

    CommandMessenger(int mode) {
        this.mode = mode;

        connectorRead = new Connector(mode, 0);
        connectorWrite = new Connector(mode, 1);

    }

    public String sendMessage(String msg) {
        return connectorWrite.sendMessage(msg);
    }


}
