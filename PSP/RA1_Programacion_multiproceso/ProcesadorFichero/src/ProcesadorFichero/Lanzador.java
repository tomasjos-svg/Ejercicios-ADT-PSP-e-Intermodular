package ProcesadorFichero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class Lanzador {
    public static void main(String[] args) throws IOException, InterruptedException {
        String entrada = args[0];           
        String ruta = "C:\\Users\\tomas\\eclipse-workspace_r\\eclipse-workspace-docente-reorganizado\\PSP\\"
        		+ "RA1_Programacion_multiproceso\\ProcesadorFichero\\src\\ProcesadorFichero\\";
        String[] consonantes = { "B", "C", "D", "F", "G","H","J","K", "L","M","N","Ñ","P","Q","R","S","T","V","W","X","Y","Z" };         
         for (int i = 0; i < consonantes.length; i++) {
            String err = ruta +"_Errores_" + consonantes[i] + ".txt";
            ProcessBuilder p;
            p = new ProcessBuilder("java", ruta+"ProcesadorFichero.java", ruta+entrada, consonantes[i], ruta+consonantes[i] + ".txt");
            p.redirectOutput(new File(ruta + "salida.txt"));
            p.redirectError(new File(err));
            p.start();        
        }        
        Thread.sleep(5000);
        for (int i=0; i<consonantes.length;i++) {
            String f=ruta+consonantes[i]+".txt";
            File archivo = new File(f);
            if (!archivo.exists()) {
                System.out.println("No se ha creado el fichero de la letra " + consonantes[i]);
                continue;
            }
            BufferedReader b=new BufferedReader(new FileReader(f));
            String n=b.readLine();
            System.out.println("el numero de " + consonantes[i] + " es de "+ n);
            b.close();
        }
    }    
}
