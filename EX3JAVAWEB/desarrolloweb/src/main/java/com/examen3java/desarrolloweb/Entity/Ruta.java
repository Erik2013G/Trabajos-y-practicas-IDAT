package com.examen3java.desarrolloweb.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "rutas")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private Long id;

    @NotBlank(message = "El origen es obligatorio")
    @Column(nullable = false, length = 100)
    private String origen;

    @NotBlank(message = "El destino es obligatorio")
    @Column(nullable = false, length = 100)
    private String destino;

    @NotNull(message = "La duración es obligatoria")
    @Positive(message = "La duración debe ser positiva")
    @Column(nullable = false)
    private Integer duracionEstimadaHoras;

    @NotNull(message = "La distancia es obligatoria")
    @Positive(message = "La distancia debe ser positiva")
    @Column(nullable = false)
    private Double distanciaKilometros;

    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL)
    private List<Viaje> viajes = new ArrayList<>();

    // Constructores
    public Ruta() {}

    public Ruta(String origen, String destino, Integer duracionEstimadaHoras, Double distanciaKilometros) {
        this.origen = origen;
        this.destino = destino;
        this.duracionEstimadaHoras = duracionEstimadaHoras;
        this.distanciaKilometros = distanciaKilometros;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getDuracionEstimadaHoras() {
        return duracionEstimadaHoras;
    }

    public void setDuracionEstimadaHoras(Integer duracionEstimadaHoras) {
        this.duracionEstimadaHoras = duracionEstimadaHoras;
    }

    public Double getDistanciaKilometros() {
        return distanciaKilometros;
    }

    public void setDistanciaKilometros(Double distanciaKilometros) {
        this.distanciaKilometros = distanciaKilometros;
    }

    public List<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    @Override
    public String toString() {
        return origen + " - " + destino;
    }

    public String getNombre() {
        throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
    }
}
