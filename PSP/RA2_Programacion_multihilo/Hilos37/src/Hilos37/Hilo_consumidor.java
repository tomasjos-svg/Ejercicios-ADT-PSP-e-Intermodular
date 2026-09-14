package Hilos37;


public class Hilo_consumidor extends Thread {
	private final SeccionCritica seccioncritica;
	 private final String nombre;
	    
	 public Hilo_consumidor(SeccionCritica sc, String name)     {
	    this.seccioncritica = sc;
	    this.nombre = name;
	 } 
	        
	 public void run()  {
	    	int i=0;	
	    	while (true) {
	    	    seccioncritica.showTemperatura();
	    		System.out.println("temperaturas mostradas en el instante " +i);
	    		i++;
	    		try {
	                Thread.sleep(1000);
	            } 
	            catch (InterruptedException e) {
	               System.err.println("Productor " + this.nombre +
	                e.getMessage());
	            }
	    	}
	    	
	 }

}
