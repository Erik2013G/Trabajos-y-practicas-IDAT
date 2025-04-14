package com.examen3java.desarrolloweb.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "buses")
@Getter
@Setter
public class Bus {

    // Constantes para validaciones
    public static final int CAPACIDAD_MINIMA = 10;
    public static final int CAPACIDAD_MAXIMA = 100;
    public static final int ANIO_MINIMO_FABRICACION = 2000;
    public static final String PATRON_PLACA = "^[A-Z0-9]{6}$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bus")
    private Long id;

    @NotBlank(message = "La placa es obligatoria")
    @Pattern(regexp = PATRON_PLACA, message = "La placa debe tener 6 caracteres alfanuméricos en mayúsculas")
    @Column(nullable = false, length = 6, unique = true)
    private String placa;

    @NotBlank(message = "El modelo es obligatorio")
    @Column(nullable = false, length = 50)
    private String modelo;

    @NotBlank(message = "La marca es obligatoria")
    @Column(nullable = false, length = 50)
    private String marca;

    @NotNull(message = "El año de fabricación es obligatorio")
    @Min(value = ANIO_MINIMO_FABRICACION, message = "El año mínimo de fabricación es " + ANIO_MINIMO_FABRICACION)
    @Max(value = 2025, message = "El año no puede ser mayor al actual")
    @Column(nullable = false)
    private Integer anioFabricacion;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = CAPACIDAD_MINIMA, message = "La capacidad mínima es " + CAPACIDAD_MINIMA)
    @Max(value = CAPACIDAD_MAXIMA, message = "La capacidad máxima es " + CAPACIDAD_MAXIMA)
    @Column(nullable = false)
    private Integer capacidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoBus tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoBus estado = EstadoBus.DISPONIBLE;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Viaje> viajes = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDate fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;

    // Enums mejorados
    public enum TipoBus {
        NORMAL("Normal", 1.0),
        SEMI_CAMA("Semi Cama", 1.2),
        CAMA("Cama", 1.5),
        VIP("VIP", 2.0);

        private final String descripcion;
        private final double factorPrecio;

        TipoBus(String descripcion, double factorPrecio) {
            this.descripcion = descripcion;
            this.factorPrecio = factorPrecio;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public double getFactorPrecio() {
            return factorPrecio;
        }
    }

    public enum EstadoBus {
        DISPONIBLE("Disponible"),
        EN_MANTENIMIENTO("En mantenimiento"),
        INACTIVO("Inactivo"),
        EN_VIAJE("En viaje");

        private final String descripcion;

        EstadoBus(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public static boolean isValid(String estado) {
            try {
                EstadoBus.valueOf(estado);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
    }

    // Constructores
    public Bus() {}

    public Bus(String placa, String modelo, String marca, Integer anioFabricacion, 
              Integer capacidad, TipoBus tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anioFabricacion = anioFabricacion;
        this.capacidad = capacidad;
        this.tipo = tipo;
    }

    // Métodos de negocio
    public void cambiarEstado(EstadoBus nuevoEstado) {
        if (this.estado == EstadoBus.EN_VIAJE && nuevoEstado != EstadoBus.DISPONIBLE) {
            throw new IllegalStateException("No se puede cambiar el estado de un bus en viaje a " + nuevoEstado);
        }
        this.estado = nuevoEstado;
    }

    public boolean estaDisponibleParaViaje(LocalDate fechaViaje) {
        return this.estado == EstadoBus.DISPONIBLE &&
               !this.viajes.stream()
                   .anyMatch(v -> v.getFechaHoraSalida().toLocalDate().equals(fechaViaje));
    }

    // Métodos utilitarios
    public String getInfoCompleta() {
        return String.format("%s %s (%s) - Capacidad: %d - Tipo: %s",
                marca, modelo, placa, capacidad, tipo.getDescripcion());
    }

    @Override
    public String toString() {
        return getInfoCompleta();
    }
}