package Ejemplo_cliente_conecta_con_servidor_multiples_hilos;

import java.io.Serializable;

public class Usuario implements Serializable {
     String nombre;
     String contraseña;
     String permisos;
     
     public Usuario(String name, String password, String securityLevel) {
    	 this.nombre=name;
    	 this.contraseña=password;
    	 this.permisos=securityLevel;
     }
	 public String getNombre() {
		 return nombre;
	 }
	 public void setNombre(String nombre) {
		 this.nombre = nombre;
	 }
	 public String getContraseña() {
		 return contraseña;
	 }
	 public void setContraseña(String contraseña) {
		 this.contraseña = contraseña;
	 }
	 public String getPermisos() {
		 return permisos;
	 }
	 public void setPermisos(String permisos) {
		 this.permisos = permisos;
	 }
     
}
