package Ejemplo_3_tema_3_acceso_datos;

import java.sql.*;
public class Ejemplo {
    private static Connection conexion;
    private static String bd="alumno";
    private static String user="alumno";
    private static String password=System.getenv().getOrDefault("DB_PASSWORD", "password");
    private static String host="127.0.0.1:3307";
    private static String server="jdbc:mysql://"+host+"/"+bd;
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection
            (server,user,password);
            System.out.println("Conexión OK");             
        } catch (ClassNotFoundException ex) {
            System.out.println("Error con el driver");
        } catch (SQLException ex) {
            System.out.println("Imposible conectar");             
       }
        try {
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery
            ("select * from profesor");
            while (rs.next())  {
                System.out.println(
                        "nombre: " +rs.getString (2) +
                        "apellido: " + rs.getString (3)+
                        "departamento: " + rs.getString(4) +
                        "DNI:" + rs.getString(1)
                        );
            }             
        }catch (SQLException ex) {
            System.out.println("Imposible consultar");
        }
        try {
            conexion.close();
            System.out.println("Cerrar conexion");
        } catch (SQLException ex) {
            System.out.println("Imposible cerrar ");
        }
    }
  }
