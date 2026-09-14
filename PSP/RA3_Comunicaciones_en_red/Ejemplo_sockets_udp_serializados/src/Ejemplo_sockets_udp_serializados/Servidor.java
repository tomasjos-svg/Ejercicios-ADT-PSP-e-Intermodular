package Ejemplo_sockets_udp_serializados;

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        try {
            DatagramSocket socket =
                    new DatagramSocket(6000);
            byte[] buffer = new byte[4096];
            DatagramPacket paquete =
                    new DatagramPacket(buffer, buffer.length);
            System.out.println("Servidor UDP esperando objeto...");

            socket.receive(paquete);
            ByteArrayInputStream bais =
                    new ByteArrayInputStream(
                            paquete.getData(),
                            0,
                            paquete.getLength());
            ObjectInputStream ois =
                    new ObjectInputStream(bais);
            Alumno alumno =
                    (Alumno) ois.readObject();
            System.out.println("Objeto recibido por UDP:");
            System.out.println(alumno);
            ois.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
