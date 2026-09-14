package ProductorConsumidor;

public class ProductorConsumidor {
    private static Caja caja;
    private static Thread p;
    private static Thread [] cs;
    private static final int numc = 5;    
    
    public static void main(String[] args)    {
        caja = new Caja();
        p=new Thread(new Productor(caja,1));
        cs= new Thread[numc]; 
        for(int i = 0; i < numc; i++)        {
           cs[i] = new Thread(new Consumidor(caja, i));
            cs[i].start();
        }         
        p.start();
    }    
}
