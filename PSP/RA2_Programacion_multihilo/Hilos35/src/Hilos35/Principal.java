package Hilos35;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Principal {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce el numero de vehiculos: ");
        int numeroVehiculos = teclado.nextInt();

        System.out.print("Introduce el numero de puestos de peaje: ");
        int numeroPuestos = teclado.nextInt();

        Peaje peaje = new Peaje(numeroPuestos);

        Semaphore semaforo = new Semaphore(numeroPuestos, true);

        Vehiculo[] vehiculos = new Vehiculo[numeroVehiculos];

        for (int i = 0; i < numeroVehiculos; i++) {
            vehiculos[i] = new Vehiculo(
                    "Vehiculo " + (i + 1),
                    peaje,
                    semaforo
            );

            vehiculos[i].start();
        }

        for (int i = 0; i < numeroVehiculos; i++) {
            try {
                vehiculos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Caja final: " + peaje.getCaja() + " euros");

        teclado.close();
    }
}