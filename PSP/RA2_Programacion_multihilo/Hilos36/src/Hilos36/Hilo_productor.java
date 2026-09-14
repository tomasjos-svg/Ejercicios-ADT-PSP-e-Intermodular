package Hilos36;

import java.util.Random;
import java.util.Scanner;

public class Hilo_productor extends Thread {
    
    private final SeccionCritica seccioncritica;
    private final String nombre;
    
    public Hilo_productor(SeccionCritica sc, String name)     {
        this.seccioncritica = sc;
        this.nombre = name;
        
        
    } 
        
    public void run()  {
    	
    	Random r=new Random();
    	while (true) {
    		Alumno alumno=new Alumno(generarCadena(10),generarCadena(8),r.nextInt(0,10));
    		seccioncritica.putAlumno(alumno);
    		try {
                Thread.sleep(1000);
            } 
            catch (InterruptedException e) {
               System.err.println("Productor " + this.nombre +
                e.getMessage());
            }
    	}
    	
    }
    
    public static String generarCadena(int longitud) {
    	Random random =new Random();
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }

        return sb.toString();
    }
}
