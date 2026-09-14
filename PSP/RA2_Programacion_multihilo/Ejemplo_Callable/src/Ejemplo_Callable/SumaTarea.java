package Ejemplo_Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class SumaTarea implements Callable<Integer> {
    private int n1;
    private int n2;
    public SumaTarea(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }
    @Override
    public Integer call() throws Exception {
        Thread.sleep(2000);
        return n1 + n2;
    }
}