import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket srv;
        Socket[] cli = new Socket[2];
        int PUERTO = 51324;

        try {
            srv = new ServerSocket(PUERTO);
            System.out.println("Servidor abierto en el puerto " + PUERTO);
            System.out.println("-----------------------------------------");

            for (int i = 0; i < cli.length; i++) {
                System.out.println("Esperando al cliente numero " + (i + 1));
                cli[i] = srv.accept();
                System.out.println("El cliente " + (i + 1) + " se ha conectado");
                System.out.println("-----------------------------------------");
            }

            MessageGestor[] messageGestors = new MessageGestor[2];
            messageGestors[0] = new MessageGestor(cli[0], cli[1]);
            messageGestors[1] = new MessageGestor(cli[1], cli[0]);

            for (MessageGestor messageGestor : messageGestors) {
                messageGestor.start();
            }

            while (messageGestors[0].isAlive() && messageGestors[1].isAlive()) {

            }

            for (MessageGestor messageGestor : messageGestors) {
                try {
                    messageGestor.end();
                } catch (Exception e) {
                    ;
                }
            }

            for (Socket socket : cli) {
                ;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

class MessageGestor extends Thread {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean stop;

    public MessageGestor(Socket input, Socket output) {
        try {
            this.input = new ObjectInputStream(input.getInputStream());
            this.output = new ObjectOutputStream(output.getOutputStream());
            stop = false;
        } catch (IOException e) {
            ;
        }
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                Mensaje message = (Mensaje) input.readObject();
                System.out.println("Mensaje recibido");

                switch (message.getCommand()) {
                    case "quit":
                        end();
                        break;

                    default:
                        output.writeObject(message.getMessage());
                        break;
                }

            } catch (Exception e) {
                System.out.println("super rayao");
                ;
            }

        }

    }

    public synchronized void end() {
        stop = true;
    }
}
