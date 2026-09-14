package Ejercicio_10_tema_2_acceso_datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LeeFichero {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		  BufferedReader ent = null;
	      Scanner sc=new Scanner(System.in);
	      try {
	    	  File directorio_prueba=new File(".\\alumnos");
	          File fichero = new File(directorio_prueba,"lista.txt");
	          directorio_prueba.mkdir();
	          fichero.createNewFile();

	          ent = new BufferedReader(new FileReader(".\\alumnos\\lista.txt"));
	          String cadena=null;
	          int lineas=0;
	          int caracteres=0;
	          int palabras=0;
	          cadena=ent.readLine();
	          while(cadena!=null){
	        	  lineas++;
	        	  caracteres=caracteres+cadena.length();
	        	  String[] lista_palabras = cadena.split(" ");
	        	  palabras=palabras+lista_palabras.length;
	        	  if (lineas%20==0) {
	        		  String mensaje=null;
	        		  mensaje=sc.nextLine();
	        		  while (!mensaje.equalsIgnoreCase("continua")) {
	        			  mensaje=sc.nextLine();
	        		  }
	        	  }
	        	  cadena=ent.readLine();	        	  
	          }	          
	          System.out.println(" el numero de lineas es " + lineas);
	          System.out.println(" el número de caracteres es " + caracteres);
	          System.out.println(" el número de palabras  es  " + palabras);
	          
	     } finally {
	       if (ent != null)
	       ent.close();
	       	    		   
	     }          
	      
	       FileWriter out =new FileWriter(".\\alumnos\\lista.txt",true);
	       System.out.println(" escribe un nuevo nombre ");
	       String nombre=sc.nextLine();
	       System.out.println(" escribe un nuevo apellido");
	       String apellido=sc.nextLine();
	       
	       out.append("nombre "+ nombre + " apellidos " + apellido + "\n");
	       out.close();
	}

}
