import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.Test;

import com.example.Practica_tema_2_ADT.DatosTienda;

class DatosTiendaTest {
    @Test
    void cargaCorrecta() throws Exception {
        DatosTienda datos;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("datos_tienda.ser"))) {
            datos = (DatosTienda) in.readObject();
        }

        assertEquals(2, datos.getClientes().size());
        assertEquals(2, datos.getProductos().size());
        assertEquals(2, datos.getPedidos().size());
        assertTrue(datos.getPedidos().stream().allMatch(p ->
            datos.getClientes().containsKey(p.getIdCliente())));
    }
}
