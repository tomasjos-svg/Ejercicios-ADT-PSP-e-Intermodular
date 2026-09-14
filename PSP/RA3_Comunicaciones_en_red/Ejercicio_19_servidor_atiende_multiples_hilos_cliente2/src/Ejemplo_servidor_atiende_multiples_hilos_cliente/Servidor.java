package Ejemplo_servidor_atiende_multiples_hilos_cliente;

import java.io.IOException;
import java.net.ServerSocket;



public class Servidor {

    private static ListaUsuarios lista;

    public static void inicializarLista() {
        lista.añadir(new Usuario("admin", "admin123", "ADMINISTRADOR"));
        lista.añadir(new Usuario("juan", "juan123", "USUARIO"));
        lista.añadir(new Usuario("maria", "maria123", "USUARIO"));
        lista.añadir(new Usuario("pedro", "pedro123", "USUARIO"));
        lista.añadir(new Usuario("ana", "ana123", "USUARIO"));
    }

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(1250);

            lista = new ListaUsuarios();
            inicializarLista();

            System.out.println("Servidor iniciado en el puerto 1250.");

            while (true) {
                new Hilo(servidor.accept(), lista).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}