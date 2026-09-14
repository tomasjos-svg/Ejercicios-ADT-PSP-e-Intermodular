package HilosSimples;

public class Hilo2 implements Runnable{
	   public void run()    {
	      int i=0;
	     System.out.println("Inicio " +Thread.currentThread().getName());
	     while(i<5) {
	       try  {
	          Thread.sleep(5000);    
	          System.out.println(Thread.currentThread().getName() +i);
	       }
	      catch(InterruptedException e)  {
	      }
	      i++;
	   }
	    System.out.println("Finaliza " + Thread.currentThread().getName());
	 }
	}
