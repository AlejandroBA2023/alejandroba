import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cli {
    volatile static boolean pararLectura = false;

    public static void main(String argv[]) {
        // Definimos los parámetros de conexión
        InetAddress direccion; // La IP o Dirección de conexión
        Socket servidor; // Socket para conectarnos a un servidor u otra máquina
        int numCliente = 0; // Mi número de cliente
        int PUERTO = 1234;  // Puerto de conexión
        
        System.out.println("Soy el cliente e intento conectarme");
        
        try {
            Scanner sc = new Scanner(System.in);
            String mensaje;
            boolean exit = false;
            byte addr[] = { (byte)127, (byte)0, (byte)0, (byte)1 };
            direccion = InetAddress.getByAddress( addr );
            // Nos conectamos al servidor: dirección y puerto
            
            servidor = new Socket(direccion, PUERTO); 
            System.out.println("Conexión realizada con éxito");
            DataOutputStream salida = new DataOutputStream(servidor.getOutputStream());
            Runnable leerMensajes = () -> { 
                try {
                    DataInputStream entrada = new DataInputStream(servidor.getInputStream());
                    while (!pararLectura) {
                        System.out.println("\t\t\t" + entrada.readUTF());
                        Thread.sleep(2000);
                    }
                } catch (Exception e){;}
            };
            new Thread( leerMensajes ).start();

            while (!exit) {
                mensaje = sc.nextLine();

                if (mensaje.equals("EXIT")){
                    exit = true;
                    pararLectura = true;
                } else {
                    salida.writeUTF(mensaje);
                }
            }
            salida.close();
            sc.close();
            servidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}