package HilosSimples;

public class Hilo1 extends Thread{
    String nombre;
     public Hilo1(String string) {
         nombre=string;
      }
       public void run() {
          int i=0;   
          System.out.println("Inicio " + nombre);
          while(i<5) {
          try  {
             Thread.sleep(5000);
             System.out.println(nombre +i);
          }
          catch(InterruptedException e) {
          }
          i++;
         }
         System.out.println("Finaliza " + nombre);
      }
}
