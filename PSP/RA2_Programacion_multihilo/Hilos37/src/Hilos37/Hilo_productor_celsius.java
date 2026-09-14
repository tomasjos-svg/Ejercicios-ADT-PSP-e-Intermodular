package Hilos37;

import java.util.Random;

public  class Hilo_productor_celsius extends Thread{
	int valor;
	 private final SeccionCritica seccioncritica;
	    private final String nombre;
	    
	    public Hilo_productor_celsius(SeccionCritica sc, String name)     {
	        this.seccioncritica = sc;
	        this.nombre = name;
	    } 
	        
	    public void run()  {
	    	Random r=new Random();
	    	   	
	    	while (true) {
	    		int t=r.nextInt(-273,1000);
		    	seccioncritica.putCelsius(t);
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
