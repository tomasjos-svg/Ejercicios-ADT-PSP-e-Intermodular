package Hilos33;

public class SeccionCritica {
	private int valor;
    private boolean modificada = false; 
    public SeccionCritica(int v) {
    	this.valor=v;
    }
    public synchronized int getValor()  {
        while (!modificada)   {
            try   {
                wait();
            } 
            catch (InterruptedException e)  {            
                System.err.println("getValor:" +  e.getMessage());
            }
        }
        modificada = false;
        notifyAll();
        return valor;
    } 
  public synchronized void putValor(int v) {
         while (modificada)  {
            try      {
                wait();
            } 
            catch (InterruptedException e) {
                System.err.println("putValor"+   e.getMessage());
            }
        }
        valor = valor+v;
        modificada = true;
        notifyAll();
    }
}
