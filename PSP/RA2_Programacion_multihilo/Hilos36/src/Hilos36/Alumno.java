package Hilos36;

public class Alumno {
	String nombre;
	String dni;
	int calificacion;
	public Alumno(String name, String id, int qualif) {
		this.nombre=name;
		this.dni=id;
		this.calificacion=qualif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
}
