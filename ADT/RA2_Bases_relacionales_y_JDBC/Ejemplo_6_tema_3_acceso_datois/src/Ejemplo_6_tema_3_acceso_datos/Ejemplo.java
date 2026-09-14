package Ejemplo_6_tema_3_acceso_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
public class Ejemplo {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "alumno", System.getenv().getOrDefault("DB_PASSWORD", "password"));
            String llamada = "call devuelve_nom(?,?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);
            sentenciaLlamable.setString(1, "2345");
            sentenciaLlamable.registerOutParameter(2, Types.VARCHAR);
            System.out.println("Llamada final: " + sentenciaLlamable.toString());
            sentenciaLlamable.executeUpdate();
            String nombre = sentenciaLlamable.getString(2);
            System.out.println("Nombre del profesor: " + nombre);
            sentenciaLlamable.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getErrorCode() + " - " + ex.getMessage());
        }
    }
}