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
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1522/XEPDB1", "alumno", System.getenv().getOrDefault("DB_PASSWORD", "password"));
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

/*
CREATE OR REPLACE PROCEDURE devuelve_nom (
    p_dni IN VARCHAR2,
    p_nombre OUT VARCHAR2
)
AS
BEGIN
    SELECT nombre
    INTO p_nombre
    FROM profesor
    WHERE dni = p_dni;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_nombre := 'Profesor no encontrado';
END;
/
*/