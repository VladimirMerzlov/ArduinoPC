import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {

    public static void main(String[] args) {
        String host = "192.168.3.3";
        String hostlocal = "localhost";
        int port = 5001;
        try {
            String exit = "exit";
            System.out.println("<<< Client is ready >>>");
            Socket socket = new Socket(host, port);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println(" [Enter text] : ");
                StringBuffer sb = new StringBuffer(console.readLine());


                if (sb.equals(exit) ) {
                    System.out.println("Left the chat");
                    socket.close();
                    writer.close();

                    console.close();
                    break;
                }else{
                    writer.write(sb + "\n");
                    writer.flush();
//                    System.out.println(reader.readLine());

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

