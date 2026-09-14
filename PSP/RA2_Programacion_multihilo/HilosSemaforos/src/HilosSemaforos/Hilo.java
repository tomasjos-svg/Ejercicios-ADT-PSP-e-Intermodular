package HilosSemaforos;

import java.util.Random;
public class Hilo extends Thread {
   Clasesemaforo sem;
   String codhilo;
   Hilo(String codhilo, Clasesemaforo sem) {
      this.sem=sem;
      this.codhilo=codhilo;
   }
   @Override
   public void run() {
      if(codhilo.equals("lector"))   {
         while(true){
            int temp = sem.muestraDatos();
            System.out.println("El lector ha leído: " + temp);
            try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                 e.printStackTrace();
            }
         }
      }
      else 
      {
         String color = "verde";
         Random r= new Random();
         while (true) {
           if (color.equals("verde")) {
              color = "rojo";
           } else {
              color = "verde";
           }
           int temperatura = r.nextInt(41);
           sem.cambiadatos(color,temperatura);
           System.out.println("El modificador cambió color y temperatura: " + color + " " + temperatura);
           try {
             Thread.sleep(2000);
           } catch (InterruptedException e) {
              e.printStackTrace();
           }
         }
      }
   }
}
   
