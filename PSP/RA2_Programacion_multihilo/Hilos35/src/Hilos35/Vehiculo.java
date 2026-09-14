package Hilos35;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Vehiculo extends Thread {

    private String nombre;
    private Peaje peaje;
    private Semaphore semaforo;

    public Vehiculo(String nombre, Peaje peaje, Semaphore semaforo) {
        this.nombre = nombre;
        this.peaje = peaje;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {

        int puesto = -1;

        try {
            System.out.println(nombre + " esperando puesto de peaje");

            semaforo.acquire();

            puesto = peaje.ocuparPuesto();

            Random r = new Random();

            double cantidad = r.nextInt(16) + 5;

            peaje.pagar(nombre, puesto, cantidad);

            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {

            if (puesto != -1) {
                peaje.liberarPuesto(puesto);
            }

            semaforo.release();

            System.out.println(nombre + " abandona el peaje");
        }
    }
}