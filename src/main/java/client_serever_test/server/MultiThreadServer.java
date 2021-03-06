package client_serever_test.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

    private static int portServer = 5000; // Номер порта сервера

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(portServer);
            System.out.println(" <<< Server is ready >>> ");

             while(true){
                 Socket serverClient = serverSocket.accept();
                 ServerThreadTest stt = new ServerThreadTest(serverClient);
                 stt.start();
             }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
