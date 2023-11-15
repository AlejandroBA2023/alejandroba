import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * class Servidor
 * 
 * Se encarga de establecer el servidor en el puerto elegido y conectar a los
 * dos clientes
 */
public class Servidor {
    public static void main(String[] args) {
        ServerSocket srv;
        Socket[] cli = new Socket[2]; // Numero de clientes
        Properties p = getProperties(); // TODO: Obtener puerto desde un archhivo
        int PUERTO = Integer.parseInt(p.getProperty("PUERTO"));

        try {
            // Se abre el servidor
            srv = new ServerSocket(PUERTO);
            System.out.println("Servidor abierto en el puerto " + PUERTO);
            System.out.println("-----------------------------------------");

            // Se conectan los clientes
            for (int i = 0; i < cli.length; i++) {
                System.out.println("Esperando al cliente numero " + (i + 1));
                cli[i] = srv.accept();
                System.out.println("El cliente " + (i + 1) + " se ha conectado");
                System.out.println("-----------------------------------------");
            }

            // Hilos para gestionar los mensajes de cada cliente
            MessageGestor[] messageGestors = new MessageGestor[2];
            messageGestors[0] = new MessageGestor(cli[0], cli[1]);
            messageGestors[1] = new MessageGestor(cli[1], cli[0]);

            for (MessageGestor messageGestor : messageGestors) {
                messageGestor.start();
            }

            while (messageGestors[0].isAlive() && messageGestors[1].isAlive()) {
                // Este while controla si los hilos siguen vivos
            }

            // Finaliza los hilos restantes
            for (MessageGestor messageGestor : messageGestors) {
                try {
                    messageGestor.end();
                } catch (Exception e) {
                    ;
                }
            }

            srv.close();

        } catch (IOException e) { // En caso de que no se haya podido abrir el servidor:
            System.out.println("No se ha podido abrir el servidor...");
            System.err.println(e.getMessage());

        } catch (IllegalArgumentException e) { // En caso de que el puerto introducido no este en el rango
            System.out.println("El puerto especificado es erroneo, " +
                    "recuerda que el valor del puerto debe ser entre 0 y 65535");
        }
    }

    /**
     * getProperties
     * 
     * Se encarga de obtener el archivo .properties
     * 
     * @return 
     */
    private static Properties getProperties(){
        try { 
            Properties p = new Properties();
            FileInputStream fis = new FileInputStream("config.properties");
            p.load(fis);
            return p;
        } catch (FileNotFoundException e) {
            System.err.println("no se ha encontrado el archivo config.properties");
        } catch (IOException e) {
            System.err.println("No se ha podido cargar el archivo config.properties");
        }
        return null;
    }
}

/**
 * class MessageGestor
 * 
 * Se encarga de gestionar los mensajes de un cliente a otro
 */
class MessageGestor extends Thread {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean stop;

    // Constructor
    public MessageGestor(Socket input, Socket output) {
        try {
            this.input = new ObjectInputStream(input.getInputStream());
            this.output = new ObjectOutputStream(output.getOutputStream());
            stop = false;
        } catch (IOException e) {
            ;
        }
    }

    // Metodo Run
    @Override
    public void run() {
        while (!stop) {
            try {
                Mensaje message = (Mensaje) input.readObject();
                // System.out.println("Mensaje recibido");

                switch (message.getCommand()) { // Compara el mensaje, si es quit, finaliza
                    case "quit":
                        output.writeObject(message.getCommand());
                        end();
                        break;

                    default:
                        output.writeObject(message.getMessage());
                        break;
                }

            } catch (Exception e) {
                end();
            }

        }

    }

    /*
     * Finaliza el hilo y cierra la entrada y salida
     */
    public synchronized void end() {
        try {
            stop = true;
            input.close();
            output.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
