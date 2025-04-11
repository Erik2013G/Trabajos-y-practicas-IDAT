package com.examen3java.desarrolloweb.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "viajes")
@Getter
@Setter
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viaje")
    private Long id;

    @NotNull(message = "La ruta es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ruta_id", nullable = false)
    private Ruta ruta;

    @NotNull(message = "El bus es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @NotNull(message = "La fecha de salida es obligatoria")
    @Future(message = "La fecha de salida debe ser en el futuro")
    @Column(nullable = false)
    private LocalDateTime fechaHoraSalida;

    @NotNull(message = "La fecha de llegada es obligatoria")
    @Future(message = "La fecha de llegada debe ser en el futuro")
    @Column(nullable = false)
    private LocalDateTime fechaHoraLlegada;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio base debe ser mayor que 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener máximo 10 enteros y 2 decimales")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioBase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoViaje estado = EstadoViaje.PROGRAMADO;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pasaje> pasajes = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Version
    private Long version;

    @ManyToOne
    private Cliente cliente;

    private Integer asientosDisponibles;

    public enum EstadoViaje {
        PROGRAMADO, EN_CURSO, COMPLETADO, CANCELADO
    }

    // Constructores
    public Viaje() {}

    public Viaje(Ruta ruta, Bus bus, LocalDateTime fechaHoraSalida,
                LocalDateTime fechaHoraLlegada, BigDecimal precioBase) {
        this.ruta = Objects.requireNonNull(ruta, "La ruta no puede ser nula");
        this.bus = Objects.requireNonNull(bus, "El bus no puede ser nulo");
        this.fechaHoraSalida = Objects.requireNonNull(fechaHoraSalida, "La fecha de salida no puede ser nula");
        this.fechaHoraLlegada = Objects.requireNonNull(fechaHoraLlegada, "La fecha de llegada no puede ser nula");
        this.precioBase = Objects.requireNonNull(precioBase, "El precio base no puede ser nulo");
        validateFechas();
    }

    // Métodos de negocio
    public void cancelar() {
        if (this.estado == EstadoViaje.EN_CURSO) {
            throw new IllegalStateException("No se puede cancelar un viaje en curso");
        }
        this.estado = EstadoViaje.CANCELADO;
    }

    public void iniciar() {
        if (this.estado != EstadoViaje.PROGRAMADO) {
            throw new IllegalStateException("Solo se pueden iniciar viajes programados");
        }
        if (LocalDateTime.now().isBefore(this.fechaHoraSalida)) {
            throw new IllegalStateException("No se puede iniciar un viaje antes de su hora programada");
        }
        this.estado = EstadoViaje.EN_CURSO;
    }

    public void completar() {
        if (this.estado != EstadoViaje.EN_CURSO) {
            throw new IllegalStateException("Solo se pueden completar viajes en curso");
        }
        this.estado = EstadoViaje.COMPLETADO;
    }

    public boolean tieneAsientosDisponibles() {
        return this.asientosDisponibles > 0;
    }

    public void reservarAsiento() {
        if (!tieneAsientosDisponibles()) {
            throw new IllegalStateException("No hay asientos disponibles");
        }
        this.asientosDisponibles--;
    }

    public void liberarAsiento() {
        if (this.asientosDisponibles < bus.getCapacidad()) {
            this.asientosDisponibles++;
        }
    }

    // Métodos auxiliares
    private void validateFechas() {
        if (fechaHoraSalida.isAfter(fechaHoraLlegada)) {
            throw new IllegalArgumentException("La fecha de salida no puede ser después de la fecha de llegada");
        }
    }

    @Transient
    public String getFechaFormateada() {
        if (this.fechaHoraSalida == null) return "";
        return fechaHoraSalida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Transient
    public String getResumen() {
        return String.format("%s - %s | %s | %s",
                ruta.getOrigen(),
                ruta.getDestino(),
                getFechaFormateada(),
                estado);
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Viaje)) return false;
        Viaje viaje = (Viaje) o;
        return Objects.equals(id, viaje.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Viaje{" +
                "id=" + id +
                ", ruta=" + (ruta != null ? ruta.getOrigen() + "-" + ruta.getDestino() : "null") +
                ", bus=" + (bus != null ? bus.getPlaca() : "null") +
                ", estado=" + estado +
                '}';
    }

    // DTO estático
    public static record ViajeDTO(
        Long id,
        String rutaInfo,
        String busInfo,
        String fechaHoraSalida,
        String fechaHoraLlegada,
        BigDecimal precioBase,
        EstadoViaje estado,
        int totalPasajes
    ) {
        public ViajeDTO(Viaje viaje) {
            this(
                viaje.getId(),
                viaje.getRuta() != null ? viaje.getRuta().getOrigen() + " - " + viaje.getRuta().getDestino() : "",
                viaje.getBus() != null ? viaje.getBus().getPlaca() + " - " + viaje.getBus().getModelo() : "",
                viaje.getFechaHoraSalida() != null ? viaje.getFechaHoraSalida().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "",
                viaje.getFechaHoraLlegada() != null ? viaje.getFechaHoraLlegada().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "",
                viaje.getPrecioBase(),
                viaje.getEstado(),
                viaje.getPasajes() != null ? viaje.getPasajes().size() : 0
            );
        }
    }
}