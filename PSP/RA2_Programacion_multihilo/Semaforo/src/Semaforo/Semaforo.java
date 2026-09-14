package Semaforo;

import java.util.concurrent.Semaphore;

public class Semaforo  
{ 
   public static void main(String args[]) throws  InterruptedException    { 
      Semaphore s = new Semaphore(3, true); 
      s.tryAcquire(2); 
      System.out.print(" permisos disp: ");
      System.out.println(s.availablePermits()); 
      System.out.println("todos los permisos retirados: " + s.drainPermits());           
      s.release(1); 
      Hilo h1 = new Hilo(s, "Hilo 1",10); 
      Hilo h2 = new Hilo(s, "Hilo 2",20);          
      h1.start(); 
      h2.start(); 
        //toString devuelve la identificacion del
        //semaforo
      System.out.println(s.toString()); 
      h1.join(); 
      h2.join(); 
    } 
}
