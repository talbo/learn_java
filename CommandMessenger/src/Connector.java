import java.io.*;
import java.net.*;

public class Connector implements Runnable {

    static final int SERVER = 0;
    static final int CLIENT = 1;

    static final int READ = 0;
    static final int WRITE = 1;

    static final String ADDRESS = "localhost";
    static final int PORT1 = 6666;
    static final int PORT2 = 5555;

    private int mode;
    private int type;
    private int port;

    private Thread thread;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    Connector(int mode, int type) {

        this.mode = mode;
        this.type = type;

        setPort();

        thread = new Thread(this, "Connector thread");
        thread.start();

    }

    public void run() {

        try {
            System.out.println("Connecting...");
            if (mode == SERVER) {
                ServerSocket ss = new ServerSocket(port);
                socket = ss.accept();
            } else {
                InetAddress ipAddress = InetAddress.getByName(ADDRESS);
                socket = new Socket(ipAddress, port);
            }
            System.out.println("Connected!");

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);

            if (type == READ) readLoop();

        } catch (Exception x) {
            x.printStackTrace();
        }

    }

    public String sendMessage(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
            msg = in.readUTF();
        } catch (Exception x) {x.printStackTrace();}

        return msg;
    }

    private void readLoop() {
        try {
            String line = null;
            while (true) {
                line = in.readUTF();
                System.out.println("Read from socket: " + line);
                System.out.println("Sending it back");
                out.writeUTF(line);
                out.flush();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private void setPort() {
        if ((mode == SERVER && type == READ) || (mode == CLIENT && type == WRITE)) port = PORT1;
        if ((mode == SERVER && type == WRITE) || (mode == CLIENT && type == READ)) port = PORT2;
    }

}
