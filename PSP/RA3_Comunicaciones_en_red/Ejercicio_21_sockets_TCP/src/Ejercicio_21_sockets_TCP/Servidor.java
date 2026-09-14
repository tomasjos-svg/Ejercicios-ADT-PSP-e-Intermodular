package Ejercicio_21_sockets_TCP;

import java.io.IOException;
import java.net.ServerSocket;



public class Servidor {

    private static ListaUsuarios lista;
    private static ListaCalificaciones listac;
    private static SeccionCritica sc;
    
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(1250);

            sc=new SeccionCritica();
          //  cargarListaUsuarios();
          //  cargarListaCalificaciones();

            System.out.println("Servidor iniciado en el puerto 1250.");
            new Hilo_productor(servidor.accept(),sc).start();
            while (true) {
                new Hilo(servidor.accept(),sc).start();
                
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}