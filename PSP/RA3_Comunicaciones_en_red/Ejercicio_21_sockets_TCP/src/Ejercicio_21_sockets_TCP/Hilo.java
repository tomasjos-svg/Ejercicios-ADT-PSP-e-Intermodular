package Ejercicio_21_sockets_TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Hilo extends Thread {

    private Socket cliente;
    private SeccionCritica sc;
    private ListaUsuarios listau;
    private ListaCalificaciones listac;

    public Hilo(Socket cliente, SeccionCritica sc) {
        this.cliente = cliente;
        this.sc=sc;
        this.listau=null;
        
        
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream salida =
                    new ObjectOutputStream(cliente.getOutputStream());
            salida.flush();

            ObjectInputStream entrada =
                    new ObjectInputStream(cliente.getInputStream());

            String nombre = (String) entrada.readObject();
            String contraseña = (String) entrada.readObject();
            
            System.out.println(nombre + " " + contraseña);
            
            listau=sc.devolverListaUsuarios();

            Usuario usuario = listau.autenticar(nombre, contraseña);
            if (usuario!=null) {
            	System.out.println(usuario.getNombre()+ " "+ usuario.getContraseña());
            }
            else {
            	System.out.println("no encuentro el usuario");
            }
            
            try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            if (usuario == null) {
                salida.writeObject("0");
                salida.flush();
            }  else {
                salida.writeObject("1");
                salida.flush();
                listac= sc.devolverListaCalificaciones();
                Calificaciones calificaciones=listac.buscar(usuario.getDNI());
                salida.writeObject(calificaciones);               
            }

            entrada.close();
            salida.close();
            cliente.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}