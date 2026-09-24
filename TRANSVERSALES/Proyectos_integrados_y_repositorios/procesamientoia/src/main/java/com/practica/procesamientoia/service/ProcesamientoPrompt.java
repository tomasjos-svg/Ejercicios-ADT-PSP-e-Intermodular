package com.practica.procesamientoia.service;
import com.practica.procesamientoia.model.Respuesta;

import java.time.LocalDateTime;

import com.practica.procesamientoia.exception.OpenAIException;
import com.practica.procesamientoia.model.Prompt;

public class ProcesamientoPrompt implements Runnable {
	private final Long trabajoId;
	private final RateLimiterService rateLimiterService;
	private final OpenAIService openAIService;
	private final int maxReintentos;
	private final long esperaReintentoMs;
	public ProcesamientoPrompt(
	        Long trabajoId,
	        PromptService promptService,
	        RateLimiterService rateLimiterService,
	        OpenAIService openAIService,
	        int maxReintentos,
	        long esperaReintentoMs) {
        super();
	    this.trabajoId = trabajoId;
	    this.promptService = promptService;
	    this.rateLimiterService = rateLimiterService;
	    this.openAIService = openAIService;
	    this.maxReintentos = maxReintentos;
	    this.esperaReintentoMs = esperaReintentoMs;
	}
	private final PromptService promptService;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
	        // 1. Obtener el siguiente prompt pendiente
	        Prompt prompt =
	                promptService.obtenerSiguientePendiente(trabajoId);
	        // Si no quedan prompts pendientes, termina este hilo
	        if (prompt == null) {
	        	System.out.println(
	                    Thread.currentThread().getName()
	                    + " - No quedan prompts. Finaliza.");
	            break;
	        }
	        System.out.println(
	                Thread.currentThread().getName()
	                + " - Prompt obtenido: "
	                + prompt.getId());
	        int reintentos = 0;
	        long esperaActual = esperaReintentoMs;
	        boolean promptTerminado = false;
	        /*
	         * Este bucle se ocupa de procesar UN prompt.
	         * Solamente se repetirá cuando OpenAI devuelva un 429.
	         */
	        while (!promptTerminado) {
	            try {
	                // 2. Esperar hasta que el RateLimiter permita enviar
	            	 // Momento en que solicita permiso
	                System.out.println(
	                        Thread.currentThread().getName()
	                        + " - Solicita permiso: "
	                        + LocalDateTime.now());
	                rateLimiterService.esperarTurno();
	                // 3. Enviar el prompt a OpenAI
	                // Momento en que realmente va a enviar la petición
	                System.out.println(
	                        Thread.currentThread().getName()
	                        + " - Envía petición: "
	                        + LocalDateTime.now());
	                String respuesta =
	                        openAIService.enviarPrompt(prompt.getTexto());
	                // Si funciona, guardar la respuesta
	             // Momento en que recibe la respuesta
	                System.out.println(
	                        Thread.currentThread().getName()
	                        + " - Recibe respuesta: "
	                        + LocalDateTime.now());
	                promptService.guardarRespuesta(
	                        prompt,
	                        respuesta);
	                // Este prompt ya está terminado
	                promptTerminado = true;
	            } catch (OpenAIException e) {
	                // 4. Comprobar si el error es un 429
	                if (e.getcodigoHttp() == 429) {
	                    // Comprobar si todavía podemos reintentar
	                    if (reintentos < maxReintentos) {
	                        try {
	                            // Espera progresiva:
	                            // 2s, 4s, 8s...
	                            Thread.sleep(esperaActual);
	                        } catch (InterruptedException ex) {
	                            Thread.currentThread().interrupt();
	                            return;
	                        }
	                        // Hemos consumido un reintento
	                        reintentos++;
	                        // Duplicar la espera para el siguiente 429
	                        esperaActual = esperaActual * 2;
	                        /*
	                         * No ponemos promptTerminado a true.
	                         * Por tanto vuelve a comenzar el while
	                         * y antes de llamar nuevamente a OpenAI
	                         * volverá a pasar por esperarTurno().
	                         */
	                    } else {
	                        // Se han agotado los reintentos
	                        promptService.marcarError(
	                                prompt,
	                                e.getMessage());
	                        promptTerminado = true;
	                    }
	                } else {
	                    // Si no es 429, no reintentamos
	                    promptService.marcarError(
	                            prompt,
	                            e.getMessage());
	                    promptTerminado = true;
	                }
	            } catch (InterruptedException e) {
	                /*
	                 * Puede producirse en
	                 * rateLimiterService.esperarTurno()
	                 */
	            	 System.out.println(
	                         Thread.currentThread().getName()
	                         + " - Hilo interrumpido");
	                Thread.currentThread().interrupt();
	                return;
	            }
	        }
	    }
	}
}
