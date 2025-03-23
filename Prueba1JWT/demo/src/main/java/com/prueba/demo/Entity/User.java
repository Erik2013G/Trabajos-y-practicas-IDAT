package com.prueba.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Tabla de usuarios
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incremento del ID
    private Long id;

    @Column(unique = true, nullable = false) // Nombre de usuario único y no nulo
    private String username;

    @Column(nullable = false) // La contraseña no puede ser nula
    private String password;

    @Column(unique = true, nullable = false) // El correo debe ser único y no nulo
    private String email;

    private boolean enabled;
    private boolean validEmail;
    private boolean validRole;
    private boolean validAuthorities;
    

    // Constructor vacío (necesario para JPA)
    public User(String email2, String name, String provider, String providerId) {}

    // Constructor con todos los campos
    public User(String username, String password, String email, boolean enabled, 
                      boolean validEmail, boolean validRole, boolean validAuthorities) {
                        this.username = username;
                        this.password = password;
                        this.email = email;
                        this.enabled = enabled;
                        this.validEmail = validEmail;
                        this.validRole = validRole;
                        this.validAuthorities = validAuthorities;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    // Método toString para depuración
    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", validEmail=" + validEmail +
                ", validRole=" + validRole +
                ", validAuthorities=" + validAuthorities +
                '}';
    }
}
