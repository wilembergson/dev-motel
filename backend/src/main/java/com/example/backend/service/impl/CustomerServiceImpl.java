package com.example.backend.service.impl;

import com.example.backend.model.dto.NewCustomerDTO;
import com.example.backend.model.entity.Customer;
import com.example.backend.repository.CustomerRepository;
import com.example.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    public void newCustomer(NewCustomerDTO dto){
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        Customer customer = new Customer(
                UUID.randomUUID().toString(),
                dto.name(),
                dto.phone(),
                dto.email(),
                dto.login(),
                encryptedPassword,
                true,
                dto.cpf()
        );
        repository.save(customer);
    }
}
