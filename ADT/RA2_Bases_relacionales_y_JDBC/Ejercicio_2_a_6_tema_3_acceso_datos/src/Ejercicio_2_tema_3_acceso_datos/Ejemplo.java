package Ejercicio_2_tema_3_acceso_datos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Ejemplo {
	static Scanner sc;
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "alumno", System.getenv().getOrDefault("DB_PASSWORD", "password"));
            String dml = "CREATE TABLE LIBROS (CLAVE VARCHAR2(4) PRIMARY KEY, TITULO VARCHAR2(100), AUTOR VARCHAR2(20), FECHA_EDICION DATE, NUMERO_PAG INTEGER)";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            dml = "INSERT INTO LIBROS (CLAVE, TITULO, AUTOR, FECHA_EDICION, NUMERO_PAG) VALUES (?, ?, ?, TO_DATE(?,'DD/MM/YYYY'), ?)";
            sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, "L001");
            sentenciaPreparada.setString(2, "El Quijote");
            sentenciaPreparada.setString(3, "Miguel de Cervantes");
            sentenciaPreparada.setString(4, "01/01/1605");
            sentenciaPreparada.setInt(5, 863);
            sentenciaPreparada.executeUpdate();
            sentenciaPreparada.setString(1, "L002");
            sentenciaPreparada.setString(2, "La Celestina");
            sentenciaPreparada.setString(3, "Fernando de Rojas");
            sentenciaPreparada.setString(4, "01/01/1499");
            sentenciaPreparada.setInt(5, 320);
            sentenciaPreparada.executeUpdate();
            sentenciaPreparada.setString(1, "L003");
            sentenciaPreparada.setString(2, "1984");
            sentenciaPreparada.setString(3, "George Orwell");
            sentenciaPreparada.setString(4, "08/06/1949");
            sentenciaPreparada.setInt(5, 328);
            sentenciaPreparada.executeUpdate();
            sentenciaPreparada.setString(1, "L004");
            sentenciaPreparada.setString(2, "Rebelion en la granja");
            sentenciaPreparada.setString(3, "George Orwell");
            sentenciaPreparada.setString(4, "17/08/1945");
            sentenciaPreparada.setInt(5, 144);
            sentenciaPreparada.executeUpdate();
            sentenciaPreparada.setString(1, "L005");
            sentenciaPreparada.setString(2, "El principito");
            sentenciaPreparada.setString(3, "Antoine de Saint-Ex");
            sentenciaPreparada.setString(4, "06/04/1943");
            sentenciaPreparada.setInt(5, 96);
            sentenciaPreparada.executeUpdate();
            sentenciaPreparada.setString(1, "L006");
            sentenciaPreparada.setString(2, "La metamorfosis");
            sentenciaPreparada.setString(3, "Franz Kafka");
            sentenciaPreparada.setString(4, "01/01/1915");
            sentenciaPreparada.setInt(5, 128);
            sentenciaPreparada.executeUpdate();
            sentenciaPreparada.setString(1, "L007");
            sentenciaPreparada.setString(2, "Ficciones");
            sentenciaPreparada.setString(3, "Jorge Luis Borges");
            sentenciaPreparada.setString(4, "01/01/1944");
            sentenciaPreparada.setInt(5, 224);
            sentenciaPreparada.executeUpdate();
            sentenciaPreparada.setString(1, "L008");
            sentenciaPreparada.setString(2, "Rayuela");
            sentenciaPreparada.setString(3, "Julio Cortazar");
            sentenciaPreparada.setString(4, "28/06/1963");
            sentenciaPreparada.setInt(5, 736);
            sentenciaPreparada.executeUpdate();
            sentenciaPreparada.setString(1, "L009");
            sentenciaPreparada.setString(2, "Pedro Paramo");
            sentenciaPreparada.setString(3, "Juan Rulfo");
            sentenciaPreparada.setString(4, "01/01/1955");
            sentenciaPreparada.setInt(5, 136);
            sentenciaPreparada.executeUpdate();
            sentenciaPreparada.setString(1, "L010");
            sentenciaPreparada.setString(2, "La colmena");
            sentenciaPreparada.setString(3, "Camilo Jose Cela");
            sentenciaPreparada.setString(4, "01/01/1951");
            sentenciaPreparada.setInt(5, 336);
            sentenciaPreparada.executeUpdate();
            int numero = 0;
            String nombre="";
            String sql = "SELECT * FROM libros WHERE AUTOR LIKE ? AND NUMERO_PAG >= ?";
            PreparedStatement s = conexion.prepareStatement(sql);
            try {
            	System.out.println("Escribe el nombre del autor ");
				sc=new Scanner (System.in);
				nombre=sc.nextLine();
				System.out.println("Escribe el número de páginas ");
				numero= sc.nextInt();
				sc.nextLine();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
                      
            s.setString(1, nombre);
            s.setInt(2, numero);
            ResultSet resultado = s.executeQuery();
            while (resultado.next()) {
                System.out.println("Clave: " + resultado.getString("CLAVE"));
                System.out.println("Titulo: " + resultado.getString("TITULO"));
                System.out.println("Autor: " + resultado.getString("AUTOR"));
                System.out.println("Fecha: " + resultado.getDate("FECHA_EDICION"));
                System.out.println("Paginas: " + resultado.getInt("NUMERO_PAG"));
            }
            sc = new Scanner(System.in);
            System.out.print("Introduce la clave: ");
            String clave = sc.nextLine();
            System.out.print("Introduce el titulo: ");
            String titulo = sc.nextLine();
            System.out.print("Introduce el autor: ");
            String autor = sc.nextLine();
            System.out.print("Introduce la fecha de edicion (DD/MM/YYYY): ");
            String fecha = sc.nextLine();
            System.out.print("Introduce el numero de paginas: ");
            int paginas = sc.nextInt();
            sql = "INSERT INTO LIBROS (CLAVE, TITULO, AUTOR, FECHA_EDICION, NUMERO_PAG) VALUES (?, ?, ?, TO_DATE(?,'DD/MM/YYYY'), ?)";
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, clave);
            sentenciaPreparada.setString(2, titulo);
            sentenciaPreparada.setString(3, autor);
            sentenciaPreparada.setString(4, fecha);
            sentenciaPreparada.setInt(5, paginas);
            int registrosAfectados = sentenciaPreparada.executeUpdate();
            System.out.println("Registros insertados: " + registrosAfectados);
            sentenciaPreparada.close();
            sc.close();
            System.out.println("Tabla creada, 10 libros insertados y sentencia select e inserción ejecutados correctamente");
            resultado.close();
            sentenciaPreparada.close();
           // conexion.commit();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getErrorCode() + " - " + ex.getMessage());
        }
    }
}