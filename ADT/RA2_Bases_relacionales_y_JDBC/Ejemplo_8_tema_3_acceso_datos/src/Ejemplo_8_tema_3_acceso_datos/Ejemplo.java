package Ejemplo_8_tema_3_acceso_datos;

import java.sql.*;
public class Ejemplo {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "alumno", System.getenv().getOrDefault("DB_PASSWORD", "password"));
            DatabaseMetaData meta = conexion.getMetaData();
            System.out.println("Base de datos: " + meta.getDatabaseProductName());
            System.out.println("Version: " + meta.getDatabaseProductVersion());
            System.out.println("Driver: " + meta.getDriverName());
            System.out.println("\n--- TABLAS DEL USUARIO ALUMNO ---");
            try (ResultSet rs = meta.getTables(null, "ALUMNO", null, new String[]{"TABLE"})) {
                while (rs.next()) {
                    System.out.println("Tabla: " + rs.getString("TABLE_NAME"));
                }
            }
            System.out.println("\n--- COLUMNAS DE PROFESOR ---");
            try (ResultSet rs = meta.getColumns(null, "ALUMNO", "PROFESOR", null)) {
                while (rs.next()) {
                    String nombre = rs.getString("COLUMN_NAME");
                    String tipo = rs.getString("TYPE_NAME");
                    int tamano = rs.getInt("COLUMN_SIZE");
                    String nullable = rs.getString("IS_NULLABLE");
                    System.out.println("Columna: " + nombre + ", Tipo: " + tipo + ", Tamaño: " + tamano + ", Nullable: " + nullable);
                }
            }
            System.out.println("\n--- CLAVE PRIMARIA DE PROFESOR ---");
            try (ResultSet pk = meta.getPrimaryKeys(null, "ALUMNO", "PROFESOR")) {
                while (pk.next()) {
                    String columna = pk.getString("COLUMN_NAME");
                    String nombrePK = pk.getString("PK_NAME");
                    System.out.println("PK: " + columna + " (constraint " + nombrePK + ")");
                }
            }
            System.out.println("\n--- DATOS DE PROFESOR ---");
            Statement sentencia = conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM PROFESOR");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnas = rsmd.getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= columnas; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
            rs.close();
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getErrorCode() + " - " + ex.getMessage());
        }
    }
}


