package Ejemplo_Future_Cancelacion;

import java.util.concurrent.Callable;

public class TareaLarga implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Trabajando... " + i);
            Thread.sleep(1000);
        }
        return "Tarea terminada";
    }
}