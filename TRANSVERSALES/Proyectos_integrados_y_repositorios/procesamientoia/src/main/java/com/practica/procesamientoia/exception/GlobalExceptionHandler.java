package com.practica.procesamientoia.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(
            RecursoNoEncontradoException ex,
            HttpServletRequest request) {

        Map<String, Object> respuesta = new LinkedHashMap<>();

        respuesta.put("fecha", LocalDateTime.now());
        respuesta.put("estado", 404);
        respuesta.put("error", "Not Found");
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("ruta", request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(respuesta);
    }
    @ExceptionHandler(CsvInvalidoException.class)
    public ResponseEntity<Map<String, Object>> CsvInvalido(
            CsvInvalidoException ex,
            HttpServletRequest request) {

        Map<String, Object> respuesta = new LinkedHashMap<>();

        respuesta.put("fecha", LocalDateTime.now());
        respuesta.put("estado", 400);
        respuesta.put("error", "CSV INVALIDO");
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("ruta", request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(respuesta);
    }
    @ExceptionHandler(OpenAIException.class)
    public ResponseEntity<Map<String, Object>> manejarOpenAI(
            OpenAIException ex,
            HttpServletRequest request) {

        Map<String, Object> respuesta = new LinkedHashMap<>();

        respuesta.put("fecha", LocalDateTime.now());
        respuesta.put("estado", ex.getcodigoHttp());
        respuesta.put("error", "Error de OpenAI");
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("ruta", request.getRequestURI());

        return ResponseEntity
                .status(ex.getcodigoHttp())
                .body(respuesta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacion(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errores = new LinkedHashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> respuesta = new LinkedHashMap<>();

        respuesta.put("fecha", LocalDateTime.now());
        respuesta.put("estado", 400);
        respuesta.put("error", "Bad Request");
        respuesta.put("mensaje", "Existen datos no validos");
        respuesta.put("ruta", request.getRequestURI());
        respuesta.put("erroresValidacion", errores);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(respuesta);
    }
}