package Ejemplo_tipos_atomicos;

import java.util.concurrent.atomic.AtomicInteger;

public class Contador {
    private AtomicInteger valor;
    public Contador() {
        valor = new AtomicInteger(0);
    }
    public void incrementar() {
        valor.incrementAndGet();
    }
    public int getValor() {
        return valor.get();
    }
}
