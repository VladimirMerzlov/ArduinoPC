import java.io.*;
import java.net.Socket;

public class ClientArduino {
    private String hostClient; // "localhost"
    private int portClient;    // 5000


    public ClientArduino(String hostClient, int portClient) {
        this.hostClient = hostClient;
        this.portClient = portClient;

    }

    public String CreateClient() throws IOException {
        String message = "Client is ready...";
        Socket socket = new Socket(hostClient, portClient);
        System.out.println(message);
        BufferedWriter outMessage = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        outMessage.write(message + "\n");
        outMessage.flush();
        outMessage.close();

        while (true) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            if (in.readLine().equals("exit")) {
                in.close();
                System.out.println("Client is over...");
                break;

            } else {
                return in.readLine();

            }

        }

        socket.close();
        return "exit";
    }


}
