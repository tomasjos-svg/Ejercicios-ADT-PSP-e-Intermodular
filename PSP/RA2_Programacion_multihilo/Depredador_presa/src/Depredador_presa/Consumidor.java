package Depredador_presa;
import java.util.Random;
public class Consumidor extends Thread{
    private final SeccionCritica seccioncritica;
    private final String nombre;
	
    public Consumidor(SeccionCritica sc, String nombre)      {
        this.seccioncritica = sc;
        this.nombre = nombre;
    } 
    @Override
    public void run(){
    	Random r=new Random();
    	int espacio;
        do{
        	int s= r.nextInt(11)+20;
        	System.out.println("Espacio recorrido por la presa " + s);
        	espacio=this.seccioncritica.espacio_recorrido_presa(s);
        	
            try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
