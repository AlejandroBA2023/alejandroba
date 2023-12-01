package UT4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sdhcp {
    private static final String IPSERVER = "192.168.60.79";
    private static List<String> ipList = new ArrayList<>();
    private static final String ROUTER = "192.168.10.1";
    private static final String MASK = "255.255.255.0";
    private static final String DNS = "8.8.8.8";
    private static final int PORT = 67;
    private static final int TIEMPOCESION = 60;
    private static final int TIEMPORENOVACION = TIEMPOCESION / 2;
    private static int cont = 0;

    public static void main(String[] args) {

        createIpList(10, 20);
        DatagramSocket srv = getSocket();


        while (true) {
            byte[] msgCliente = recibirPeticion(srv); 
            byte[] transactionID = Arrays.copyOfRange(msgCliente, 4, 8);
            byte[] mac = Arrays.copyOfRange(msgCliente, 28, 44);
            int rawOption = findEndMagicCookie(msgCliente);

            byte[] option = sacarOpcion(msgCliente, rawOption, 53);
            System.out.println(option[0]);

            switch (option[0]) {
                case 1:
                //1, es un discover, se responde con un offer
                    enviarRespuesta(transactionID, mac, srv, (byte)2);
                    System.out.println("id: " + transactionID);
                    System.out.println("Ip ofrecida: " + ipList.get(cont));
                    break;
                case 3:
                //3, es un request, se responde con un ack
                    enviarRespuesta(transactionID, mac, srv, (byte)5);
                    System.out.println("id: " + transactionID);
                    System.out.println("Ip definitiva: " + ipList.get(cont));
                    cont++;
                    break;
            }
        }


    }



    private static byte[] sacarOpcion(byte[] msgCliente, int inicio, int cod) {
        byte[] opcion = null;
        int longitud;
        for (int i = inicio; i < msgCliente.length && opcion == null; ++i) {
            //lee la opcion, si no coincide con el codigo solicitado
            //lee esa opcion para pasar a la siguiente
            if (msgCliente[i] == cod) {
                longitud = (int)msgCliente[i + 1];
                opcion = Arrays.copyOfRange(msgCliente, i + 2, i+2+longitud);
            }else{
                longitud = (int)msgCliente[i + 1];
                i+=1+longitud;
            }
        }
        return opcion;
    }// sacarOpcion()

    private static int findEndMagicCookie(byte[] msgCliente) {
        //Devuelve la posicion del array en la que empizan
        //las opciones (el fin de la magic cookie)
        int endMC = -1;
        for (int i = 0; i < msgCliente.length && endMC == -1; ++i) {
            if (msgCliente[i] == 99 && msgCliente[i + 1] == -126
            && msgCliente[i + 2] == 83 && msgCliente[i + 3] == 99) {
                endMC = i + 4;
            }
        }
        return endMC;
    }// findEndMagicCookie()

    private static byte[] recibirPeticion(DatagramSocket s) {
        byte[] buffer = new byte[576];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        try {
            System.out.println("ESPERANDO CLIENTE...");
            s.receive(paquete);
            buffer = paquete.getData();
        } catch (IOException e) {
            System.out.println(e);
        }
        return buffer;
    }

    private static DatagramSocket getSocket(){
        DatagramSocket srv = null;
        try {
            srv = new DatagramSocket(PORT);
            System.out.println("SERVIDOR CONECTADO\n-------------------");
            
        } catch (SocketException e) {
            System.err.println("No se ha podido abrir el servidor");
            System.exit(2);
        }
        return srv;
    }

    private static void createIpList(int min, int max) {
        if (min < 10 || max > 255) {
            System.err.println("El rango de las IPs es incorrecto");
            System.exit(1);
        }
        for (int i = min; i<max; i++){
            ipList.add("192.168.10." + i);
        }
    }

    private static void enviarRespuesta(byte[] transactionID, byte[] MAC, DatagramSocket s, byte type) {
        //Segun si es un offer o un ack recibe por parametro (type),
        //el byte necesario que compone la respuesta adecuada
        ByteBuffer Offer = ByteBuffer.allocate(576);
        
        //CABECERA
        //Message type
        Offer.put((byte)2);
        //Hardware type
        Offer.put((byte)1);
        //Hardware address length
        Offer.put((byte)6);
        //Hops
        Offer.put((byte)0);
        Offer.put(transactionID);
        //secs
        Offer.putShort((short)0);
        //flags
        Offer.putShort((short)1);
        //Ip cliente
        Offer.put(new byte[4]);
        //Ip ofrecida
        Offer.put(toByteArray(ipList.get(cont)));
        //Ip siguiente DHCP server
        Offer.put(new byte[4]);
        //Ip relay agent
        Offer.put(new byte[4]);
        Offer.put(MAC);
        //Server hostname
        Offer.put(new byte[64]);
        //Boot filename
        Offer.put(new byte[128]);
        byte[] magicCookie = {99,(byte)130,83,99};
        
        //OPTIONS
        Offer.put(magicCookie);
            //Opcion                //Length                //Contenido opcion
        Offer.put((byte)53);    Offer.put((byte)1);     Offer.put(type);
        Offer.put((byte)1);     Offer.put((byte)4);     Offer.put(toByteArray(MASK));
        Offer.put((byte)6);     Offer.put((byte)4);     Offer.put(toByteArray(DNS));
        Offer.put((byte)3);     Offer.put((byte)4);     Offer.put(toByteArray(ROUTER));
        Offer.put((byte)51);    Offer.put((byte)4);     Offer.put((byte)TIEMPOCESION);
        Offer.put((byte)58);    Offer.put((byte)4);     Offer.put((byte)TIEMPORENOVACION);
        Offer.put((byte)54);    Offer.put((byte)4);     Offer.put(toByteArray(IPSERVER));
        Offer.put((byte)255);
        
        send(Offer, s);
    }// enviarRespuesta()

    private static void send(ByteBuffer Offer, DatagramSocket s) {
        //Envia la respuesta al cliente mediante broadcast
        try {
            InetAddress BROADCAST = InetAddress.getByName("255.255.255.255");
            DatagramPacket paquete = new DatagramPacket(Offer.array(), Offer.array().length, BROADCAST, 68);
            s.send(paquete);
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }// send()

    
    private static byte[] toByteArray(String StringToByte) {
        //Transforma una direccion en un array de bytes
        byte[] convertido = null;
        try {
            convertido = InetAddress.getByName(StringToByte).getAddress();
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
        return convertido;
    }// toByteArray

}
