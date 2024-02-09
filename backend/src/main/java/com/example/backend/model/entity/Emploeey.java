package com.example.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Entity
@NoArgsConstructor
public class Emploeey extends GeneralUser{

    public Emploeey(String id, String name, Long phone, String email, String login, String password, String role, boolean active, Long registration) {
        super(id, name, phone, email, login, password, active);
        this.role = role;
        this.registration = registration;
    }

    @Column(name = "role")
    private String role;

    @Column(name = "registration", unique = true)
    private Long registration;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
