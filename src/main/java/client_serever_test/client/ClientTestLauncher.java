package client_serever_test.client;

import client_serever_test.client.ClientTestA;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientTestLauncher {

    private static String hostSet = "localhost"; // Хост для подключения клиента к серверу
    private static int port = 5000;              // Порт для подключеия клиента к серверу

    public static void main(String[] args) {
        try {
            InetAddress host = InetAddress.getByName(hostSet);

            System.out.println(" <<< Client is ready >>> ");
            System.out.println("Enter the command : ");

            //while(true){
            ClientTestB client = new ClientTestB(host, port);
            client.start();
            //}


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
