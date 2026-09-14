package ProcesadorFicheroObjetos;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import ProcesadorFicheroObjetos.Alumno;
public class ProcesadorFichero {
public static ObjectInputStream lectorFicheros(String nombreFichero) throws IOException {
	FileInputStream lector = null;
    ObjectInputStream lectorO = null;
    lector = new FileInputStream(nombreFichero);
    lectorO = new ObjectInputStream(lector);
    return lectorO;
}
	

public static PrintWriter escritorFicheros( String nombreFichero) throws IOException {
    PrintWriter escribeFichero;
    FileWriter escritorFichero;
    escritorFichero = new FileWriter(nombreFichero);
    escribeFichero = new PrintWriter(escritorFichero);
    return escribeFichero;
}
public static void recuento(String Entrada) throws FileNotFoundException, IOException, ClassNotFoundException {
	ObjectInputStream entrada=null;
	//entrada=lectorFicheros(Entrada);
	ArrayList<Alumno> listaalumnos=new ArrayList<Alumno>();
	FileInputStream lector = null;
	 try {
   // lector = new FileInputStream(Entrada);
   // entrada = new ObjectInputStream(lector);
		 entrada=lectorFicheros(Entrada);
	Alumno a;
	
	System.out.println("Hemos entrado en el proceso de lectura de " + Entrada);
	    
    while (true) {
     //   System.out.println("Nombre: " + a.getNombre()  + " DNI: " + a.getDni() + " Calificación: " + a.getCalificacion());
        a = (Alumno) entrada.readObject();
        listaalumnos.add(a);
    }
    
	 } catch (EOFException e) {
	        System.out.println("Fin del fichero");
	       
	    } catch (Exception e) {
	    	System.out.println("ERROR:");
	        e.printStackTrace();
	    }
	 
	 finally {
		 if (entrada != null) {
		        entrada.close();
		    }
	 }
    for(Alumno a1: listaalumnos) {
    	 System.out.println( "Nombre: " + a1.getNombre()   + " DNI: " + a1.getDni()   + " Calificación: " + a1.getCalificacion() );
    }
    System.out.println("Alumnos leídos: " + listaalumnos.size());
    

    
}
public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
String entrada = args[0];  
System.out.println("entrada en proceso de lectura de fichero " + entrada);
System.out.println(entrada);

recuento(entrada);
} 
}
