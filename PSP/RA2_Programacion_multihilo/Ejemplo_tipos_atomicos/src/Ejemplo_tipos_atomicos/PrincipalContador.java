package Ejemplo_tipos_atomicos;

public class PrincipalContador {
    public static void main(String[] args)
            throws InterruptedException {
        Contador c = new Contador();
        HiloContador h1 = new HiloContador(c);
        HiloContador h2 = new HiloContador(c);
        h1.start();
        h2.start();
        h1.join();
        h2.join();
        System.out.println("Valor final: " + c.getValor());
    }
}
