package Hilos33;

public class Lector extends Thread{
    private final SeccionCritica seccioncritica;
    private final String codc; 
    public Lector(SeccionCritica sc, String codc)      {
        this.seccioncritica = sc;
        this.codc = codc;
    } 
    @Override
    public void run()     {
        while(true)        {
            System.out.println("Valor " + seccioncritica.getValor() + "consumido por  consumidor " + codc);
            try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}      
