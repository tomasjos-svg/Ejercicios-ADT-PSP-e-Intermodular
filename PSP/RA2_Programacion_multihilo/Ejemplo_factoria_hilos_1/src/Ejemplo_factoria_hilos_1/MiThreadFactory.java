package Ejemplo_factoria_hilos_1;

import java.util.concurrent.ThreadFactory;

public class MiThreadFactory implements ThreadFactory {
    private int contador = 1;
    @Override
    public Thread newThread(Runnable r) {
        Thread hilo = new Thread(r);
        hilo.setName("Trabajador-" + contador);
        contador++;
        return hilo;
    }
}
