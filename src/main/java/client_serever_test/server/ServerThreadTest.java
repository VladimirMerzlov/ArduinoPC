package client_serever_test.server;


import jssc.SerialPort;
import jssc.SerialPortException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThreadTest extends Thread {
    Socket serverClient;
    String portNameUbuntu = "/dev/ttyUSB0";     // Физические порты для
    String portNameWindows = "COM3";            //   подключения Arduino


    public ServerThreadTest(Socket serverClient) {
        this.serverClient = serverClient;
    }

    @Override
    public void run() {
        try {

            ConnectionArduino connectionArduino = new ConnectionArduino(portNameWindows,
                    9600, 8, 1);
            SerialPort sp = connectionArduino.createSerialPort();
            System.out.println(" <<< Arduino connection is === " + connectionArduino.isOpenPortArduino());

            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            String clientMessage = "";

            label_1:
            while (!clientMessage.equalsIgnoreCase("exit")) {
                clientMessage = inStream.readUTF();
                System.out.println(clientMessage);

                switch (clientMessage) {
                    case "on":
                        sp.writeString("1");
                        break;

                    case "off":
                        sp.writeString("0");
                        break;

                    case "exit":
                        sp.writeString("0");
                        sp.closePort();
                        break label_1;

                    default:
                        System.out.println(clientMessage + " не является командой");
                        break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }


}
