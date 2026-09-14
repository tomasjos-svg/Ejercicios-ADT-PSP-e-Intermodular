package Ejemplo_15_tema_2_acceso_datos;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
public class ConvertirXmlJson {
    public static void main(String[] args) {
        File ficheroXml = new File(".//persona.xml");
        File ficheroJson = new File(".//persona.json");
        try {
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode arbol = xmlMapper.readTree(ficheroXml);
            System.out.println("Contenido leído:");
            System.out.println(
                JsonUtil.mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(arbol)
            );
            JsonUtil.mapper.writeValue(
                ficheroJson,
                arbol
            );
            System.out.println();
            System.out.println(
                "Conversión XML -> JSON realizada."
            );
            System.out.println(
                "Fichero: "
                + ficheroJson.getAbsolutePath()
            );
        } catch (IOException e) {
            System.out.println(
                "Error: " + e.getMessage()
            );
        }
    }
}