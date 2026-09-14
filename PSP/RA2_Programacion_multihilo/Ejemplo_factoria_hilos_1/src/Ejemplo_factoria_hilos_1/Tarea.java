package Ejemplo_factoria_hilos_1;

public class Tarea implements Runnable {
    private int codigo;
    public Tarea(int codigo) {
        this.codigo = codigo;
    }
    @Override
    public void run() {
        System.out.println(  "Ejecutando tarea "  + codigo
            + " en hilo "  + Thread.currentThread().getName()  );
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(  "Finaliza tarea " + codigo );
    }
}
