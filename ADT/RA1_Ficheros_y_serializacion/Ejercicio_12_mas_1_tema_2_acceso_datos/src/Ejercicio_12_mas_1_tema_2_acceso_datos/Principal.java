package Ejercicio_12_mas_1_tema_2_acceso_datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
public class Principal {
	public static void main(String args[]){
	      String cad = "entrada de datos";
	      Scanner sc;
	      System.out.println("Escribe un numero real");
	      double numero;
	      int numero_entero;
	      sc=new Scanner(System.in);
	      numero=sc.nextDouble();
	      sc.nextLine();
	      
	      try {
	           FileOutputStream f = new FileOutputStream(".//cadena.txt",true);
	           ObjectOutputStream entrada=new ObjectOutputStream(f);
	           entrada.writeDouble(numero);
	           
	           System.out.println("Introduce números positivos por teclado");
	           numero_entero=sc.nextInt();
	           sc.nextLine();
	           while (numero_entero>0) {
	        	   entrada.write(numero_entero);
	        	   System.out.println("Introduce números positivos por teclado");
		           numero_entero=sc.nextInt();
		           sc.nextLine();
	           }
	           System.out.println("Introduce número real por teclado");  
	           numero=sc.nextDouble();
	           while(numero!=-1.0) {
	        	   entrada.writeDouble(numero);
	        	   System.out.println("Introduce número real por teclado");
		           numero=sc.nextInt();
		           sc.nextLine();
	           }
	           entrada.close();
	           FileInputStream f_o=new FileInputStream(".//cadena.txt");
	           ObjectInputStream salida=new ObjectInputStream(f_o);
	           
	           System.out.println("el contenido del fichero es " + salida.readDouble()); 
	           double numero_salida=salida.readDouble();
	           while (salida.available()>0) {
	        	   System.out.println(numero_salida);
	        	   numero_salida=salida.readDouble();
	           }
	        	   
	           
	        } 
	    catch (IOException e)   {
	       System.out.println("Anomalia en flujo de salida");
	   }
	} 
}
