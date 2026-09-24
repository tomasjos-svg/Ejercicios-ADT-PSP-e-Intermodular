package com.practica.procesamientoia.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RateLimiterService {
	private final long intervaloMinimoMs;

    private long ultimoEnvio;

    public RateLimiterService(
        @Value("${openai.intervalo-ms}") long intervaloMinimoMs) {
        this.intervaloMinimoMs = intervaloMinimoMs;
        this.ultimoEnvio = 0;
    }

    public synchronized void esperarTurno()
            throws InterruptedException {

        // calcular cuánto tiempo ha pasado
    	long tiempo_transcurrido= System.currentTimeMillis()-ultimoEnvio;
    	
    	// calcular cuánto falta para poder enviar
    	long tiempoEspera = intervaloMinimoMs - tiempo_transcurrido;
    	if (tiempoEspera>0) {
    		 // si todavía falta tiempo:
            //     Thread.sleep(...)
        	Thread.sleep(tiempoEspera);
    	}
        // actualizar ultimoEnvio
    	this.ultimoEnvio=System.currentTimeMillis();
    }
}
