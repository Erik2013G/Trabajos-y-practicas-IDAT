package com.examen3java.desarrolloweb.Entity;

import java.math.BigDecimal;
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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pasajes")
public class Pasaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasaje")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viaje_id", nullable = false)
    private Viaje viaje;

    @NotBlank(message = "El número de asiento es obligatorio")
    @Column(nullable = false, length = 10)
    private String numeroAsiento;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPasaje estado = EstadoPasaje.RESERVADO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaEmision = LocalDateTime.now();

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public enum EstadoPasaje {
        RESERVADO, CONFIRMADO, CANCELADO, UTILIZADO
    }

    // Constructores
    public Pasaje() {}

    public Pasaje(Cliente cliente, Viaje viaje, String numeroAsiento, BigDecimal precio) {
        this.cliente = cliente;
        this.viaje = viaje;
        this.numeroAsiento = numeroAsiento;
        this.precio = precio;
    }

    // Métodos de negocio
    public void confirmar() {
        this.estado = EstadoPasaje.CONFIRMADO;
    }

    public void cancelar() {
        this.estado = EstadoPasaje.CANCELADO;
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

    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(String numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public EstadoPasaje getEstado() {
        return estado;
    }

    public void setEstado(EstadoPasaje estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    @Override
    public String toString() {
        return "Pasaje #" + id + " - " + estado;
    }
}