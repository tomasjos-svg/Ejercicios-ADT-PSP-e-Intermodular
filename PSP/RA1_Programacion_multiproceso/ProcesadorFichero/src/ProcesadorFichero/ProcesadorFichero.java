package ProcesadorFichero;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class ProcesadorFichero {
public static BufferedReader lectorFicheros(
    String nombreFichero) throws FileNotFoundException {
    FileReader lector;
    lector = new FileReader(nombreFichero);
    BufferedReader lectorfichero;
    lectorfichero = new BufferedReader(lector);
    return lectorfichero;
}
public static PrintWriter escritorFicheros(
    String nombreFichero) throws IOException {
    PrintWriter escribeFichero;
    FileWriter escritorFichero;
    escritorFichero = new FileWriter(nombreFichero);
    escribeFichero = new PrintWriter(escritorFichero);
    return escribeFichero;
}
public static void recuento(String Entrada, String letra, String Salida) throws FileNotFoundException, IOException {
    BufferedReader entrada;
    entrada = lectorFicheros(Entrada);
    PrintWriter salida;
    salida = escritorFicheros(Salida);
    String lineaLeida;
    lineaLeida = entrada.readLine();
    int totalConsonantes = 0;
    //Mientras no queden líneas....
    while (lineaLeida != null) {
    	System.out.println("linea leida");
        //...recorremos la linea...
        for (int i = 0; i < lineaLeida.length(); i++) {
            char letraLeida = lineaLeida.charAt(i);
            System.out.println(lineaLeida.charAt(i));
            char letraPasada = letra.charAt(0);
            // incrementamos el contador
            if (letraLeida == letraPasada) {
                totalConsonantes++;
                System.out.println(i);
            }            
        }            
        lineaLeida = entrada.readLine();    }        
    salida.println(totalConsonantes);
    salida.flush();        
    salida.close();
    entrada.close();
}
public static void main(String[] args) throws FileNotFoundException, IOException {
String entrada = args[0];  
System.out.println(entrada);
String letra = args[1];
System.out.println(letra);
String resultado = args[2];
System.out.println(resultado);
recuento(entrada, letra, resultado);
} 
}
