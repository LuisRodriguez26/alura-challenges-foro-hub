package com.alura.foro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.foro.domain.Usuario;
import com.alura.foro.dto.DatosAutenticacionUsuario;
import com.alura.foro.infra.security.DatosJWTToken;
import com.alura.foro.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
public ResponseEntity realizarLogin(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
    var authenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.correo(), datosAutenticacionUsuario.contrasena());
    var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
    var jwTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
    return ResponseEntity.ok(new DatosJWTToken(jwTtoken));
}
}