package com.example.backend.controller;

import com.example.backend.model.dto.NewCustomerDTO;
import com.example.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service){
        this.service = service;
    }

    @PostMapping("/new-customer")
    public ResponseEntity<Object> newCustomer(@RequestBody NewCustomerDTO dto){
        service.newCustomer(dto);
        return new ResponseEntity<>(Map.of("mensagem", "Conta criada."), HttpStatus.CREATED);
    }
}
