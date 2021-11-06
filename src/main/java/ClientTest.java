import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {
    public static void main(String[] args) {
        String message = "Client is ready...";

        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println(message);

            BufferedWriter outMessage = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            outMessage.write(message + "\n");
            outMessage.flush();
           // outMessage.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                if (in.readLine().equals("exit")) {
                    in.close();
                    System.out.println("Client is over...");
                    break;

                } else {
                    System.out.println(in.readLine());

                }

            }

//            socket.close();
            System.out.println("exit");
        } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

}

