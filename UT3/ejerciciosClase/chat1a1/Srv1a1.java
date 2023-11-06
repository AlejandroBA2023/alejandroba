import java.io.*;
import java.net.*;

public class Srv1a1 {

    public static void main(String argv[]) {
        ServerSocket servidor; 
        Socket[] clientes = new Socket[2];
        DataOutputStream[] salidas = new DataOutputStream[2];
        DataInputStream[] entradas = new DataInputStream[2];

        int PUERTO = 1234; // Puerto para esuchar
        try {
            servidor = new ServerSocket(PUERTO);

            for (int i = 0; i < clientes.length; i++) {
                clientes[i] = servidor.accept();
                salidas[i] = new DataOutputStream(clientes[i].getOutputStream());
                entradas[i] = new DataInputStream(clientes[i].getInputStream());
                System.out.println("El cliente " + i + " se ha conectado");
            }
                
            SendMessages m0to1 = new SendMessages(entradas[0], salidas[1]);
            SendMessages m1to0 = new SendMessages(entradas[1], salidas[0]);
            m0to1.start();
            m1to0.start();

            while (true) {
                for (Socket cliente : clientes) {
                    if(cliente.isClosed()){
                        m0to1.end();
                        m1to0.end();

                        break;
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * do {
                                
                // Aceptamos la conexión
                cliente = servidor.accept();
                System.out.println("Cliente conectado");
                DataOutputStream ps = new DataOutputStream(cliente.getOutputStream());
                DataInputStream is = new DataInputStream(cliente.getInputStream());

                while (cliente.isConnected()) {
                    System.out.println(is.readUTF());
                }

                ps.close();
                is.close();
                
                
                System.out.println("\t Se ha cerrado la conexión con el cliente: ");
            } while (true);
    */
}

class SendMessages extends Thread{
    volatile private boolean exit;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public SendMessages(DataInputStream entrada, DataOutputStream salida){
        this.entrada = entrada;
        this.salida = salida;
    }

    @Override
    public void run(){
        exit = false;
        while (!exit) {
            try {
                salida.writeUTF(entrada.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void end(){
        exit = true;
    }
}
