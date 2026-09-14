package Ejemplo_servidor_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Principal {
	public static void main(String args[]) {
		ServerSocket servidor;
		Socket conexion;
		DataOutputStream salida;
		DataInputStream entrada;
		int num = 0;
		try { 
			servidor = new ServerSocket(5000);
			System.out.println("Servidor Arrancado orrectamente");
			while (true) {
				conexion = servidor.accept();     
				num++;
				System.out.println("Conexión número" + num +" desde: " + conexion.getInetAddress() .getHostName());
				entrada = new DataInputStream(conexion. getInputStream());  
				salida = new DataOutputStream(conexion. getOutputStream());
				String mensaje = entrada.readUTF();
				System.out.println("Conexión n." + num + "mensaje: " + mensaje);
				salida.writeUTF("Bienvenidos al servidor TCP " + mensaje);  conexion.close();                     
			}
		} catch (IOException e) { }
	}
}
