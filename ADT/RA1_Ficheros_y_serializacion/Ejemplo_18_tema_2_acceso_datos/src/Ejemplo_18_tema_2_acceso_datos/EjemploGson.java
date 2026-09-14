package Ejemplo_18_tema_2_acceso_datos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EjemploGson {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Usuario usuario = new Usuario(10, "Ana");
        String json = gson.toJson(usuario);
        System.out.println("JSON:");
        System.out.println(json);
        Usuario usuario2 = gson.fromJson(json, Usuario.class);
        System.out.println();
        System.out.println("Objeto recuperado:");
        System.out.println("ID: " + usuario2.getId());
        System.out.println("Nombre: " + usuario2.getNombre());
    }
}