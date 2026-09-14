package Ejemplo_servidor_atiende_multiples_hilos_cliente;

import java.io.*;
import java.net.*;

public class Hilo extends Thread {
    private Socket cliente;
    public Hilo(Socket cliente) {
        this.cliente = cliente;
    }
    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida =   new PrintWriter(cliente.getOutputStream(), true);
            String recibido = entrada.readLine();
            System.out.println("Entrada: " + recibido);
            String enviado = recibido.toUpperCase();
            System.out.println("Salida: " + enviado);
            salida.println(enviado);
            salida.close();
            entrada.close();
            cliente.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}