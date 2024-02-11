package com.example.backend.service.impl;

import com.example.backend.exceptions.DefaultError;
import com.example.backend.model.dto.CustomerInfoDTO;
import com.example.backend.model.dto.NewCustomerDTO;
import com.example.backend.model.entity.Customer;
import com.example.backend.repository.CustomerRepository;
import com.example.backend.service.CustomerService;
import com.example.backend.utils.RolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    public void newCustomer(NewCustomerDTO dto) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        Customer customer = new Customer(
                UUID.randomUUID().toString(),
                dto.name(),
                dto.phone(),
                dto.email(),
                dto.login(),
                encryptedPassword,
                true,
                dto.cpf(),
                RolesEnum.CUSTOMER.getRoleName()
        );
        repository.save(customer);
    }

    public CustomerInfoDTO getCustomerByCpf(String cpf) {
        Customer customer = repository.findByCpf(Long.parseLong(cpf));
        if (customer == null) throw new DefaultError("Usuário não encontrado.", HttpStatus.NOT_FOUND);
        return new CustomerInfoDTO(
                customer.getName(),
                customer.getCpf(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getLogin(),
                customer.getRole()
        );
    }
}
