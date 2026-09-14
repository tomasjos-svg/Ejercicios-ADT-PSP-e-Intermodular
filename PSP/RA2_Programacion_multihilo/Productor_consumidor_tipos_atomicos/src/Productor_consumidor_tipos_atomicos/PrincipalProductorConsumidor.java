package Productor_consumidor_tipos_atomicos;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class PrincipalProductorConsumidor {
    public static void main(String[] args) {
        BlockingQueue<Integer> cola;
        cola = new ArrayBlockingQueue<Integer>(5);
        AtomicInteger contador;
        contador = new AtomicInteger(0);
        Thread productor;
        productor = new Thread(
            new Productor(cola, contador)
        );
        Thread consumidor1;
        consumidor1 = new Thread(
            new Consumidor(cola, 1)
        );
        Thread consumidor2;
        consumidor2 = new Thread(
            new Consumidor(cola, 2)
        );
        productor.start();
        consumidor1.start();
        consumidor2.start();
    }
}
