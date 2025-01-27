package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor", uniqueConstraints = {
    @UniqueConstraint(columnNames = "correo") // Evita correos duplicados
})
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-incremental
    private Long id;

    @Column(name = "nombre_prof", nullable = false, length = 100) // Nombre del instructor
    private String nombreprof;

    @Column(name = "correo", nullable = false, length = 100, unique = true) // Correo único
    private String correo;

    @Column(name = "telefono", length = 20) // Teléfono
    private String telefono;

    @Column(name = "especialidad", length = 100) // Especialidad
    private String especialidad;

    // Constructor vacío
    public Instructor() {
    }

    // Constructor con parámetros
    public Instructor(String nombreprof, String correo, String telefono, String especialidad) {
        this.nombreprof = nombreprof;
        this.correo = correo;
        this.telefono = telefono;
        this.especialidad = especialidad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreprof() {
        return nombreprof;
    }

    public void setNombreprof(String nombreprof) {
        this.nombreprof = nombreprof;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", nombreprof='" + nombreprof + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
