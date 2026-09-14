package Ejemplo_18_tema_2_acceso_datos;
import com.google.gson.annotations.SerializedName;
public class Usuario {
    @SerializedName("usuario_id")
    private int id;
    private String nombre;
    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
}