package Ejemplo_sockets_udp_serializados;

import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            Alumno alumno =
                    new Alumno("Pedro", "Martínez Ruiz", 21);
            ByteArrayOutputStream baos =
                    new ByteArrayOutputStream();
            ObjectOutputStream oos =
                    new ObjectOutputStream(baos);
            oos.writeObject(alumno);
            oos.flush();
            byte[] datos = baos.toByteArray();
            InetAddress direccion =
                    InetAddress.getByName("localhost");
            DatagramPacket paquete =
                    new DatagramPacket(
                            datos,
                            datos.length,
                            direccion,
                            6000);
            socket.send(paquete);
            System.out.println("Objeto UDP enviado:");
            System.out.println(alumno);
            oos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
