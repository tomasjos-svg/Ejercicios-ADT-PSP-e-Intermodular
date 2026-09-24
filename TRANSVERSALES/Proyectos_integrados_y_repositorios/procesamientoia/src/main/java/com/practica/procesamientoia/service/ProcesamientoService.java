package com.practica.procesamientoia.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.practica.procesamientoia.model.EstadoTrabajo;
import com.practica.procesamientoia.model.Trabajo;
@Service
public class ProcesamientoService {
    private final TrabajoService trabajoService;
    private final PromptService promptService;
    private final OpenAIService openAIService;
    private final RateLimiterService rateLimiterService;
    private final int numeroHilos;
    private final int maxReintentos;
    private final long esperaReintentoMs;
    public ProcesamientoService(
            TrabajoService trabajoService,
            PromptService promptService,
            OpenAIService openAIService,
            RateLimiterService rateLimiterService,
            @Value("${procesamiento.numero-hilos}") int numeroHilos,
            @Value("${openai.max-reintentos}") int maxReintentos,
            @Value("${openai.espera-reintento-ms}") long esperaReintentoMs) {
        this.trabajoService = trabajoService;
        this.promptService = promptService;
        this.openAIService = openAIService;
        this.rateLimiterService = rateLimiterService;
        this.numeroHilos = numeroHilos;
        this.maxReintentos = maxReintentos;
        this.esperaReintentoMs = esperaReintentoMs;
    }
    public synchronized void iniciarProcesamiento(Long trabajoId) {
        // Buscar el trabajo.
        // buscarPorId ya lanza RecursoNoEncontradoException si no existe.
        Trabajo trabajo = trabajoService.buscarPorId(trabajoId);
        // Si ya se está procesando, no arrancarlo otra vez.
        if (trabajo.getEstado() == EstadoTrabajo.PROCESANDO) {
            return;
        }
        // Si ya ha finalizado, tampoco se procesa otra vez.
        if (trabajo.getEstado() == EstadoTrabajo.FINALIZADO) {
            return;
        }
        if (trabajo.getEstado() == EstadoTrabajo.FINALIZADO_CON_ERRORES) {
            return;
        }
        // El trabajo comienza a procesarse.
        trabajo.setEstado(EstadoTrabajo.PROCESANDO);
        trabajoService.guardar(trabajo);
        // Crear los hilos procesadores.
        for (int i = 0; i < numeroHilos; i++) {
            ProcesamientoPrompt procesador =
                    new ProcesamientoPrompt(
                            trabajoId,
                            promptService,
                            rateLimiterService,
                            openAIService,
                            maxReintentos,
                            esperaReintentoMs);
            Thread hilo =
                    new Thread(
                            procesador,
                            "procesador-" + (i + 1));
            hilo.start();
        }
    }
}