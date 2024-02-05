package com.example.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Emploeey extends GeneralUser{

    @Column(name = "registration")
    private Integer registration;
}
