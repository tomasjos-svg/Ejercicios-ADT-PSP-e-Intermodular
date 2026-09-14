package Ejemplo_cliente_TCP;

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
	
	String usuario, contraseña,respuesta;
	try {
		cliente = new Socket(InetAddress.getLocalHost(), 5005);   
		entrada = new DataInputStream (cliente.getInputStream());  
		salida = new DataOutputStream(cliente.getOutputStream());
		usuario = "Pepe";
		salida.writeUTF(usuario);  
		contraseña = "pepe_02";
		salida.writeUTF(usuario); 
		respuesta = entrada.readUTF();                            
		System.out.println("Mi mensaje: " + usuario + " " + contraseña);
		System.out.println("Respuesta del Servidor: " + respuesta);
		cliente.close();                                          
	} catch (IOException e) {
		System.out.println("Error: " + e.getMessage());
	}
}
}
