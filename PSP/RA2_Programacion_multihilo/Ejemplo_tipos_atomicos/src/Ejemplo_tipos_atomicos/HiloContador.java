package Ejemplo_tipos_atomicos;

public class HiloContador extends Thread {
    private Contador contador;
    public HiloContador(Contador contador) {
        this.contador = contador;
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {        	
            contador.incrementar();
            System.out.println("Contador " + contador.getValor());
            
        }
    }
}
