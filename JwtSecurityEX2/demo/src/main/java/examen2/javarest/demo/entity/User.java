package examen2.javarest.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Collection;
import java.util.Collections;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private boolean validEmail;
    private boolean validRole;
    private boolean validAuthorities;
    private String role;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isValidEmail() {
        return validEmail;
    }

    public void setValidEmail(boolean validEmail) {
        this.validEmail = validEmail;
    }

    public boolean isValidRole() {
        return validRole;
    }

    public void setValidRole(boolean validRole) {
        this.validRole = validRole;
    }

    public boolean isValidAuthorities() {
        return validAuthorities;
    }

    public void setValidAuthorities(boolean validAuthorities) {
        this.validAuthorities = validAuthorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setAuthorities(String authorities) {
        if ("ADMIN".equals(authorities) || "USER".equals(authorities)) {
            this.role = authorities;
            this.validAuthorities = true;
        } else {
            this.validAuthorities = false;
            throw new IllegalArgumentException("Rol no válido: " + authorities);
        }
    }
}