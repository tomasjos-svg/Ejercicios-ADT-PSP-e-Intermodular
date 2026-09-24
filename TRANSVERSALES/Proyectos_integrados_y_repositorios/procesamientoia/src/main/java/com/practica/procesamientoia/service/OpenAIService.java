package com.practica.procesamientoia.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practica.procesamientoia.exception.OpenAIException;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
@Service
public class OpenAIService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String apiKey;
    private final String model;
    private final String url;
    public OpenAIService(
            @Value("${openai.api-key}") String apiKey,
            @Value("${openai.model}") String model,
            @Value("${openai.url}") String url) {
        this.apiKey = apiKey;
        this.model = model;
        this.url = url;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }
    public String enviarPrompt(String texto) {
        // 1. Crear las cabeceras HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        // 2. Crear el cuerpo de la petición
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("model", model);
        cuerpo.put("input", texto);
        // 3. Unir cuerpo y cabeceras
        HttpEntity<Map<String, Object>> peticion =
                new HttpEntity<>(cuerpo, headers);
        try {
            // 4. Enviar una petición POST a OpenAI
            ResponseEntity<String> respuesta =
                    restTemplate.postForEntity(
                            url,
                            peticion,
                            String.class);
            // 5. Obtener el JSON recibido
            String json = respuesta.getBody();
            if (json == null || json.isBlank()) {
                throw new OpenAIException("OpenAI ha devuelto una respuesta vacía",502);
            }
            // 6. Extraer y devolver el texto generado
            return extraerTexto(json);
        } catch (HttpStatusCodeException e) {
            /*
             * OpenAI ha respondido con un código HTTP de error:
             * 400, 401, 429, 500, etc.
             */
            throw new OpenAIException(
                    "Error devuelto por OpenAI: "
                            + e.getResponseBodyAsString(),
                    e.getStatusCode().value());
        } catch (RestClientException e) {
            /*
             * Error de comunicación:
             * conexión, DNS, timeout, etc.
             */
            throw new OpenAIException(
                    "No se ha podido establecer comunicación con OpenAI",503);
        }
    }

    private String extraerTexto(String json) {

        try {
            JsonNode raiz = objectMapper.readTree(json);
            JsonNode output = raiz.path("output");
            StringBuilder textoRespuesta = new StringBuilder();
            // Recorremos los elementos de output
            for (JsonNode elemento : output) {
                JsonNode contenido = elemento.path("content");
                // Recorremos los elementos de content
                for (JsonNode parte : contenido) {
                    if ("output_text".equals(
                            parte.path("type").asText())) {
                        String texto =
                                parte.path("text").asText();
                        textoRespuesta.append(texto);
                    }
                }
            }
            if (textoRespuesta.isEmpty()) {
                throw new OpenAIException(
                        "La respuesta de OpenAI no contiene texto",
                        502);
            }
            return textoRespuesta.toString();
        } catch (JsonProcessingException e) {
            throw new OpenAIException(
                    "No se ha podido interpretar la respuesta JSON de OpenAI",
                    502);
        }
    }
    
}