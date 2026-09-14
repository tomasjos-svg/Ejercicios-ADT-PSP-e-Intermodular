package Ejemplo_19_tema_2_acceso_datos;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
public class EjemploAnotacionesJackson {
public static void main(String[] args) {
        ObjectMapper mapper =  new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Persona persona = new Persona("Ana","Lopez",25);
        try {
            mapper.writeValue(new File("persona.json"),persona);
            System.out.println("JSON creado correctamente.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}