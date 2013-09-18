import java.net.*;
import java.io.*;

public class Server {

    public static void main(String[] ar)    {
        int port = 6666;
        try {

            ServerSocket ss = new ServerSocket(port);

            System.out.println("Wait...");
            Socket socket = ss.accept();
            System.out.println("Client connecing!");

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = null;
            while(true) {

                line = in.readUTF();
                System.out.println("Client: " + line);

                System.out.println("Sending it back");
                out.writeUTF(line);
                out.flush();

            }

        } catch(Exception x) { x.printStackTrace(); }
    }

}