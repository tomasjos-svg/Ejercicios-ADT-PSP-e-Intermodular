package Hilos36;

public class Principal {
	public static void main(String[] args)  {
		SeccionCritica sc=new SeccionCritica();
		Hilo_productor hilo_productor =new Hilo_productor(sc,"productor");
		Hilo_consumidor hilo_consumidor = new Hilo_consumidor(sc, "consumidor");
		hilo_productor.start();
		hilo_consumidor.start();
			
	}
 
}
