import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerArduino {

    public static void main(String[] args) throws IOException {
        String exit = "exit";
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server is ready...");

        Socket clientSocket = serverSocket.accept();

        BufferedReader inServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String fromClient = inServer.readLine();
        System.out.println(fromClient);

        BufferedWriter outServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        label_2:
        while (true) {
            System.out.println("Enter the command : ");
            Scanner commandToClient = new Scanner(System.in);
            //BufferedReader commandToClient = new BufferedReader(new InputStreamReader(System.in));
            String sb = commandToClient.nextLine();
            System.out.println(sb);

            if (sb.equals(exit)) {
                System.out.println(sb + " exit ");
                outServer.write(sb + "\n");
                outServer.flush();
                System.out.println("Server is over...");

                outServer.close();
                inServer.close();
                clientSocket.close();
                serverSocket.close();
                break label_2;
            } else {

                outServer.write(sb + "\n");
                System.out.println(sb + " on/Off");
                System.out.println(sb.equals(exit));
                // outServer.flush();
            }

        }

        clientSocket.close();
        clientSocket.close();
    }
}
