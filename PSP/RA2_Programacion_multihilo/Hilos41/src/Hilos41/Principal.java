package Hilos41;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Principal {
    public static void main(String[] args) {
        ExecutorService ejecutor;
        ejecutor = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            Tarea t = new Tarea(i);
            ejecutor.execute(t);
        }
        ejecutor.shutdown();
        System.out.println("Tareas enviadas al ejecutor");
    }
}
