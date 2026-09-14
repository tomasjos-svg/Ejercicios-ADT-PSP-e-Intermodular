import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
public class EjemploJackson {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File fichero = new File("persona.json");
        try {
            Persona persona = new Persona("Juan", "Gómez", 30);
            mapper.writeValue(fichero, persona);
            System.out.println("JSON creado correctamente.");
            System.out.println();
            JsonNode nodo = mapper.readTree(fichero);
            ObjectNode personaJson = (ObjectNode) nodo;
            System.out.println("JSON ORIGINAL:");
            System.out.println(mapper.writerWithDefaultPrettyPrinter()
            		.writeValueAsString(personaJson));
            personaJson.put("edad", 35);
            System.out.println();
            System.out.println("Edad modificada.");
            personaJson.put("ciudad", "Madrid");
            System.out.println("Ciudad añadida.");
            personaJson.put("telefono", "600123456");
            System.out.println("Teléfono añadido.");
            personaJson.remove("apellidos");
            System.out.println("Apellidos eliminados.");
            mapper.writeValue(fichero, personaJson);
            System.out.println();
            System.out.println("Cambios guardados.");
            System.out.println();
            JsonNode resultado = mapper.readTree(fichero);
            System.out.println("JSON FINAL:");
            System.out.println(mapper.writerWithDefaultPrettyPrinter()
            		.writeValueAsString(resultado));
        } catch (IOException e) {
            System.out.println(
                    "Error trabajando con el fichero JSON: "
                    + e.getMessage()
            );
        }
    }
}