package Hilos36;

import java.util.ArrayList;
import java.util.Iterator;

public class SeccionCritica {
	ArrayList<Alumno> alumnos;
	public SeccionCritica() {
		alumnos=new ArrayList<Alumno>();
	}
	public synchronized void putAlumno(Alumno a) {
		alumnos.add(a);
	}
	public synchronized void showAlumnos()
	{
		Iterator it=alumnos.iterator();
		while(it.hasNext()) {
			Alumno a=(Alumno) it.next();
			System.out.println("nombre " + a.getNombre() + " dni " + a.getDni() + " nota " + a.getCalificacion());
		}
	}

}
