package com.examen3java.desarrolloweb.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen3java.desarrolloweb.Entity.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    List<Viaje> findByClienteId(Long clienteId);
    List<Viaje> findByBusPlaca(String busPlaca);
    List<Viaje> findByRutaNombre(String rutaNombre);
    List<Viaje> findByRutaOrigen(String rutaOrigen);
    List<Viaje> findByRutaDestino(String rutaDestino);
    List<Viaje> findByRutaId(Long rutaId);
    List<Viaje> findByEstado(Viaje.EstadoViaje estado);
    List<Viaje> findByFechaHoraSalidaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Viaje> findByBusId(Long busId);
}