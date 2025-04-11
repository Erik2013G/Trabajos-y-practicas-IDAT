package com.examen3java.desarrolloweb.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen3java.desarrolloweb.Entity.Pasaje;

@Repository
public interface PasajeRepository extends JpaRepository<Pasaje, Long> {
    List<Pasaje> findByViajeId(Long viajeId);
    List<Pasaje> findByClienteId(Long clienteId);
    List<Pasaje> findByEstado(String estado);
    List<Pasaje> findByFechaCompraBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Pasaje> findByNumeroAsiento(Integer numeroAsiento);
    boolean existsByViajeIdAndNumeroAsiento(Long viajeId, Integer numeroAsiento);
    Optional<Pasaje> findByNumeroAsiento(String numeroAsiento);
    Optional<Pasaje> findByNumeroPasaje(String numeroPasaje); // Declaración correcta
}