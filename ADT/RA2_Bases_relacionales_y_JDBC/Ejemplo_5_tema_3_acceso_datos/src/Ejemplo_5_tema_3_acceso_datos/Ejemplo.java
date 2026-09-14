package Ejemplo_5_tema_3_acceso_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Ejemplo {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "alumno", System.getenv().getOrDefault("DB_PASSWORD", "password"));
            String dml = "UPDATE PROFESOR SET NOMBRE = ? WHERE DNI = ?";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, "LUIS");
            sentenciaPreparada.setString(2, "2345");
            System.out.println("Sentencia final: " + sentenciaPreparada.toString());
            int registrosAfectados = sentenciaPreparada.executeUpdate();
            System.out.println("Registros Afectados: " + registrosAfectados);
            sentenciaPreparada.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
        }
    }
}