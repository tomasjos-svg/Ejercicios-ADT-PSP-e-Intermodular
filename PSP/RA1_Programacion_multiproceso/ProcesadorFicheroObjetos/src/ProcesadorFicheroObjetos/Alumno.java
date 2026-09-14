package ProcesadorFicheroObjetos;

import java.io.Serializable;

public class Alumno implements Serializable {
  public Alumno(String nombre, String dni, double calificacion) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.calificacion = calificacion;
	}
  private static final long serialVersionUID = 1L;
  String nombre;
  String dni;
  double calificacion;
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
  public double getCalificacion() {
	return calificacion;
  }
  public void setCalificacion(double calificacion) {
	this.calificacion = calificacion;
  }
  
}
