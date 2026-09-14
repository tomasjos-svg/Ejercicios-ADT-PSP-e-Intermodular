package Ejemplo_Callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrincipalCallable {
    public static void main(String[] args)
            throws Exception {
        ExecutorService ejecutor;
        ejecutor = Executors.newFixedThreadPool(2);
        Future<Integer> resultado1;
        Future<Integer> resultado2;
        resultado1 = ejecutor.submit(
            new SumaTarea(5, 7)
        );
        resultado2 = ejecutor.submit(
            new SumaTarea(10, 20)
        );
        System.out.println("Tareas lanzadas");
        System.out.println(
            "Resultado 1: " + resultado1.get()  );
        System.out.println(
            "Resultado 2: " + resultado2.get() );
        ejecutor.shutdown();
    }
}
