package Ejemplo_factoria_hilos_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrincipalEjecutor {
    public static void main(String[] args) {
        ExecutorService ejecutor;
        ejecutor = Executors.newFixedThreadPool( 3,
                new MiThreadFactory()
        );
        for (int i = 1; i <= 6; i++) {
            Tarea t = new Tarea(i);
            ejecutor.execute(t);
        }
        ejecutor.shutdown();
        System.out.println("Tareas enviadas al ejecutor");
    }
}
