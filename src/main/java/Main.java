import jssc.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConnectionArduino connectionArduino = new ConnectionArduino("/dev/ttyUSB0",
                9600, 8, 1);
        SerialPort sp = connectionArduino.createSerialPort();
        Scanner sc = new Scanner(System.in);


        try {

            label_1:
            while (sc.hasNext()) {
                String s = sc.nextLine();
                switch (s) {
                    case "on":
                        sp.writeString("1");
                        //serialPort.writeString("1");
                        break;
                    case "off":
                        sp.writeString("0");
                        //serialPort.writeString("0");
                        break;

                    case "exit":
                        sp.writeString("0");
                        sp.closePort();
                        break label_1;
                    default:
                        System.out.println(s + " не является командой");
                        break;
                }
            }

        } catch (SerialPortException e) {
            e.printStackTrace();
        }

    }
}