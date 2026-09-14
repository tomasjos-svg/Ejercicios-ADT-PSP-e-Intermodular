import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class EjemploJackson {
	
	static Scanner sc=null;
	static File fichero=null;
	
	public static void añadirpersona(ArrayList<Persona> personas) {
		System.out.println("introduce los datos de la persona");
		System.out.println("Nombre ");
		String nombre= sc.nextLine();
		System.out.println("Apellidos ");
		String apellidos=sc.nextLine();
		System.out.println("edad ");
		int edad = sc.nextInt();
		sc.nextLine();
		Persona p=new Persona(nombre,apellidos,edad);
		personas.add(p);		
	}
	
	public static void modificarpersona(ArrayList<Persona> personas) {
		System.out.println("indica el nombre y apellidos de la persona que buscas");
		System.out.println("nombre ");
		String nombre= sc.nextLine();
		System.out.println("apellidos");
		String apellidos =sc.nextLine();
		Iterator it=personas.iterator();
		boolean encontrado=false;
		while((it.hasNext())&&(encontrado==false)) {
			Persona aux=(Persona)it.next();
			if ((aux.getNombre().equals(nombre))&&(aux.getApellidos().equals(apellidos))){
				encontrado=true;
				System.out.println("introduce nuevo nombre");
				nombre=sc.nextLine();
				System.out.println("introduce nuevo apellido");
				apellidos=sc.nextLine();
				System.out.println("introduce nueva edad");
				int edad=sc.nextInt();
				sc.nextLine();	
				aux.setNombre(nombre);
				aux.setApellidos(apellidos);
				aux.setEdad(edad);				
			}
		}
		if (encontrado == false) {
			System.out.println("la persona no existe");
		}
	}
	
	public static void borrarpersona(ArrayList<Persona>personas) {
		System.out.println("indica el nombre y apellidos de la persona que buscas");
		System.out.println("nombre ");
		String nombre= sc.nextLine();
		System.out.println("apellidos");
		String apellidos =sc.nextLine();
		Iterator it=personas.iterator();
		boolean encontrado=false;
		int i =0;
		while((it.hasNext())&&(encontrado==false)) {
			Persona aux=(Persona)it.next();
			if ((aux.getNombre().equals(nombre))&&(aux.getApellidos().equals(apellidos))){
				encontrado=true;
				it.remove();	
			}
			else 
			{
				i++;
			}
		}
		if (encontrado == false) {
			System.out.println("la persona no existe");
		}
	}
	
	public static void visualizarpersonas(ArrayList<Persona>personas) {
		
		Iterator it=personas.iterator();
		boolean encontrado=false;
		int i =0;
		while((it.hasNext())&&(encontrado==false)) {
			Persona aux=(Persona)it.next();
			System.out.println(aux.toString());
		}
	}
	public static void convertirarraylistensjoncongson(ArrayList<Persona> personas) throws IOException {
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 String datos = gson.toJson(personas);
		 FileWriter fich=new FileWriter(fichero);
		 fich.write(datos);
		 
	}
	
	public static void convertirjsonenarraylistcongson(ArrayList<Persona> personas,File fichero) throws IOException {
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 Type tipo = new TypeToken<ArrayList<Persona>>() {}.getType();
		 FileReader fichero_r=new FileReader(fichero);
		 BufferedReader fich=new BufferedReader(fichero_r);	
		 
		 String datos= null;
		 String linea="";
		 
		 while ((linea=fich.readLine())!=null) {
			 datos=datos + linea;
		 }
		 personas=gson.fromJson(datos, tipo);
		 fich.close();
		 fichero_r.close();
		 		 
	}
	
    public static void main(String[] args) {
    	ArrayList<Persona> personas;
    	
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        fichero = new File("persona.json");
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
        	    personas = mapper.readValue(
        	        fichero,
        	        new TypeReference<ArrayList<Persona>>() {}
        	    );
        	    /*convertirjsonenarraylistcongson(personas,fichero);*/
        	}
        	
        	sc=new Scanner(System.in);
        	int opcion;
			do {
        		System.out.println("Elija una opción: 1 para añadir una persona, 2 para borrar una persona, 3 para modificar una persona, 4 para visualizar la lista, 5 para salir");
            	opcion=sc.nextInt();
            	sc.nextLine();
        		switch(opcion) {
            	
            		case 1: añadirpersona(personas);/*convertirarraylistenjsoncongson(personas);*/ mapper.writeValue(fichero, personas);break;
            		case 2: borrarpersona(personas);/*convertirarraylistenjsoncongson(personas);*/mapper.writeValue(fichero, personas); break;
            		case 3: modificarpersona(personas);/*convertirarraylistenjsoncongson(personas);*/mapper.writeValue(fichero, personas); break;
            		case 4: visualizarpersonas(personas); break;
            		case 5: System.out.println("saliendo de la aplicacion");break;
            		default: System.out.println("opcion no valida");break;
            	
            	}
        		
        	}while(opcion!=5);
        	mapper.writeValue(fichero, personas);
        	/*convertirarraylistenjsoncongson(personas);*/
        	
         }
         catch (IOException e) {
            System.out.println(
                    "Error trabajando con el fichero JSON: "
                    + e.getMessage()
            );
        }
    }
}