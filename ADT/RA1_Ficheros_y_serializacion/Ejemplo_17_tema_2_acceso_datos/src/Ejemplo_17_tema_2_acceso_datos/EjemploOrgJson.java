package Ejemplo_17_tema_2_acceso_datos;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class EjemploOrgJson {
    public static void main(String[] args) throws JSONException {
        String json =
                "{"
                + "\"nombre\":\"Ana\","
                + "\"apellidos\":\"Lopez\","
                + "\"edad\":25"
                + "}";
        // String JSON -> JSONObject
        JSONObject objeto = new JSONObject(json);
        // JSONObject -> XML
        String xml ="<Persona>"+ XML.toString(objeto) + "</Persona>";
        System.out.println(xml);
    }
}