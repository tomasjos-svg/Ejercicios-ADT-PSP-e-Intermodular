package Ejemplo_servidor_atiende_multiples_hilos_cliente;

import java.io.*;
import java.net.*;
public class Servidor{
 public static void main(String[] args) {
    try  {
    ServerSocket servidor = new ServerSocket(1234);
      while(true)  {
           new Hilo(servidor.accept()).start();
      }
    }
    catch(IOException e)    {     }
  }
 }
