package Ejercicio_21_sockets_TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Socket cliente = new Socket(InetAddress.getLocalHost(), 1250);

            ObjectOutputStream salida =
                    new ObjectOutputStream(cliente.getOutputStream());
            salida.flush();

            ObjectInputStream entrada =
                    new ObjectInputStream(cliente.getInputStream());

            System.out.println("Introduce el usuario:");
            String usuario = sc.nextLine();
            salida.writeObject(usuario);
            salida.flush();

            System.out.println("Introduce la contraseña:");
            String contraseña = sc.nextLine();
            salida.writeObject(contraseña);
            salida.flush();

            String respuesta = (String) entrada.readObject();

            if (respuesta.equals("0")) {
                System.out.println("Usuario o contraseña incorrectos.");

            } else if (respuesta.equals("1")) {
                System.out.println("Bienvenido, usuario " + usuario);

                              
                Calificaciones calificaciones = (Calificaciones)entrada.readObject();
                System.out.println("Calificaciones de " + usuario+ ": psp: "+ calificaciones.getPsp()+ " ad: "+ calificaciones.getAd()+ " pmdm: "+ calificaciones.getPmdm()+ " di: "+ calificaciones.getDi());
                
                
            }

            entrada.close();
            salida.close();
            cliente.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   
}