package Ejemplo_lock;


public class Principal {
    public static void main(String[] args)
            throws InterruptedException {
        Contador contador = new Contador();
        Hilo h1 =       new Hilo(contador, "Hilo-1");
        Hilo h2 =       new Hilo(contador, "Hilo-2");
        Hilo h3 =       new Hilo(contador, "Hilo-3");
        h1.start();
        h2.start();
        h3.start();
        h1.join();
        h2.join();
        h3.join();
        System.out.println("\nValor final del contador: " + contador.getValor()
        );
    }
}
