package Productor_consumidor_tipos_atomicos;

import java.util.concurrent.BlockingQueue;

public class Consumidor implements Runnable {
    private BlockingQueue<Integer> cola;
    private int codigo;
    public Consumidor(BlockingQueue<Integer> cola,
                      int codigo) {
        this.cola = cola;
        this.codigo = codigo;
    }
    @Override
    public void run() {
        while (true) {
            try {
                int valor = cola.take();
                System.out.println(
                    "Consumidor " + codigo +
                    " consume: " + valor
                );
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

