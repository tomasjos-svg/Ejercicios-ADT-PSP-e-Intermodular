package Depredador_presa;
import java.util.Random;
public class SeccionCritica {
	private int recorrido_depredador,recorrido_presa;
    private boolean modificada = false; 
    public SeccionCritica() {
    	Random r=new Random();
    	do {
    		this.recorrido_depredador=r.nextInt(201);
    	  	this.recorrido_presa=r.nextInt(201);
    	  	
    	}while((this.recorrido_depredador-this.recorrido_presa)>=0);
    	System.out.println("posicion inicial depredador: "+ this.recorrido_depredador);
	  	System.out.println("posicion inicial presa: "+ this.recorrido_presa);
    	
    }
    public synchronized int espacio_recorrido_depredador(int s)  {
        
        if(((this.recorrido_presa-this.recorrido_depredador)>0) &&((this.recorrido_presa-this.recorrido_depredador)<200)){
        	this.recorrido_depredador=this.recorrido_depredador+s;
            modificada = false;
            notifyAll();
            int espacio=this.recorrido_presa-this.recorrido_depredador;
            System.out.println("espacio entre depredador y presa "+ espacio);
            return espacio;
        }
        else
        {
        	modificada = false;
            notifyAll();
        	return (this.recorrido_presa-this.recorrido_depredador); 
         }
    } 
  public synchronized int espacio_recorrido_presa(int s) {
         
        if(((this.recorrido_presa-this.recorrido_depredador)>0) &&((this.recorrido_presa-this.recorrido_depredador)<200)){
        	this.recorrido_presa=this.recorrido_presa+s;
            modificada = true;
            notifyAll();
            int espacio=this.recorrido_presa-this.recorrido_depredador;
            System.out.println("espacio entre depredador y presa "+ espacio);
            return espacio;
        }
        else
        	return (this.recorrido_presa-this.recorrido_depredador); 
        
    }
}
