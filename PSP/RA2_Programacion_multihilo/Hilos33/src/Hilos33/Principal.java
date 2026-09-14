package Hilos33;


public class Principal{
    public static void main(String[] args)  {
       ContadorSeguro contadorseguro=new ContadorSeguro();
      
       Hilo uno = new Hilo("uno", contadorseguro);
       Hilo dos = new Hilo("dos", contadorseguro);
       Hilo tres = new Hilo("tres", contadorseguro);
       Hilo cuatro = new Hilo("cuatro", contadorseguro);
       uno.start();
       dos.start();
       tres.start();
       cuatro.start();
     
       
      
       
      }
     }