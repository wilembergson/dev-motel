package com.example.backend.service.impl;

import com.example.backend.model.dto.NewCustomerDTO;
import com.example.backend.model.entity.Customer;
import com.example.backend.repository.CustomerRepository;
import com.example.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    public void newCustomer(NewCustomerDTO dto){
        System.out.println(dto);
        Customer customer = new Customer(
                UUID.randomUUID().toString(),
                dto.getName(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getUsername(),
                dto.getPassword(),
                "CUSTOMER",
                true,
                dto.getCpf()
        );
        repository.save(customer);
    }
}
