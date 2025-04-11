package com.examen3java.desarrolloweb.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "buses")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bus")
    private Long id;

    @NotBlank(message = "La placa es obligatoria")
    @Pattern(regexp = "^[A-Z0-9]{6}$", message = "La placa debe tener 6 caracteres alfanuméricos")
    @Column(nullable = false, length = 6, unique = true)
    private String placa;

    @NotBlank(message = "El modelo es obligatorio")
    @Column(nullable = false, length = 50)
    private String modelo;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 10, message = "La capacidad mínima es 10")
    @Max(value = 100, message = "La capacidad máxima es 100")
    @Column(nullable = false)
    private Integer capacidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoBus tipo;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Viaje> viajes = new ArrayList<>();

    public enum TipoBus {
        NORMAL, SEMI_CAMA, CAMA, VIP
    }

    // Constructores
    public Bus() {}

    public Bus(String placa, String modelo, Integer capacidad, TipoBus tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.tipo = tipo;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public TipoBus getTipo() {
        return tipo;
    }

    public void setTipo(TipoBus tipo) {
        this.tipo = tipo;
    }

    public List<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    @Override
    public String toString() {
        return modelo + " (" + placa + ")";
    }
}