package multiplicador;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
/*
public class Iniciador {
    public void iniciarMultiplicador(Integer n1,
                        Integer n2,String  f)    {
         String   clase = "C:\\Users\\tomas\\eclipse-workspace_r\\eclipse-workspace-docente-reorganizado\\PSP\\RA1_Programacion_multiproceso\\Multiplicador\\src\\multiplicador\\Multiplicador.java";
         ProcessBuilder pb = null;
             
               
      try {
             pb = new ProcessBuilder("java",clase, n1.toString(), n2.toString());
            // pb.directory(new File( "c:\\users\\tomas\\Documents\\"));
             pb.redirectError(new File("errores.txt"));
             pb.redirectOutput(new  File(f));
             pb.start();
           } catch ( Exception e) {
                 e.printStackTrace();
           }
        }
        public static void main(String[] args){
               Iniciador l=new Iniciador();
              l.iniciarMultiplicador(1, 5,"f1.txt");
              l.iniciarMultiplicador(5, 10,"f2.txt");
              System.out.println("Ok");
        }
}
*/
/*
public class Iniciador {
    public void iniciarMultiplicador(Integer n1,Integer n2)    {
         String   clase = "C:\\Users\\tomas\\eclipse-workspace_r\\eclipse-workspace-docente-reorganizado\\"
         		+ ""
         		+ "PSP\\RA1_Programacion_multiproceso\\Multiplicador\\src\\multiplicador\\Multiplicador.java";
         ProcessBuilder pb;
          try {
             pb = new ProcessBuilder("java",clase,
              n1.toString(), n2.toString());
           pb.directory(new File("c:\\users\\tomas\\Documents\\"));
           pb.redirectError(new File("errores.txt"));
           pb.redirectOutput(Redirect.INHERIT);
           pb.start();
           } catch ( Exception e) 
           {
                 e.printStackTrace();
           }
        }
        public static void main(String[] args){
               Iniciador l=new Iniciador();
              l.iniciarMultiplicador(1, 5);
              l.iniciarMultiplicador(5, 10);
              System.out.println("Ok");
        }
}
*/

public class Iniciador {
    public void iniciarMultiplicador(Integer n1,
                        Integer n2)    {
         String   clase = "C:\\Users\\tomas\\eclipse-workspace_r\\eclipse-workspace-docente-reorganizado\\"
         		+ "PSP\\RA1_Programacion_multiproceso\\Multiplicador\\src\\multiplicador\\Multiplicador.java";
         ProcessBuilder pb;
          try {
              pb = new ProcessBuilder("java",clase,n1.toString(), n2.toString());
              pb.redirectError(new File("errores.txt"));
              Process p= pb.start();
              InputStreamReader s = new  InputStreamReader(p.getInputStream(),"UTF-8");
              BufferedReader b=new BufferedReader(s);
              String salida=b.readLine();
              while ((salida!= null)&&(salida.length()!=0)) {
                  System.out.println(salida);  
                  salida=b.readLine();
              }
              p.getInputStream().close(); 
          }
              catch ( Exception e) 
              {
                    e.printStackTrace();
               }
           }
        public static void main(String[] args){
              Iniciador l=new Iniciador();
              l.iniciarMultiplicador(1, 5);
              l.iniciarMultiplicador(5, 10);
              System.out.println("Ok");
        }
}

