package com.example.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@NoArgsConstructor
public class Customer extends GeneralUser{

    public Customer(String id, String name, Integer phone, String email, String username, String password, String role, boolean active, Integer cpf) {
        super(id, name, phone, email, username, password, role, active);
        this.cpf = cpf;
    }
    @Column(name = "cfp")
    private Integer cpf;
}
