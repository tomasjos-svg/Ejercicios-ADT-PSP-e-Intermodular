package Hilos_22;

public class Hilo2 implements Runnable{
	   public void run()    {
	      int i=0;
	     System.out.println("Inicio " +Thread.currentThread().getName());
	     while(true) {
	    	  int a=(int)(Math.random()*20)+1;
	          System.out.println(Thread.currentThread().getName()+ " "+ a);
	          try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   }
	    
	 }
	}
