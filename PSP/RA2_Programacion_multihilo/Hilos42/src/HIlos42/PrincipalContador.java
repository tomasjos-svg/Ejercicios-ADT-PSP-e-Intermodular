package HIlos42;

public class PrincipalContador {
    public static void main(String[] args)
            throws InterruptedException {
        Contador c = new Contador();
        HiloContador h1 = new HiloContador(c);
        HiloContador h2 = new HiloContador(c);
        HiloContador h3= new HiloContador(c);
        HiloContador h4 = new HiloContador(c);
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h1.join();
        h2.join();
        h3.join();
        h4.join();
        System.out.println("Valor final: " + c.getValor());
    }
}
