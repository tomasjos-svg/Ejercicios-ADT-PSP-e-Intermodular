package Ejemplo_inetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
public class Principal {
public static void main(String[] args) {
byte[] dirLoopback = {127, 0, 0, 1}; 
InetAddress host;
try {
     host = InetAddress.getLocalHost();  
     System.out.println("Mi dirección IP es: "+host.getHostAddress());
     System.out.println("Mi nombre es: "+host.getHostName());
     System.out.println("Mi nombre FQDN:  "+host.getCanonicalHostName());
     System.out.println();
     host= InetAddress.getByName("www.transicionestructural.net");
     System.out.println("La dir.IP del servidor de TransicionEstructural es: "+host.getHostAddress());
System.out.println("Su nombre es: "+host.getHostName());
System.out.println("Su nombre FQDN es: " +host.getCanonicalHostName());
System.out.println();
     host = InetAddress.getByAddress(dirLoopback);
System.out.println("La dirección IP de mi equipo es: "+host.getHostAddress());
System.out.println();
} catch (UnknownHostException e) {
System.out.println("Error de conexión");
System.out.println(e.toString());
}
}
}
