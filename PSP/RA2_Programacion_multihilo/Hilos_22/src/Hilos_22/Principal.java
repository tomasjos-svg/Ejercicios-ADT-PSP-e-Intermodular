package Hilos_22;


public class Principal{
    public static void main(String[] args)  {
       Hilo1 uno = new Hilo1("uno");
       Thread dos= new Thread(new Hilo2(),"dos");
    //   Hilo3 tres=new Hilo3("tres");
       uno.setPriority(1);
       dos.setPriority(7);
     //  tres.setPriority(4);
       uno.start();
       dos.start();
     //  tres.start();
       uno.isAlive();
       dos.isAlive();
   //    tres.isAlive();
       
          
       
      }

     
}