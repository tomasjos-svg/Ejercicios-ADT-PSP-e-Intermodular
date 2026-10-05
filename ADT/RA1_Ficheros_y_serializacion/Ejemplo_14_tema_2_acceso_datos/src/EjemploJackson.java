/*import java.io.File;
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
*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
public class EjemploJackson {
    public static void main(String[] args) {
        File fichero = new File("personas.json");
        try {
            System.out.println("===== CRUD CON ARRAYLIST =====");
            List<Persona> personas = new ArrayList<>();
            personas.add(new Persona("Juan", "Gómez", 30));
            personas.add(new Persona("Ana", "López", 25));
            personas.add(new Persona("Luis", "Martínez", 40));
            JsonUtil.mapper.writeValue(fichero,personas);
            System.out.println("Lista inicial guardada.");
            Persona[] arrayPersonas =JsonUtil.mapper.readValue(fichero,
                            Persona[].class);
            personas =new ArrayList<>(
                            Arrays.asList(arrayPersonas));
            System.out.println("Personas leídas del fichero:");
            for (Persona p : personas) {
                System.out.println(p);
            }
            Persona nueva = new Persona("María","Fernández",28);
            personas.add(nueva);
            System.out.println("Persona añadida: " + nueva);
            System.out.println("Buscando a Ana...");
            for (Persona p : personas) {
                if (p.getNombre().equals("Ana")) {
                    System.out.println("Encontrada: " + p);
                }
            }
            for (Persona p : personas) {
                if (p.getNombre().equals("Ana")) {
                    p.setEdad(27);
                    System.out.println();
                    System.out.println("Ana modificada: " + p);
                }
            }
            personas.removeIf(p -> p.getNombre().equals("Luis"));
            System.out.println("Luis eliminado.");
            JsonUtil.mapper.writeValue(fichero,personas);
            System.out.println("Cambios guardados en el fichero.");
            System.out.println("JSON DESPUÉS DEL CRUD CON ARRAYLIST:");
            System.out.println(JsonUtil.mapper
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(personas));

            System.out.println();
            System.out.println("===== CRUD DIRECTAMENTE SOBRE JSON =====");
            JsonNode nodo =JsonUtil.mapper.readTree(fichero);
            ArrayNode listaJson =(ArrayNode) nodo;
            System.out.println("JSON leído directamente:");
            System.out.println(JsonUtil.mapper
                            .writerWithDefaultPrettyPrinter()
                            .writeValueAsString(listaJson));
            ObjectNode nuevaPersona =JsonUtil.mapper.createObjectNode();
            nuevaPersona.put("nombre","Pedro");
            nuevaPersona.put("apellidos","Suárez");
            nuevaPersona.put("edad",35);
            listaJson.add(nuevaPersona);
            System.out.println("Pedro añadido.");
            System.out.println("Buscando a María...");
            for (JsonNode persona : listaJson) {
                if (persona.get("nombre").asText()
                        .equals("María")) {
                    System.out.println(persona);
                }
            }
            for (JsonNode persona : listaJson) {
                if (persona.get("nombre").asText()
                        .equals("María")) {
                    ObjectNode personaJson =
                            (ObjectNode) persona;
                    personaJson.put("edad",29);
                    personaJson.put("ciudad","Oviedo");
                    personaJson.put("telefono","600123456");
                    System.out.println("María modificada.");
                }
            }
            for (int i = 0;i < listaJson.size(); i++) {
                JsonNode persona =listaJson.get(i);
                if (persona.get("nombre").asText().equals("Juan")) {
                    listaJson.remove(i);
                    System.out.println("Juan eliminado.");
                    break;
                }
            }
            JsonUtil.mapper.writeValue(fichero,listaJson);
            System.out.println("Cambios JSON guardados.");
            JsonNode resultado =JsonUtil.mapper.readTree(fichero);
            System.out.println("JSON FINAL:");
            System.out.println(JsonUtil.mapper
                            .writerWithDefaultPrettyPrinter()
                            .writeValueAsString(resultado));
        } catch (IOException e) {
            System.out.println("Error trabajando con el fichero JSON: "
                    + e.getMessage());
        }
    }
}
