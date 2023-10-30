package ejerciciosClase.Ejer1;
import java.io.*;
import java.net.*;

public class Srv {

    public static void main(String argv[]) {

        // Definimos los Sockets:
        // ----------------------
        //
        // Socket de escucha del servidor
        ServerSocket servidor; 
        // Socket para atender a un cliente
        Socket cliente; 
        
        int numCliente = 0; // Contador de clientes 
        int PUERTO = 5000; // Puerto para esuchar
        System.out.println("Soy el servidor y empiezo a escuchar peticiones por el puerto: " + PUERTO);

        try {
            // Apertura de socket para escuhar a través de un puerto
            servidor = new ServerSocket(PUERTO);
            
            // Atendemos a los clientes
            //     + En la realidad por cada cliente lanzaríamos un hilo 
            do {
                System.out.println("\t Llega el cliente: " + ++numCliente);
                                
                // Aceptamos la conexión
                cliente = servidor.accept();
                
                // Creamos la salida hacia el cliente y la usamos
                DataOutputStream ps = new DataOutputStream(cliente.getOutputStream());
                ps.writeUTF("Usted es mi cliente: "+numCliente);
                
                // Y cerramos la conexión porque ya no vamos a operar más con él
                cliente.close();
                
                System.out.println("\t Se ha cerrado la conexión con el cliente: " +numCliente);
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
