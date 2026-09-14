package Hilos_23;

public class Hilo2 implements Runnable{
	   public void run()    {
		   int i=0;   
	          int distancia=0;
	          System.out.println("Inicio " + Thread.currentThread().getName());
	          while(distancia<100) {
	            int velocidad=(int)(Math.random()*2)+8;
	            distancia=distancia+velocidad;
	            if (distancia>100) {
	            	distancia=100;
	            }
	            System.out.println(Thread.currentThread().getName()+ ": espacio recorrido "+ " " + distancia);
	            try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	   }
	          System.out.println("Finaliza " + Thread.currentThread().getName());
	 }
	}
