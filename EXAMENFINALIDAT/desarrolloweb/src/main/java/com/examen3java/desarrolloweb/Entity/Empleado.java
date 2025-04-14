package com.examen3java.desarrolloweb.Entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long id;

    @NotBlank(message = "Los nombres son obligatorios")
    @Column(nullable = false, length = 100)
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Column(nullable = false, length = 100)
    private String apellidos;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "^[0-9]{8}$", message = "DNI debe tener 8 dígitos")
    @Column(nullable = false, length = 8, unique = true)
    private String dni;

    @NotBlank(message = "El código de empleado es obligatorio")
    @Column(nullable = false, length = 20, unique = true)
    private String codigoEmpleado;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{9}$", message = "Teléfono debe tener 9 dígitos")
    @Column(nullable = false, length = 9)
    private String telefono;

    @Email(message = "Debe ser un email válido")
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Departamento departamento;

    @OneToMany(mappedBy = "empleadoAsignado")
    private List<Queja> quejasAsignadas = new ArrayList<>();

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // Enums
    public enum Departamento {
        ATENCION_CLIENTE, OPERACIONES, ADMINISTRACION, SISTEMAS, GERENCIA
    }

    // Constructores
    public Empleado() {}

    public Empleado(String nombres, String apellidos, String dni, String codigoEmpleado, 
                   String telefono, String email, Departamento departamento) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.codigoEmpleado = codigoEmpleado;
        this.telefono = telefono;
        this.email = email;
        this.departamento = departamento;
    }

    // Métodos de negocio
    public void desactivarEmpleado() {
        this.activo = false;
    }

    public int getQuejasPendientes() {
        return (int) quejasAsignadas.stream()
                .filter(q -> q.getEstado() == Queja.EstadoQueja.EN_REVISION)
                .count();
    }

    public void asignarQueja(Queja queja) {
        quejasAsignadas.add(queja);
        queja.setEmpleadoAsignado(this);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Queja> getQuejasAsignadas() {
        return quejasAsignadas;
    }

    public void setQuejasAsignadas(List<Queja> quejasAsignadas) {
        this.quejasAsignadas = quejasAsignadas;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    @Override
    public String toString() {
        return apellidos + ", " + nombres + " (" + codigoEmpleado + ")";
    }
}
