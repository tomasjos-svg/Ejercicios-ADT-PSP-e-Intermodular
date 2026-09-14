package Ejemplo_cliente_TCP;

import java.util.Random;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
public class Principal {
public static void main(String args[]) {
	Socket cliente;
	DataInputStream entrada;
	DataOutputStream salida;
	String mensaje, respuesta;
	try {
		Random r = new Random();
		cliente = new Socket(InetAddress.getLocalHost(), 5002);   
		entrada = new DataInputStream (cliente.getInputStream());  
		salida = new DataOutputStream(cliente.getOutputStream());
		mensaje = " numero" + r.nextInt(1,10);
		salida.writeUTF(mensaje);                                 
		respuesta = entrada.readUTF();                            
		System.out.println("Mi mensaje: " + mensaje);
		System.out.println("Respuesta del Servidor: " + respuesta);
		cliente.close();                                          
	} catch (IOException e) {
		System.out.println("Error: " + e.getMessage());
	}
}
}
