package Ejemplo_lock;


public class Hilo extends Thread {
    private Contador contador;
    private String nombre;
    public Hilo(Contador contador, String nombre) {
        this.nombre=nombre;
        this.contador = contador;
    }
    @Override
    public void run() {
        for(int i=0; i<5; i++) {
            contador.incrementar();
            System.out.println("Hilo " + this.nombre + " ha incrementado en una unidad el contador");
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}