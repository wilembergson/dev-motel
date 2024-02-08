package com.example.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Emploeey extends GeneralUser{

    public Emploeey(String id, String name, Long phone, String email, String username, String password, String role, boolean active, Long registration) {
        super(id, name, phone, email, username, password, active);
        this.role = role;
        this.registration = registration;
    }

    @Column(name = "role")
    private String role;

    @Column(name = "registration", unique = true)
    private Long registration;

}
