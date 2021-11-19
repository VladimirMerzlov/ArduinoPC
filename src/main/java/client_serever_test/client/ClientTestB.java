package client_serever_test.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTestB extends Thread {
    private final InetAddress socket1;
    private final int port;

    public ClientTestB(InetAddress socket1, int port) {
        this.socket1 = socket1;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(socket1, port);

            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

            String clientToServerMsg = "";
            //String serverToClientMsg = "";


            while (!clientToServerMsg.equalsIgnoreCase("exit")) {

                clientToServerMsg = bf.readLine();
                outStream.writeUTF(clientToServerMsg);
                outStream.flush();
//                serverToClientMsg = inStream.readUTF();
//                System.out.println(serverToClientMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
