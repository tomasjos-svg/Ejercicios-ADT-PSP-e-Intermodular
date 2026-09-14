package Ejemplo_16_tema_2_acceso_datos;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class EjemploJackson {
    public static void main(String[] args) {
        ObjectMapper jsonMapper = new ObjectMapper();
        XmlMapper xmlMapper = new XmlMapper();
        File ficheroJson = new File("persona.json");
        File ficheroXml = new File("persona.xml");
        try {
            JsonNode arbol = jsonMapper.readTree(ficheroJson);
            xmlMapper
                .writer()
                .withRootName("Persona")
                .writeValue(ficheroXml, arbol);
            System.out.println("Conversión JSON -> XML realizada.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}