package Depredador_presa;


public class Principal{
    public static void main(String[] args)  {
       SeccionCritica seccioncritica=new SeccionCritica();
       Productor depredador = new Productor( seccioncritica, "depredador");
       Consumidor presa = new Consumidor(seccioncritica,"presa");
       
       depredador.start();
       presa.start();
                 
       
       
      }
     
}