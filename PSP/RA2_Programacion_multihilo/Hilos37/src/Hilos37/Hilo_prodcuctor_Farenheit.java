package Hilos37;

import java.util.Random;

public class Hilo_prodcuctor_Farenheit extends Thread{
	int valor;
	 private final SeccionCritica seccioncritica;
	    private final String nombre;
	    
	    public Hilo_prodcuctor_Farenheit(SeccionCritica sc, String name)     {
	        this.seccioncritica = sc;
	        this.nombre = name;
	    } 
	        
	    public void run()  {
	    	Random r=new Random();
	    	   	
	    	while (true) {
	    		int celsius=seccioncritica.getCelsius();
	    		double fahrenheit = celsius * 9.0 / 5.0 + 32;
		    	seccioncritica.putFarenheit((int)fahrenheit);
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
