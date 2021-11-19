package client_serever_test.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTestA {
    private final InetAddress host;
    private final int port;

    public ClientTestA(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    // Создаем сокет,ридер, райтер , и запускаем логику
    public void start() {

        try {

            Socket socket = new Socket(this.host, this.port);
            System.out.println(" <<< Client is ready >>>");
            //Создаем ридер и райтер для обмена сообщениями
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(" Enter the command : ");
            BufferedReader consol = new BufferedReader(new InputStreamReader(System.in));
//проверяем живой ли канал,и если да,то работаем с ним
            while (!socket.isOutputShutdown()) {
                // ждем консоль клиента на предмет появления данных
                if (consol.ready()) {
                    System.out.println(" Выполняем команду "); // !!!
                    Thread.sleep(1000); // !!!
                    String clientCommand = consol.readLine();

                    // отправляем данные с консоли в сокет серверу
                    writer.write(clientCommand);
                    writer.flush();
                    System.out.println("Client sent message " + clientCommand + " to Server.");// !!!
                    Thread.sleep(2000); // Ждем чтобы сервер успел прочесть и ответить

                    // проверяем условия выхода из соединения
                    if (clientCommand.equalsIgnoreCase("quit")) {
                        System.out.println(" connections is over ");
                        Thread.sleep(2000);

                        //Смотрим что на последок ответил сервер
                        if (reader.read() > -1) {
                            System.out.println("reading ...");
                            String in = reader.readLine();
                            System.out.println(in);
                        }
                        //после предварительных приготовлений выходим из цикла записи/чтения
                        break;
                    }
                    // Если условие разъединения не достигнуто - продолжаем работу
                    System.out.println(" Client send message & start waiting for data from server ...");
                    Thread.sleep(2000);
                    //проверяем что ответил сервер
                    if (reader.read() > -1) {
                        //если успел забираем ответ из канала сервера в сокете
                        System.out.println("reading ...");
                        String in = reader.readLine();
                        System.out.println(in);

                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}


