import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    volatile static Socket srv;

    public static void main(String[] args) {
        InetAddress ip;
        int PUERTO = 51324;
        byte addr[] = { (byte) 127, (byte) 0, (byte) 0, (byte) 1 };

        try {
            ip = InetAddress.getByAddress(addr);
            srv = new Socket(ip, PUERTO);

            System.out.println("Conexion realizada");

            new ReadMessages(srv).start();
            ;
            while (!srv.isClosed()) {

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

/**
 * SendMessages
 */
class SendMessages extends Thread {
    static Scanner sc;

    public SendMessages() {
        sc = new Scanner(System.in);
    }

    @Override
    public void run() {

    }
}

/**
 * Hilo para leer los mensajes
 */
class ReadMessages extends Thread {
    DataInputStream dis;
    Socket srv;

    public ReadMessages(Socket srv) {
        try {
            this.srv = srv;
            dis = new DataInputStream(srv.getInputStream());

        } catch (IOException e) {
            ;
        }
    }

    @Override
    public void run() {
        String message;
        while (!srv.isClosed()) {
            try {
                message = dis.readUTF();
                if (message.equals("CLOSE")) {
                    srv.close();
                } else {
                    System.out.println(message);
                }
            } catch (IOException e) {
                ;
            }
        }
    }
}
