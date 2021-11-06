import jssc.*;

public class ConnectionArduino {

    private String portName;
    private int baudRate;
    private  int dataBits;
    private int stopBits;
    private int parity = SerialPort.PARITY_NONE;

    public ConnectionArduino(String portName, int baudRate, int dataBits, int stopBits) {
        this.portName = portName;
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;


    }

    public  SerialPort createSerialPort (){
        SerialPort serialPort = new SerialPort(this.portName);
        try {

            serialPort.openPort();
            System.out.println("Соединение : " + serialPort.isOpened());
            Thread.sleep(5000);
            serialPort.setParams(this.baudRate, this.dataBits, this.stopBits, this.parity);

        }
        catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serialPort;

    }
}
