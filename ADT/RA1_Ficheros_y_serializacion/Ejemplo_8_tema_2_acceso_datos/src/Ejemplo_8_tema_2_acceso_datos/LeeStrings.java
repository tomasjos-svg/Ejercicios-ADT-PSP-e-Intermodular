package Ejemplo_8_tema_2_acceso_datos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class LeeStrings {
 public static void main(String[] args) throws IOException {    
        String str,num;
        String sal="";
        double x;
        BufferedReader ent = new BufferedReader(new FileReader("C:\\Users\\tomas\\eclipse-workspace_r\\"
        		+ "eclipse-workspace-docente-reorganizado\\ADT\\RA1_Ficheros_y_serializacion\\"
        		+ "Ejemplo_8_tema_2_acceso_datos\\d2.txt"));
        String texto="";
        String linea=ent.readLine();  
        while(linea!=null) {
             texto=texto+linea +"\n";
             linea=ent.readLine();
        }
         System.out.println(texto);
         
        BufferedWriter out=new BufferedWriter (new FileWriter(".\\archivo.txt",true));   
        texto="Hola mundo";
        for(int i=0;i<texto.length();i++)   {
        	 out.write(texto.charAt(i));       
         }
        texto="empiezo otra linea";
        out.newLine(); //equivale a usar File().append();
        out.write(texto);
        out.close();
        ent.close();
      } // main
} // close
