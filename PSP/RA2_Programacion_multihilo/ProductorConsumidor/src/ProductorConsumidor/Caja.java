package ProductorConsumidor;

public class Caja {
    private int valor;
    private boolean llena = false; 
    public synchronized int getValor()  {
        while (!llena)   {
            try   {
                wait();
            } 
            catch (InterruptedException e)  {            
                System.err.println("getValor:" +
                                            e.getMessage());
            }
        }
        llena = false;
        notify();
        return valor;
    } 
  public synchronized void putValor(int v) {
         while (llena)  {
            try      {
                wait();
            } 
            catch (InterruptedException e) {
                System.err.println("putValor"+   e.getMessage());
            }
        }
        valor = v;
        llena = true;
        notify();
    }
}
