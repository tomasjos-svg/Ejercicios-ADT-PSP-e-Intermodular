package Ejemplo_envio_objetos_socket;

import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) {

        try {
            Socket cliente = new Socket("localhost", 5001);

            ObjectOutputStream salida =
                    new ObjectOutputStream(cliente.getOutputStream());

            Alumno alumno =
                    new Alumno("Ana", "García López", 20);

            salida.writeObject(alumno);
            salida.flush();
/* uso de reset para evitar que vuelva a enviar el mismo objeto. 
 			alumno = new Alumno("Luis", "Pérez", 20);
			salida.reset();
			salida.writeObject(alumno);
			salida.flush();

 
 */
            System.out.println("Objeto enviado:");
            System.out.println(alumno);

            salida.close();
            cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
