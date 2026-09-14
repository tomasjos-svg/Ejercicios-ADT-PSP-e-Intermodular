package Hilos_22;

public class Hilo3 extends Thread{
    String nombre;
     public Hilo3(String string) {
         nombre=string;
      }
       public void run() {
          int i=0;   
          System.out.println("Inicio " + nombre);
          while(true) {
           System.out.println("este es el hilo " + nombre);
            try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }
        // System.out.println("Finaliza " + nombre);
      }
}
