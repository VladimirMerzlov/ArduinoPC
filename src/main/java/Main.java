import jssc.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SerialPort serialPort = new SerialPort("/dev/ttyUSB0");

        try {
            serialPort.openPort();

            System.out.println("Соединение : " + serialPort.isOpened());
            Thread.sleep(5000);
            serialPort.setParams(9600, 8, 1, SerialPort.PARITY_NONE);

            label_1:
            while (sc.hasNext()) {
                String s = sc.nextLine();
                switch (s) {
                    case "on":
                        serialPort.writeString("1");
                        break;
                    case "off":
                        serialPort.writeString("0");
                        break;

                    case "exit":
                        serialPort.writeString("0");
                        serialPort.closePort();
                        break label_1;
                    default:
                        System.out.println(s + " не является командой");
                        break;
                }
            }

        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}