public class CommandMessenger {

    private Connector connectorRead;
    private Connector connectorWrite;

    CommandMessenger(int mode) {
        connectorRead = new Connector(mode, 0, this);
        connectorWrite = new Connector(mode, 1, this);
    }

    public String sendMessage(String msg) {
        return connectorWrite.sendMessage(msg);
    }

    public String parseMessage(String msg) {
        return "[" + msg + "]";
    }


}
