package com.examen3java.desarrolloweb.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "quejas")
public class Queja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_queja")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viaje_id")
    private Viaje viaje; // Opcional - no todas las quejas son sobre viajes

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_asignado_id")
    private Empleado empleadoAsignado;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 10, max = 1000, message = "La descripción debe tener entre 10 y 1000 caracteres")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column
    private LocalDateTime fechaResolucion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoQueja estado = EstadoQueja.PENDIENTE;

    @Size(max = 2000, message = "La solución no puede exceder los 2000 caracteres")
    @Column(columnDefinition = "TEXT")
    private String solucion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoQueja tipo;

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // Enums
    public enum EstadoQueja {
        PENDIENTE, EN_REVISION, RESUELTA, RECHAZADA
    }

    public enum TipoQueja {
        SERVICIO, VIAJE, FACTURACION, EQUIPAJE, OTROS
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrioridadQueja prioridad; // Nueva propiedad

// Enum para las prioridades
    public enum PrioridadQueja {
        BAJA, MEDIA, ALTA, CRITICA
    }

    // Constructores
    public Queja() {
    }

    public Queja(Cliente cliente, String descripcion, TipoQueja tipo) {
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    // Métodos de negocio
    public void asignarEmpleado(Empleado empleado) {
        this.empleadoAsignado = empleado;
        this.estado = EstadoQueja.EN_REVISION;
    }

    public void resolverQueja(String solucion) {
        this.solucion = solucion;
        this.estado = EstadoQueja.RESUELTA;
        this.fechaResolucion = LocalDateTime.now();
    }

    public void rechazarQueja(String motivo) {
        this.solucion = motivo;
        this.estado = EstadoQueja.RECHAZADA;
        this.fechaResolucion = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Empleado getEmpleadoAsignado() {
        return empleadoAsignado;
    }

    public void setEmpleadoAsignado(Empleado empleadoAsignado) {
        this.empleadoAsignado = empleadoAsignado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public EstadoQueja getEstado() {
        return estado;
    }

    public void setEstado(EstadoQueja estado) {
        this.estado = estado;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public TipoQueja getTipo() {
        return tipo;
    }

    public void setTipo(TipoQueja tipo) {
        this.tipo = tipo;
    }

    public PrioridadQueja getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadQueja prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Queja #" + id + " - " + estado + " (" + tipo + ")";
    }
}
