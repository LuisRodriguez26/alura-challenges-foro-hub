package com.alura.foro.dto;

import java.time.LocalDateTime;

import com.alura.foro.domain.Topico;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String status,
        String autor,
        String curso,
        LocalDateTime fechaCreacion
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), 
             topico.getStatus(), topico.getAutor().getNombre(), 
             topico.getCurso(), topico.getFechaCreacion());
    }
}