package Ejemplo_Future_Cancelacion;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrincipalCancelacion {
    public static void main(String[] args)
            throws Exception { 
        ExecutorService ejecutor;
        ejecutor = Executors.newSingleThreadExecutor();
        Future<String> resultado;
        resultado = ejecutor.submit(new TareaLarga());
        Thread.sleep(3000);
        if (!resultado.isDone()) {
            System.out.println("La tarea tarda demasiado");
            resultado.cancel(true);
        }
        if (resultado.isCancelled()) {
            System.out.println("Tarea cancelada");
        }
        ejecutor.shutdown();
    }
}
