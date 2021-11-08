import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

    public static void main(String[] args) {
        int port = 5001;

        try {
            String exit = "exit";
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("<<< Server is ready! >>>");

            Socket clientSocket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));


            while (true) {
                StringBuffer sb = new StringBuffer(reader.readLine());
                if (sb.equals(exit)) {
                    System.out.println("Client left the chat");
                    System.out.println("<<< Server is over! >>>");
                    clientSocket.close();
                    serverSocket.close();
                    reader.close();
//                    writer.close();
                } else {
                    System.out.println("Client write : " + sb);
//                    writer.write("From server -> " + sb + "\n" );
//                    writer.flush();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
