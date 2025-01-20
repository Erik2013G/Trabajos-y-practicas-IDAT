package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID auto-incremental en la base de datos
    private Long id;

    private String nombreprof;  // Nombre del instructor
    private String correo;       // Correo electrónico del instructor
    private String telefono;     // Teléfono del instructor
    private String especialidad; // Especialidad del instructor

    // Constructor sin parámetros
    public Instructor() {
    }

    // Constructor con parámetros
    public Instructor(String nombreprof, String correo, String telefono, String especialidad) {
        this.nombreprof = nombreprof;
        this.correo = correo;
        this.telefono = telefono;
        this.especialidad = especialidad;
    }

    // Getters y setters
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

    // Método toString para depuración (opcional)
    @Override
    public String toString() {
        return "Instructor{id=" + id +
               ", nombreprof='" + nombreprof + '\'' +
               ", correo='" + correo + '\'' +
               ", telefono='" + telefono + '\'' +
               ", especialidad='" + especialidad + '\'' +
               '}';
    }
}
