package com.practica.procesamientoia.service;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.practica.procesamientoia.exception.RecursoNoEncontradoException;
import com.practica.procesamientoia.model.EstadoPrompt;
import com.practica.procesamientoia.model.EstadoTrabajo;
import com.practica.procesamientoia.model.Prompt;
import com.practica.procesamientoia.model.Respuesta;
import com.practica.procesamientoia.model.Trabajo;
import com.practica.procesamientoia.repository.PromptRepository;
import com.practica.procesamientoia.repository.RespuestaRepository;
import com.practica.procesamientoia.repository.TrabajoRepository;
import org.springframework.transaction.annotation.Transactional;
@Service
public class PromptService {
    private final PromptRepository promptRepository;
    private final TrabajoRepository trabajoRepository;
    private final RespuestaRepository respuestaRepository;
    public PromptService(
            PromptRepository promptRepository,
            TrabajoRepository trabajoRepository,
            RespuestaRepository respuestaRepository) {

        this.promptRepository = promptRepository;
        this.trabajoRepository = trabajoRepository;
        this.respuestaRepository = respuestaRepository;
    }
    public synchronized Prompt obtenerSiguientePendiente(Long trabajoId) {

        Prompt prompt =
                promptRepository
                    .findFirstByTrabajoIdAndEstadoOrderByIdAsc(
                            trabajoId,
                            EstadoPrompt.PENDIENTE);

        if (prompt == null) {
            return null;
        }
        prompt.setEstado(EstadoPrompt.PROCESANDO);
        promptRepository.save(prompt);
        return prompt;
    }
    @Transactional
    public synchronized void guardarRespuesta(
            Prompt prompt,
            String textoRespuesta) {

        /*
         * Volvemos a recuperar el prompt de la base de datos.
         * Así trabajamos con una entidad asociada a la
         * transacción actual.
         */
        Prompt promptBD = promptRepository.findById(prompt.getId())
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el prompt con id "
                        + prompt.getId()));

        // Crear la respuesta
        Respuesta respuesta = new Respuesta();

        respuesta.setFechaCreacion(LocalDateTime.now());
        respuesta.setTexto(textoRespuesta);
        respuesta.setPrompt(promptBD);

        // Guardar la respuesta
        respuestaRepository.save(respuesta);

        // Marcar el prompt como completado
        promptBD.setEstado(EstadoPrompt.COMPLETADO);
        promptBD.setMensajeError(null);

        promptRepository.save(promptBD);

        // Obtener el trabajo al que pertenece
        Long trabajoId = promptBD.getTrabajo().getId();

        // Comprobar si el trabajo ha terminado
        actualizarEstadoTrabajo(trabajoId);
    }
    @Transactional
    public synchronized void marcarError(
            Prompt prompt,
            String mensaje) {

        /*
         * Igual que en guardarRespuesta(), recuperamos
         * de nuevo el prompt de la base de datos.
         */
        Prompt promptBD = promptRepository.findById(prompt.getId())
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el prompt con id "
                        + prompt.getId()));

        promptBD.setEstado(EstadoPrompt.ERROR);
        promptBD.setMensajeError(mensaje);

        promptRepository.save(promptBD);

        Long trabajoId = promptBD.getTrabajo().getId();

        // Comprobar si el trabajo ha terminado
        actualizarEstadoTrabajo(trabajoId);
    }

    public void actualizarEstadoTrabajo(Long trabajoId) {

        /*
         * Recuperamos realmente el Trabajo.
         * No utilizamos getReferenceById().
         */
        Trabajo trabajo = trabajoRepository.findById(trabajoId)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el trabajo con id "
                        + trabajoId));

        long pendientes =
                promptRepository.countByTrabajoIdAndEstado(
                        trabajoId,
                        EstadoPrompt.PENDIENTE);

        long procesando =
                promptRepository.countByTrabajoIdAndEstado(
                        trabajoId,
                        EstadoPrompt.PROCESANDO);

        /*
         * Si no quedan prompts pendientes ni procesando,
         * el trabajo ha terminado.
         */
        if (pendientes == 0 && procesando == 0) {

            long errores =
                    promptRepository.countByTrabajoIdAndEstado(
                            trabajoId,
                            EstadoPrompt.ERROR);

            if (errores == 0) {

                trabajo.setEstado(
                        EstadoTrabajo.FINALIZADO);

            } else {

                trabajo.setEstado(
                        EstadoTrabajo.FINALIZADO_CON_ERRORES);
            }

            trabajoRepository.save(trabajo);
        }
    }
}