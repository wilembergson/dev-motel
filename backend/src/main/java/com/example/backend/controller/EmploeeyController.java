package com.example.backend.controller;

import com.example.backend.model.dto.LoginDTO;
import com.example.backend.model.dto.LoginResponseDTO;
import com.example.backend.model.dto.NewCustomerDTO;
import com.example.backend.model.dto.NewEmploeeyDTO;
import com.example.backend.model.entity.Customer;
import com.example.backend.model.entity.Emploeey;
import com.example.backend.security.TokenService;
import com.example.backend.service.EmploeeyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/emploeey")
public class EmploeeyController {

    @Autowired
    private EmploeeyService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/new-emploeey")
    public ResponseEntity<Object> newEmploeeyr(@RequestBody NewEmploeeyDTO dto){
        service.newEmploeey(dto);
        return new ResponseEntity<>(Map.of("mensagem", "Conta criada."), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Emploeey) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("mensagem", "Usuário ou senha incorretos."));
        }
    }
}
