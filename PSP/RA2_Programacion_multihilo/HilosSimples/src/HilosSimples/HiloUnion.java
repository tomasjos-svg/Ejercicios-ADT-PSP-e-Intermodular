package HilosSimples;

public class HiloUnion extends Thread{
	   private int numhilo;
	   public HiloUnion(String name, int n)   {
	       super(name);
	       this.numhilo=n;     
	   }
	   public void run()   {
	      for (int i=1; i<=numhilo; i++)      {   
	       System.out.println(getName() + ":" + i);
	       try{
	               sleep(2000);
	       }
	       catch(InterruptedException e)
	       {
	            System.out.println("error:" + e.getMessage());
	       }  
	      }
	   }
	}
