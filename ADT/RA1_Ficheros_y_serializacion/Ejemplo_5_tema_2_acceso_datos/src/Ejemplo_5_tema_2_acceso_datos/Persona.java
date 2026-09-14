package Ejemplo_5_tema_2_acceso_datos;

public class Persona {
private int edad;
public void escribeEdad (int edadn) throws excepcionEdad{
   if (edadn<0 || edadn>100) 
       throw new excepcionEdad("error");
   edad=edadn;
} }
