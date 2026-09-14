package Ejemplo_4_tema_3_acceso_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo {
public static void main(String[] args) {
    try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conexion = DriverManager.getConnection( "jdbc:oracle:thin:@//localhost:1521/XEPDB1", "alumno", System.getenv().getOrDefault("DB_PASSWORD", "password"));          
         Statement sentencia = conexion.createStatement();
          String dml = "insert into ALUMNOS (dni, nombre, apellidos, numero_matricula) values ('1234','JUAN',  'GOMEZ',1243)";
          int registros = sentencia.executeUpdate(dml);
           System.out.println("Reg. Afectados: " + registros);
           sentencia.close();
           conexion.close();
           } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: "
           + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getErrorCode() + " - " + ex.getMessage());
        }
}}
