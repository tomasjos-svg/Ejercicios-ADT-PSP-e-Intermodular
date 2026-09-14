package Hilos38;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Principal {

    public static void main(String[] args) throws InterruptedException {

        int aforo = 3;
        int numeroClientes = 10;

        Semaphore semaforo = new Semaphore(aforo, true);
        SeccionCritica sc = new SeccionCritica();

        ArrayList<Producto> catalogo = new ArrayList<>();

        catalogo.add(new Producto("Pan", "P001", 1.20));
        catalogo.add(new Producto("Leche", "P002", 0.95));
        catalogo.add(new Producto("Huevos", "P003", 2.50));
        catalogo.add(new Producto("Arroz", "P004", 1.80));
        catalogo.add(new Producto("Aceite", "P005", 5.40));

        ArrayList<Cliente> clientes = new ArrayList<>();

        for (int i = 1; i <= numeroClientes; i++) {
            clientes.add(new Cliente("Cliente " + i, semaforo, catalogo, sc));
        }

        for (Cliente c : clientes) {
            c.start();
        }

        for (Cliente c : clientes) {
            c.join();
        }

        System.out.println("--------------------------------");
        System.out.println("Todos los clientes han pasado.");
        System.out.println("Total recaudado: " + sc.getResultado() + " €");
    }
}