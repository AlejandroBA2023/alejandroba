import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    volatile static Socket srv;

    public static void main(String[] args) {
        InetAddress ip;
        ObjectInputStream input;
        int PUERTO = 51324;
        byte addr[] = { (byte) 127, (byte) 0, (byte) 0, (byte) 1 };

        try {
            ip = InetAddress.getByAddress(addr);
            srv = new Socket(ip, PUERTO);

            System.out.println("Conexion realizada");

            SendMessages sendMessages = new SendMessages(srv.getOutputStream());
            sendMessages.start();

            input = new ObjectInputStream(srv.getInputStream());

            while (sendMessages.isAlive()) {
                var message = input.readObject();

                if (message.getClass().getName() == new String().getClass().getName()) {
                    if (message.equals("quit")) {
                        System.out.println("-------------------");
                        System.out.println("Se ha cerrado la conexion, pulsa intro para cerrar");
                        sendMessages.end();

                    } else {
                        System.out.println("-------------------");
                        System.out.println("Has recibido un nuevo mensaje: ");
                        System.out.println(message);

                    }
                } else {
                    System.out.println("-------------------");
                    System.out.println("Has recibido una persona");
                    System.out.println(((Persona) message).getName());

                }
            }

            input.close();
            srv.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

/**
 * SendMessages
 */
class SendMessages extends Thread {
    private Scanner sc;
    private volatile boolean stop;
    private ObjectOutputStream output;

    public SendMessages(OutputStream output) throws IOException {
        sc = new Scanner(System.in);
        this.output = new ObjectOutputStream(output);
        stop = false;
    }

    @Override
    public void run() {
        String command;
        while (!stop) {
            System.out.println("----------------");
            System.out.println("Introduce un comando: (sms, persona, quit)");
            command = sc.nextLine();
            try {
                switch (command) {
                    case "sms":
                        System.out.println("----------------");
                        System.out.println("Introduce el mensaje:");
                        Mensaje message = new Mensaje("sms", sc.nextLine());
                        output.writeObject(message);
                        break;

                    case "quit":
                        output.writeObject(new Mensaje(command, "Se ha cerrado la conexion"));
                        end();
                        break;

                    case "persona":
                        System.out.println("----------------");
                        System.out.println("Introduce el nombre de la persona:");
                        output.writeObject(new Mensaje(command, new Persona(sc.nextLine())));
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                ;
            }

        }
    }

    public synchronized void end() {
        try {
            stop = true;
            output.close();
            sc.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
/*
 * class ReadMessages extends Thread {
 * ObjectInputStream dis;
 * Socket srv;
 * 
 * public ReadMessages(Socket srv) {
 * try {
 * this.srv = srv;
 * dis = new ObjectInputStream(srv.getInputStream());
 * 
 * } catch (IOException e) {
 * ;
 * }
 * }
 * 
 * @Override
 * public void run() {
 * String message;
 * while (!srv.isClosed()) {
 * try {
 * message = dis.readUTF();
 * if (message.equals("CLOSE")) {
 * srv.close();
 * } else {
 * System.out.println(message);
 * }
 * } catch (IOException e) {
 * ;
 * }
 * }
 * }
 * }
 */