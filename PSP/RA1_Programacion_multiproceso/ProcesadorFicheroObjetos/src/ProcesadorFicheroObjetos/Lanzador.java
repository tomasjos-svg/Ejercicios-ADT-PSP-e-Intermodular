package ProcesadorFicheroObjetos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
public class Lanzador {
    public static void main(String[] args) throws IOException, InterruptedException {
                
        String ruta = "C:\\Users\\tomas\\eclipse-workspace\\ProcesadorFicheroObjetos\\src\\ProcesadorFicheroObjetos\\";
        String[] modulos = { "PSP", "AD", "PMDM", "IW"};         
         for (int i = 0; i < modulos.length; i++) {
        	System.out.println("iniciando proceso de lectura de fichero " + modulos[i]);
            String err = ruta +"_Errores_" + modulos[i] + ".txt";
            ProcessBuilder p;
            p = new ProcessBuilder("java", "-cp", "bin", "ProcesadorFicheroObjetos.ProcesadorFichero", ruta+modulos[i]+".dat");
           // p.redirectOutput(new File(ruta + "salida_" + modulos[i] + ".txt"));
            p.redirectOutput(Redirect.INHERIT);
            p.redirectError(new File(err));
            p.start();      
            System.out.println("Proceso lanzado para lectura de " + modulos[i]);
        }        
        Thread.sleep(5000);
      /*  for (int i=0; i<modulos.length;i++) {
            String f=ruta+"salida_" + modulos[i]+".txt";
            File archivo = new File(f);
            if (!archivo.exists()) {
                System.out.println("No se ha creado el fichero de la letra " + modulos[i]);
                continue;
            }
            BufferedReader b=new BufferedReader(new FileReader(f));
            String n=b.readLine();
            while (n!=null)
            {
            	System.out.println(n);
            	n=b.readLine();
            }
            b.close();
        } */
    }    
}
