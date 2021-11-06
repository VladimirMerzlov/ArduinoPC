import jssc.*;

import java.io.IOException;
import java.util.Scanner;
/* проверить настройки портов,хоста и т.д.*/

public class Main {

    public static void main(String[] args) {
        String portNameUbuntu = "/dev/ttyUSB0";     // Физические порты для
        String portNameWindows = "COM3";            //   подключения Arduino
        String hostClient = "localhost";            // Настройки host и port для
        int portClient = 5000;                      //  поключения сервера


        ConnectionArduino connectionArduino = new ConnectionArduino(portNameWindows,
                9600, 8, 1);
        SerialPort sp = connectionArduino.createSerialPort();
        ClientArduino clientArduino = new ClientArduino(hostClient, portClient);

        try {
            String sc = clientArduino.CreateClient();

            label_1:
            while (true) {
                switch (sc) {
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
                        System.out.println(sc + " не является командой");
                        break;
                }
            }

        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}