package Ejemplo_envio_objetos_socket;

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(5001);
            System.out.println("Servidor esperando objeto...");
            Socket cliente = servidor.accept();
            ObjectInputStream entrada =
                    new ObjectInputStream(cliente.getInputStream());

            Alumno alumno = (Alumno) entrada.readObject();
            System.out.println("Objeto recibido:");
            System.out.println(alumno);
            entrada.close();
            cliente.close();
            servidor.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
