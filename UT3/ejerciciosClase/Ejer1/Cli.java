import java.io.*;
import java.net.*;

public class Cli {

    public static void main(String argv[]) {
        // Definimos los parámetros de conexión
        InetAddress direccion; // La IP o Dirección de conexión
        Socket servidor; // Socket para conectarnos a un servidor u otra máquina
        int numCliente = 0; // Mi número de cliente
        int PUERTO = 5000;  // Puerto de conexión
        
        System.out.println("Soy el cliente e intento conectarme");
        
        try {
            // Vamos a indicar la dirección de conexión
            //     dirección local (localhost)
            // direccion = InetAddress.getLocalHost(); 
            //     por String url o por IP como sigue
            byte addr[] = { (byte)127, (byte)0, (byte)0, (byte)1 };
            direccion = InetAddress.getByAddress( addr );
            // Nos conectamos al servidor: dirección y puerto
            servidor = new Socket(direccion, PUERTO); 
            // Operamos con la conexión. En este caso recibimos los datos que nos mandan
            System.out.println("Conexión realizada con éxito");
            
            // Es inputStream porque los recibimos
            DataInputStream datos = new DataInputStream(servidor.getInputStream());
            // Si queremos leer normal
            //System.out.println(datos.readLine());
            // Si leemos con formato
            System.out.println(datos.readUTF());
            // Cerramos la conexión
            servidor.close();
            System.out.println("Soy el cliente y cierro la conexión");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}