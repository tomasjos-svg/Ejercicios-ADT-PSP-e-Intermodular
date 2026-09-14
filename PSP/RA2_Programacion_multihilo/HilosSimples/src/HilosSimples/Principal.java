package HilosSimples;

public class Principal{
    public static void main(String[] args)  {
       Hilo1 uno = new Hilo1("uno");
       Thread dos= new Thread(new Hilo2(),"dos");
       uno.setPriority(Thread.MIN_PRIORITY);
       dos.setPriority(Thread.MAX_PRIORITY);
       uno.start();
       dos.start();
       uno.isAlive();
       dos.isAlive();
       HiloUnion unionuno = new HiloUnion("uno",1);
       HiloUnion uniondos = new HiloUnion("dos",2);
           unionuno.start();
           uniondos.start();   
        try{
            unionuno.join(); 
            uniondos.join();
            System.out.println("unión de hilos realizada");
        } 
        catch (Exception e)
        {
           System.out.println("error:" + e.getMessage());
        }
      }

     
}
