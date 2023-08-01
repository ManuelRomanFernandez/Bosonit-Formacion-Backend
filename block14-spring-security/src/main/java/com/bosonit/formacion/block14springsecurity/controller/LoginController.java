package com.bosonit.formacion.block14springsecurity.controller;

import com.bosonit.formacion.block14springsecurity.domain.Persona;
import com.bosonit.formacion.block14springsecurity.exception.EntityNotFoundException;
import com.bosonit.formacion.block14springsecurity.repository.PersonaRepository;
import com.bosonit.formacion.block14springsecurity.security.JwtTokenUtil;
import com.bosonit.formacion.block14springsecurity.security.UsernameAndPasswordAuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping
public class LoginController {
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsernameAndPasswordAuthRequest request) {
        String usuario = request.getUsuario();
        String password = request.getPassword();

        Persona persona = personaRepository
                .findAll()
                .stream()
                .filter(person -> person.getUsuario().equals(usuario))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException ("Usuario no existe"));

        if (!persona.getPassword().equals(password)) {
            throw new EntityNotFoundException("Credencial no valida");
        }

        String role = Boolean.TRUE.equals(persona.isAdmin()) ? "ROLE_ADMIN" : "ROLE_USER";
        return new ResponseEntity<>(jwtTokenUtil.generateToken(usuario, role), HttpStatus.OK);
    }
}
