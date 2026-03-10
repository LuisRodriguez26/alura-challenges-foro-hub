package com.alura.foro.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.domain.Topico;
import com.alura.foro.domain.Usuario;
import com.alura.foro.dto.DatosRegistroTopico;
import com.alura.foro.dto.DatosRespuestaTopico;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistro, UriComponentsBuilder uriBuilder) {
        Usuario autor = usuarioRepository.findById(datosRegistro.autorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Topico topico = new Topico(datosRegistro.titulo(), datosRegistro.mensaje(), autor, datosRegistro.curso());
        topicoRepository.save(topico);

        DatosRespuestaTopico respuesta = new DatosRespuestaTopico(topico);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DatosRespuestaTopico>> listadoTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        List<DatosRespuestaTopico> respuesta = topicos.stream()
                .map(DatosRespuestaTopico::new)
                .toList();
        return ResponseEntity.ok(respuesta);
    }
}