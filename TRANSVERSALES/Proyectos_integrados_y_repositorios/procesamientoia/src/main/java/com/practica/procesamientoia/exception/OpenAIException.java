package com.practica.procesamientoia.exception;

public class OpenAIException extends RuntimeException{
	
	private int codigoHttp;
	 public OpenAIException(String mensaje,int codigoHttp) {
	        super(mensaje);
	        this.codigoHttp=codigoHttp;
	    }
	 public int getcodigoHttp() {
		 return this.codigoHttp;
	 }

}
