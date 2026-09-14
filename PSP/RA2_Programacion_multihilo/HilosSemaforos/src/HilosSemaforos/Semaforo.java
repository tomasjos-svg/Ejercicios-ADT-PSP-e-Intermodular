package HilosSemaforos;

public class Semaforo {
public static void main(String[] args)  {
 Clasesemaforo semaforo = new Clasesemaforo ("verde");
 System.out.println("“El semaforo empieza en color verde");

 Hilo  lector = new Hilo("lector",     semaforo);
 Hilo modif = new Hilo("modificador", semaforo);
 lector.start();
 modif.start();
 System.out.println("fin del  programa");
 }
} 
