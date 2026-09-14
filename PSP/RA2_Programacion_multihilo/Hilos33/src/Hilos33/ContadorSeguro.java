package Hilos33;

public class ContadorSeguro {

    private int valor = 0;

    public synchronized void incrementar(String nombreHilo) {
        valor++;
        System.out.println(nombreHilo + " incrementa a " + valor);
    }

    public int getValor() {
        return valor;
    }
}