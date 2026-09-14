package Hilos41;

import java.util.Random;

public class Tarea implements Runnable {
    private int codigo;
    public Tarea(int codigo) {
        this.codigo = codigo;
    }
    @Override
    public void run() {
    	Random r=new Random();
        System.out.println(
            "Ejecutando tarea " + codigo +  " en hilo " +   Thread.currentThread().getName()
        );
        try {
        	int t=r.nextInt(500,1500);
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finaliza tarea " + codigo);
    }
}
