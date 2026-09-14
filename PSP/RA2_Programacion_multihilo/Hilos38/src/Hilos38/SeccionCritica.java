package Hilos38;

class SeccionCritica {
    private double resultado = 0;

    public synchronized void sumar(double cantidad) {
        resultado += cantidad;
    }

    public synchronized double getResultado() {
        return resultado;
    }
}