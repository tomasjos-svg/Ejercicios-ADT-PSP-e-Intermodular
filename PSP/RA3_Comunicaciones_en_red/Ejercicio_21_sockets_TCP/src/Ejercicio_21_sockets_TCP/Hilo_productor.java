package Ejercicio_21_sockets_TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Hilo_productor extends Thread {

    private Socket cliente;
    private SeccionCritica sc;
   

    public Hilo_productor(Socket cliente, SeccionCritica sc) {
        this.cliente = cliente;
        this.sc=sc;
    }

    @Override
    public void run() {
       while (true) {
    	   sc.cargarListaCalificaciones();
    	   sc.cargarListaUsuarios();
    	   try {
			Thread.sleep(20000);
		   } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
       }
    }
}