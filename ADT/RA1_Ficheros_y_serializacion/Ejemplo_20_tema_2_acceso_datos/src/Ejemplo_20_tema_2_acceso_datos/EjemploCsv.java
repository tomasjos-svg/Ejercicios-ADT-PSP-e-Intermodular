package Ejemplo_20_tema_2_acceso_datos;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
public class EjemploCsv {
    public static void main(String[] args) {
        String fichero = "personas.csv";
        try {
            PersonaCsv p1 = new PersonaCsv("Ana", 25);
            PersonaCsv p2 = new PersonaCsv("Luis", 32);
            PersonaCsv p3 = new PersonaCsv("Marta", 41);
            List<PersonaCsv> personas =
                    Arrays.asList(p1, p2, p3);
            try (Writer writer =
                    Files.newBufferedWriter(
                            Paths.get(fichero))) {
                StatefulBeanToCsv<PersonaCsv> escritor =
                        new StatefulBeanToCsvBuilder<PersonaCsv>(writer)
                            .withApplyQuotesToAll(false)
                            .build();
                escritor.write(personas);
            }
            System.out.println(
                    "CSV creado correctamente."
            );
            try (Reader reader =
                    Files.newBufferedReader(
                            Paths.get(fichero))) {
                List<PersonaCsv> lista =
                        new CsvToBeanBuilder<PersonaCsv>(reader)
                            .withType(PersonaCsv.class)
                            .build()
                            .parse();
                System.out.println();
                System.out.println("Personas leídas:");
                for (PersonaCsv persona : lista) {
                    System.out.println(
                            "Nombre: "
                            + persona.getNombre()
                            + " - Edad: "
                            + persona.getEdad()
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}