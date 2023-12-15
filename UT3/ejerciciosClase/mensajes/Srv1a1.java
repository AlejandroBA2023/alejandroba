import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;

public class Srv1a1 {

    public static void main(String argv[]) {
        ServerSocket servidor;
        Socket cliente;

        int numCliente = 0; // Contador de clientes
        int PUERTO = 1234; // Puerto para esuchar
        System.out.println("Soy el servidor y empiezo a escuchar peticiones por el puerto: " + PUERTO);

        try {
            servidor = new ServerSocket(PUERTO);
            do {
                System.out.println("\t Llega el cliente: " + ++numCliente);

                cliente = servidor.accept();

                while (cliente.isConnected()) {

                    // Creamos la salida hacia el cliente y la usamos
                    DataOutputStream ps = new DataOutputStream(cliente.getOutputStream());
                    DataInputStream bis = new DataInputStream(cliente.getInputStream());
                    // ps.writeUTF("asda");new
                    // String(ByteStreams.toByteArray(inputStream),Charsets.UTF_8);

                }
                System.out.println("\t Se ha cerrado la conexión con el cliente: " + numCliente);
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}