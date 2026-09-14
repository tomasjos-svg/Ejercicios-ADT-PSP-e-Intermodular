import java.sql.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.dao.AlumnoDAO;
import com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.dao.ConexionOracle;
import com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.model.Alumno;
import com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.model.Direccion;

class AlumnoDAOTest {
  private AlumnoDAO dao;

  @BeforeEach
  void setUp() throws SQLException {
    dao = new AlumnoDAO();
    limpiarDatosTest();
  }

  @AfterEach
  void tearDown() throws SQLException {
    limpiarDatosTest();
  }

  private void limpiarDatosTest() throws SQLException {
    try (Connection con = ConexionOracle.abrir();
         PreparedStatement ps = con.prepareStatement(
             "DELETE FROM ALUMNOS WHERE CLAVE LIKE 'T%'")) {
      ps.executeUpdate();
    }
  }
  
  @Test
  void testInsertarYBuscar() throws SQLException {
    Alumno a = new Alumno("T001", "Ana", "Perez", 21,
        new Direccion("Uría", 10, "33003"));

    assertEquals(1, dao.insertar(a));
    Alumno recuperado = dao.buscarPorClave("T001");

    assertNotNull(recuperado);
    assertEquals("Ana", recuperado.getNombre());
    assertEquals("Perez", recuperado.getApellidos());
  }
  @Test
  void testListar() throws SQLException {
    dao.insertar(new Alumno("T001", "Ana", "Perez", 21,
        new Direccion("Uría", 10, "33003")));
    dao.insertar(new Alumno("T002", "Luis", "Diaz", 22,
        new Direccion("Lila", 4, "33001")));

    long numTest = dao.cargarTodos().stream()
        .filter(a -> a.getClave().startsWith("T"))
        .count();
    assertEquals(2, numTest);
  }
  @Test
  void testActualizar() throws SQLException {
    Alumno a = new Alumno("T001", "Ana", "Perez", 21,
        new Direccion("Uría", 10, "33003"));
    dao.insertar(a);

    a.setEdad(22);
    a.setDireccion(new Direccion("Cervantes", 10, "33003"));
    assertEquals(1, dao.actualizar(a));

    Alumno r = dao.buscarPorClave("T001");
    assertEquals(22, r.getEdad());
    assertEquals("Cervantes", r.getDireccion().getCalle());
  }
  @Test
  void testEliminar() throws SQLException {
    dao.insertar(new Alumno("T001", "Ana", "Perez", 21,
        new Direccion("Uría", 10, "33003")));

    assertEquals(1, dao.eliminar("T001"));
    assertNull(dao.buscarPorClave("T001"));
  }


}
