import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
public class EjemploJackson {
	
	static Scanner sc=null;
	static ObjectMapper mapper=null;
	public static void añadirpersona(JsonNode raiz) {
		
		System.out.println("introduce los datos de la persona");
		System.out.println("Nombre ");
		String nombre= sc.nextLine();
		System.out.println("Apellidos ");
		String apellidos=sc.nextLine();
		System.out.println("edad ");
		int edad = sc.nextInt();
		sc.nextLine();
		ObjectNode persona=mapper.createObjectNode();
		persona.put("nombre",nombre);
		persona.put("apellidos", apellidos);
		persona.put("edad", edad);	
			
	}
	
	public static void modificarpersona(JsonNode raiz) {
		System.out.println("indica el nombre y apellidos de la persona que buscas");
		System.out.println("nombre ");
		String nombre= sc.nextLine();
		System.out.println("apellidos");
		String apellidos =sc.nextLine();
		boolean encontrado=false;
		int i=0;
		while((i<raiz.size())&&(encontrado==false)) {
			
			if ((raiz.get(i).get("nombre").asText().equals(nombre))&&(raiz.get(i).get("apellidos").asText().equals(apellidos))){
				encontrado=true;
				System.out.println("introduce nuevo nombre");
				nombre=sc.nextLine();
				System.out.println("introduce nuevo apellido");
				apellidos=sc.nextLine();
				System.out.println("introduce nueva edad");
				int edad=sc.nextInt();
				sc.nextLine();	
				ObjectNode p =(ObjectNode) raiz.get(i);		
				p.put("nombre", nombre);
				p.put("apellidos", apellidos);
				p.put("edad", edad);
				
			}
		}
		if (encontrado == false) {
			System.out.println("la persona no existe");
		}
	}
	
	public static void borrarpersona(JsonNode raiz) {
		System.out.println("indica el nombre y apellidos de la persona que buscas");
		System.out.println("nombre ");
		String nombre= sc.nextLine();
		System.out.println("apellidos");
		String apellidos =sc.nextLine();		
		boolean encontrado=false;
		int i =0;
        while((i<raiz.size())&&(encontrado==false)) {
			if ((raiz.get(i).get("nombre").asText().equals(nombre))&&(raiz.get(i).get("apellidos").asText().equals(apellidos))){
				encontrado=true;
				((ArrayNode) raiz).remove(i);		
			}
		}
		if (encontrado == false) {
			System.out.println("la persona no existe");
		}
	}
	
	public static void visualizarpersonas(JsonNode raiz) {
		for (JsonNode persona : raiz) {

		    System.out.println(persona.get("nombre").asText());
		    System.out.println(persona.get("apellidos").asText());
		    System.out.println(persona.get("edad").asInt());
		}		
	}
	
    public static void main(String[] args) {
    	ArrayList<Persona> personas;
    	JsonNode raiz = null;
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File fichero = new File("persona.json");
        personas=new ArrayList<Persona>();
        try {
        	
        	System.out.println("bienvenido a la aplicación de gestión de personas");
        	
        	if (!fichero.exists()) {
        	    fichero.createNewFile();
        	    System.out.println("El fichero no existía. Se ha creado persona.json");
        	}

        	if (fichero.length() == 0) {
        	    System.out.println("El fichero está vacío. La lista de personas está vacía.");
        	} else {
        	    
        	   raiz=mapper.readTree(fichero);
        	}
        	
        	sc=new Scanner(System.in);
        	int opcion;
			do {
        		System.out.println("Elija una opción: 1 para añadir una persona, 2 para borrar una persona, 3 para modificar una persona, 4 para visualizar la lista, 5 para salir");
            	opcion=sc.nextInt();
            	sc.nextLine();
        		switch(opcion) {
            	
            		case 1: añadirpersona(raiz); break;
            		case 2: borrarpersona(raiz);mapper.writeValue(fichero, personas); break;
            		case 3: modificarpersona(raiz);mapper.writeValue(fichero, personas); break;
            		case 4: visualizarpersonas(raiz); break;
            		case 5: System.out.println("saliendo de la aplicacion");break;
            		default: System.out.println("opcion no valida");break;
            	
            	}
        		
        	}while(opcion!=5);
        	mapper.writeValue(fichero, personas);
			
        	/*
            Persona persona = new Persona("Juan", "Gómez", 30);
            mapper.writeValue(fichero, persona);
            mapper.readValue(fichero, persona);
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
            		*/
         }
         catch (IOException e) {
            System.out.println(
                    "Error trabajando con el fichero JSON: "
                    + e.getMessage()
            );
        }
    }
}