package com.practica_5.gestion_biblioteca.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
@Document(collection = "libros") // nombre de la colección en MongoDB
public class Libro {

    @Id
    private String id;

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    private String autor;

    private boolean disponible = true; // Indica si el libro está disponible para préstamo (por defecto true)

    public Libro(String id,
                 @NotBlank(message = "El titulo es obligatorio") String titulo,
                 @NotBlank(message = "El autor es obligatorio") String autor,
                 boolean disponible) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = disponible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}