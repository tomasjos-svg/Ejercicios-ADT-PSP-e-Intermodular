package Hilos33;

public class Hilo extends Thread{
    private String nombre;
    private ContadorSeguro seccioncritica;
    public Hilo(String string,ContadorSeguro sc) {
        this.nombre=string;
        this.seccioncritica=sc;
     }
     public void run() {
         int i=0;   
         System.out.println("Ejecutando " + nombre);
         while(true) {
        	
             this.seccioncritica.incrementar(nombre);
             
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