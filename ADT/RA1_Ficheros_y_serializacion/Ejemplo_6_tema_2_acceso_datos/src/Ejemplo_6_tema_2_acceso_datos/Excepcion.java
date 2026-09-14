package Ejemplo_6_tema_2_acceso_datos;

import java.io.IOException;

public class Excepcion {
public static void main(String[] args) throws IOException {
      int a=Integer.parseInt(args[1]);
      double b=Double.parseDouble(args[2]);	                   
      EscribeFichero.ejemploEscribeFichTexto(args[0], a, b, args[3]);  
    }
}
