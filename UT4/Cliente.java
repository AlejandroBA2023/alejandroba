package UT4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();
            socket.setBroadcast(true); // Habilita la difusión

            byte[] mensaje = {1, 1, 6, 0, 0, 0, 0, 0, 0, 0}; // Mensaje DHCPDISCOVER

            InetAddress direccion = InetAddress.getByName("255.255.255.255"); // Dirección de difusión

            DatagramPacket paquete = new DatagramPacket(mensaje, mensaje.length, direccion, 67);
            socket.send(paquete);

            System.out.println("Mensaje DHCPDISCOVER enviado.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
