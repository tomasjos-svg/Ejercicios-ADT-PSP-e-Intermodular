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
		String mensaje;
		DataOutputStream salida;
		DataInputStream entrada;
		boolean correcto=false;
		int num = 0;
		try { 
			servidor = new ServerSocket(5005);
			System.out.println("Servidor Arrancado correctamente");
			while (true) {
				conexion = servidor.accept();     
				num++;
				System.out.println("Conexión número" + num +" desde: " + conexion.getInetAddress() .getHostName());
				entrada = new DataInputStream(conexion.getInputStream());  
				salida = new DataOutputStream(conexion.getOutputStream());
				String usuario = entrada.readUTF();
				String contraseña = entrada.readUTF();
				
				System.out.println(usuario);
				System.out.println(contraseña);
				try {
				    Thread.sleep(2000); // 2000 ms = 2 segundos
				} catch (InterruptedException e) {
				    e.printStackTrace();
				}
				if ((usuario.equalsIgnoreCase("Pepe")) &&(contraseña.equalsIgnoreCase("pepe_01"))){
					correcto=true;
				}
				if (correcto) {
					mensaje ="bienvenido " + usuario;
				}
				else {
					mensaje="error en usuario o contraseña";
				}
				
				System.out.println("Conexión n." + num + "mensaje: " + mensaje);
				salida.writeUTF("Bienvenidos al servidor TCP " + mensaje);  
				conexion.close();                     
			}
		} catch (IOException e) { }
	}
}
