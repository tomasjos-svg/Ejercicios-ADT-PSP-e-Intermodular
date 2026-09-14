package Hilos38;

import java.util.*;
import java.util.concurrent.Semaphore;

class Cliente extends Thread {

    private Semaphore semaforo;
    private ArrayList<Producto> catalogo;
    private SeccionCritica sc;
    private Random random = new Random();

    public Cliente(String nombre, Semaphore semaforo,
                   ArrayList<Producto> catalogo,
                   SeccionCritica sc) {
        super(nombre);
        this.semaforo = semaforo;
        this.catalogo = catalogo;
        this.sc = sc;
    }

    public void run() {
        try {
            System.out.println(getName() + " espera para entrar.");

            semaforo.acquire();

            System.out.println(getName() + " entra en la tienda.");

            ArrayList<Compra> compras = new ArrayList<>();

            int numeroCompras = random.nextInt(1, 4);

            for (int i = 0; i < numeroCompras; i++) {
                Producto p = catalogo.get(random.nextInt(catalogo.size()));
                int cantidad = random.nextInt(1, 6);
                compras.add(new Compra(p, cantidad));
            }

            double total = 0;

            System.out.println("Compras de " + getName() + ":");

            for (Compra c : compras) {
                double subtotal = c.getTotal();
                total += subtotal;

                System.out.println(
                    c.producto.nombre + " x " + c.cantidad +
                    " = " + subtotal + " €"
                );
            }

            System.out.println(getName() + " paga " + total + " €");

            sc.sumar(total);

            Thread.sleep(random.nextInt(1000, 3000));

            System.out.println(getName() + " sale de la tienda.");

        } catch (InterruptedException e) {
            System.out.println(getName() + " fue interrumpido.");
        } finally {
            semaforo.release();
        }
    }
}