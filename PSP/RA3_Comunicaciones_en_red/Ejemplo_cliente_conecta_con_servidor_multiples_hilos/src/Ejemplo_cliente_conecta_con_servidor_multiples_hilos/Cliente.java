package Ejemplo_cliente_conecta_con_servidor_multiples_hilos;

import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) {

        try {

            Socket cliente =
                    new Socket(
                            InetAddress.getLocalHost(),
                            1234);

            BufferedReader entrada =
                    new BufferedReader(
                            new InputStreamReader(
                                    cliente.getInputStream()));

            PrintWriter salida =
                    new PrintWriter(
                            cliente.getOutputStream(),
                            true);

            String mensaje = "IES Santa Clara";

            salida.println(mensaje);

            String respuesta = entrada.readLine();

            System.out.println("Mi mensaje: " + mensaje);
            System.out.println("Respuesta del servidor: " + respuesta);

            cliente.close();

        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}