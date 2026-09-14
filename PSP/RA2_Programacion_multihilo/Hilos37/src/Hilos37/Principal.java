package Hilos37;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeccionCritica sc=new SeccionCritica();
        Hilo_prodcuctor_Farenheit hilo_farenheit=new Hilo_prodcuctor_Farenheit(sc, "productor_farenheit");
        Hilo_productor_celsius hilo_celsius = new Hilo_productor_celsius(sc, "productor_celsius");
        Hilo_consumidor hilo_consumidor =new Hilo_consumidor(sc, "consumidor");
        hilo_farenheit.start();
        hilo_celsius.start();
        hilo_consumidor.start();
	}

}
