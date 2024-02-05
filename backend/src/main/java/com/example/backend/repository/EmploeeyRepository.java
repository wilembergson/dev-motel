package com.example.backend.repository;

import com.example.backend.model.entity.Emploeey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmploeeyRepository extends JpaRepository<Emploeey, UUID> {
}
