import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] ar) {
        int serverPort = 6666;
        String address = "localhost";

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            System.out.println("Connecting to server...");
            Socket socket = new Socket(ipAddress, serverPort);
            System.out.println("Connected!");

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.println("Message:");

            while (true) {

                line = keyboard.readLine();

                out.writeUTF(line);
                out.flush();

                line = in.readUTF();
                System.out.println("Answer: " + line);

            }
        } catch (Exception x) {x.printStackTrace();}
    }

}