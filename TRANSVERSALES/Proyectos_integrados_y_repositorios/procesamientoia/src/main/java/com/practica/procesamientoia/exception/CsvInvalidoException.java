package com.practica.procesamientoia.exception;

public class CsvInvalidoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

    public CsvInvalidoException(String mensaje) {
        super(mensaje);
    }
}
