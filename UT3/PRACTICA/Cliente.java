import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

/**
 * class Cliente
 * 
 * Abre el cliente y se conecta al puerto indicado en la configuracion
 */
public class Cliente {

    public static void main(String[] args) {
        // Variables
        Socket srv;
        InetAddress ip;
        ObjectInputStream input;
        Properties p = getProperties();
        int PUERTO = Integer.parseInt(p.getProperty("PUERTO"));
        byte addr[] = { (byte) 127, (byte) 0, (byte) 0, (byte) 1 };

        try {
            // Conexion al servidor
            ip = InetAddress.getByAddress(addr);
            srv = new Socket(ip, PUERTO);
            System.out.println("Conexion realizada");

            // Hilo para enviar mensajes
            SendMessages sendMessages = new SendMessages(srv.getOutputStream());
            sendMessages.start();

            // Leer mensajes
            input = new ObjectInputStream(srv.getInputStream());

            while (sendMessages.isAlive()) { // Mientras el hilo de enviar mensajes este vivo...
                var message = input.readObject();

                if (message.getClass().getName() == new String().getClass().getName()) { // Compara si el mensaje es un String
                    if (message.equals("quit")) { // Si es el comando quit, cierra el hilo
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
                    System.out.println("Nombre: " + ((Persona) message).getName() + "\nEdad: " + ((Persona) message).getAge());

                }
            }

            input.close();
            srv.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    /**
     * class MessageGestor
     * 
     * Se encarga de gestionar los mensajes de un cliente a otro
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
 * class SendMessages
 * 
 * Se encarga de recoger la entrada del usuario para enviarsela al servidor
 * 
 */
class SendMessages extends Thread {
    // Variables
    private Scanner sc;
    private volatile boolean stop;
    private ObjectOutputStream output;

    // Constructor
    public SendMessages(OutputStream output) throws IOException {
        sc = new Scanner(System.in);
        this.output = new ObjectOutputStream(output);
        stop = false;
    }

    // Metodos
    @Override
    public void run() {
        String command;
        while (!stop) {
            System.out.println("----------------");
            System.out.println("Introduce un comando: (sms, persona, quit)");
            command = sc.nextLine();
            try {
                switch (command) { // Tratamiento del comando
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
                    Persona persona = new Persona();
                        System.out.println("----------------");
                        System.out.print("Introduce el nombre de la persona: ");
                        persona.setName(sc.nextLine());
                        System.out.print("\nIntroduce la edad de la persona: ");
                        persona.setAge(sc.nextInt());
                        System.out.println();
                        output.writeObject(new Mensaje(command, persona));
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                ;
            }

        }
    }

    /**
     * end
     * 
     * Finaliza el hilo
     */
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