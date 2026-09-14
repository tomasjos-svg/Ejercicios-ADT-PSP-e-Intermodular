package Ejemplo_6_tema_2_acceso_datos;

import java.io.IOException;
import java.io.FileWriter;
public class EscribeFichero {
  static void ejemploEscribeFichTexto(String nomFich,
	  int i, double x, String str) throws IOException {
      FileWriter out = null;
      try {
      out = new FileWriter("c:\\users\\tomas\\downloads\\"+ nomFich);
      out.write("Entero: "+i+" Real: "+x+"\n");
      out.write("String: "+str);
     } finally {
       if (out != null)
       out.close();
     }
  }
}
