package ProductorConsumidor;

public class Consumidor implements Runnable{
    private final Caja caja;
    private final int codc; 
    public Consumidor(Caja caja, int codc)      {
        this.caja = caja;
        this.codc = codc;
    } 
    @Override
    public void run()     {
        while(true)        {
            System.out.println("Valor " + caja.getValor() + "consumido por  consumidor " + codc);
        }
    }
}      
