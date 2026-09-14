package Productor_consumidor_tipos_atomicos;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
public class Productor implements Runnable {
    private BlockingQueue<Integer> cola;
    private AtomicInteger contador;
    private Random random;
    public Productor(BlockingQueue<Integer> cola,
                     AtomicInteger contador) {
        this.cola = cola;
        this.contador = contador;
        this.random = new Random();
    }
    @Override
    public void run() {
        while (true) {
            try {
                int valor = random.nextInt(100);
                cola.put(valor);
                int total = contador.incrementAndGet();
                System.out.println(
                    "Productor produce: " + valor +
                    " total producidos: " + total
                );
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
