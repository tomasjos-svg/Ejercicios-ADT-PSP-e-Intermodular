package Semaforo;

import java.util.concurrent.*; 
class Hilo extends Thread { 
    Semaphore s; 
    String nombre; 
    int valor;
    public Hilo(Semaphore s, String n, int v)  { 
        super(n); 
        this.s = s; 
        this.nombre = n; 
        this.valor=v;
    }    
    @Override
    public void run() { 
        System.out.print(nombre +" espera" );
        System.out.println(" por un permiso"); 
        try {                  
            s.acquire(); 
        } catch (InterruptedException e) { 
               e.printStackTrace(); 
        }               
        System.out.println(nombre + " ha cogido un permiso"); 
        for(int i=0; i < valor; i++)  { 
          boolean b = s.hasQueuedThreads(); 
          if(b) {     
        	  System.out.print("cola con ");
              System.out.println(s.getQueueLength()); }
               try { 
                    Thread.sleep(10); 
                } catch (InterruptedException e) { 
                    e.printStackTrace(); 
                } 
            } 
            System.out.println(nombre + " libera el permiso.");                   
            s.release(); 
        } 
} 
