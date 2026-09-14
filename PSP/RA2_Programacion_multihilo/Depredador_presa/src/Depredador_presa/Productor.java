package Depredador_presa;

import java.util.Random;

public class Productor extends Thread {
    
    private final SeccionCritica seccioncritica;
    private final String nombre;
    
        public Productor(SeccionCritica sc, String name)     {
        this.seccioncritica = sc;
        this.nombre = name;
        
        
    } 
    public void run()  {
    	Random r=new Random();
    	int espacio;
        do  {
            int s = r.nextInt(11)+20;
            System.out.println("Espacio recorrido por el depredador " + s);
        	espacio=this.seccioncritica.espacio_recorrido_depredador(s);
        	
            try {
                Thread.sleep(1000);
            } 
            catch (InterruptedException e) {
               System.err.println("Productor " + this.nombre +
                e.getMessage());
            }
        }while((espacio>0)&&(espacio<200));
        if (espacio<=0) {
        System.out.println("Presa capturada");
        }
        else {
        	System.out.println("Presa se escapo");
        }
    }
}
