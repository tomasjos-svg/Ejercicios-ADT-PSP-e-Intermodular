package Hilos_22;

public class Hilo1 extends Thread{
    String nombre;
     public Hilo1(String string) {
         nombre=string;
      }
       public void run() {
          int i=0;   
          System.out.println("Inicio " + nombre);
          while(true) {
            int a=(int)(Math.random()*122);
            while (a<96) {
            	  a=(int)(Math.random()*122);
            }
            System.out.println(nombre+ " "+(char)a + " " + a);
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
