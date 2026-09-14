package Ejemplo_lock;

import java.util.concurrent.locks.ReentrantLock;
public class Contador {
        private int valor = 0;
    private final ReentrantLock lock = new ReentrantLock();
    public void incrementar() {
        lock.lock(); // adquirir el lock
        try {
            valor++;
        } finally {
            lock.unlock(); // liberar siempre el lock
        }
    }
    public int getValor() {
        return valor;
    }
}
