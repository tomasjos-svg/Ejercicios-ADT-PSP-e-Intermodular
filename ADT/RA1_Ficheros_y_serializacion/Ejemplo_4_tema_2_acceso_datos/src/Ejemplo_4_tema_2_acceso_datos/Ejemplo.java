package Ejemplo_4_tema_2_acceso_datos;
import java.util.Scanner;
public class Ejemplo {
     public static void main(String[] args) {
        int a;
        int b;
        int res;        
        Scanner teclado = new Scanner (System.in);        
        System.out.print("Valor de a: ");
        a = teclado.nextInt();
        if (a == 13) try {
            throw new ExcepcionVerificada("13 está  prohibido !!!");
        } catch (ExcepcionVerificada ex) {
            System.out.println("Error: " + ex.getMessage());
            return;
        }        
        System.out.print("Valor de b: ");
        b = teclado.nextInt();
        if (b == 13) {
            throw new ExcepcionNoVerificada("13 está prohibido !!!");
        }      
        res = a * b;        
        System.out.println("Resultado: " + res);
    }    
}
