package com.example.backend.controller;

import com.example.backend.model.dto.NewCustomerDTO;
import com.example.backend.model.dto.NewEmploeeyDTO;
import com.example.backend.service.EmploeeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/emploeey")
public class EmploeeyController {
    private EmploeeyService service;

    @Autowired
    public EmploeeyController(EmploeeyService service){
        this.service = service;
    }

    @PostMapping("/new-emploeey")
    public ResponseEntity<Object> newEmploeeyr(@RequestBody NewEmploeeyDTO dto){
        service.newEmploeey(dto);
        return new ResponseEntity<>(Map.of("mensagem", "Conta criada."), HttpStatus.CREATED);
    }
}
