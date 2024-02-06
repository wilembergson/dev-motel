package com.example.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Emploeey extends GeneralUser{

    public Emploeey(String id, String name, Long phone, String email, String username, String password, String role, boolean active, Long registration) {
        super(id, name, phone, email, username, password, role, active);
        this.registration = registration;
    }
    @Column(name = "registration", unique = true)
    private Long registration;
}
