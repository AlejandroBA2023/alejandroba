import java.io.DataInputStream;
import java.io.DataOutputStream;
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
                System.out.println("Esperando al cliente numero " + (i+1));
                cli[i] = srv.accept();
                System.out.println("El cliente " + (i+1) + " se ha conectado");
                System.out.println("-----------------------------------------");

            }

            for (Socket socket : cli) {
                new DataOutputStream( socket.getOutputStream()).writeUTF("Te cierro");
                new DataOutputStream( socket.getOutputStream()).writeUTF("CLOSE");
                socket.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
