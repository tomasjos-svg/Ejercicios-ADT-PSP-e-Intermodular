package Hilos_23;

public class Principal{
    public static void main(String[] args)  {
       Hilo1 uno = new Hilo1("uno");
       Thread dos= new Thread(new Hilo2(),"dos");
       Hilo3 tres=new Hilo3("tres");
       uno.setPriority(1);
       dos.setPriority(1);
       tres.setPriority(1);
       uno.start();
       dos.start();
       tres.start();
       uno.isAlive();
       dos.isAlive();
       tres.isAlive();
       
       
          
       
      }

     
}