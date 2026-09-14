package ProductorConsumidor;

import java.util.Random;

public class Productor implements Runnable {
    private final Random random;
    private final Caja caja;
    private final int codp;
    private final int retardo= 1500;
        public Productor(Caja caja, int codp)     {
        this.caja = caja;
        this.codp = codp;
        random = new Random();
    } 
    public void run()  {
        while(true)   {
            int poner = random.nextInt(300);
            this.caja.putValor(poner);
            System.out.println("Valor " + poner +  " introducido por el producto" + this.codp);
            try      {
                Thread.sleep(retardo);
            } 
            catch (InterruptedException e) {
               System.err.println("Productor " + this.codp +
                e.getMessage());
            }
        }
    }
}
