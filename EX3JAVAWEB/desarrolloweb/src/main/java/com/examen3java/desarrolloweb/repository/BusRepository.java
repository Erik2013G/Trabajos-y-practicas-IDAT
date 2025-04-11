package com.examen3java.desarrolloweb.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examen3java.desarrolloweb.Entity.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsByPlaca(String placa);
    
    List<Bus> findByEstado(String estado);
    
    List<Bus> findByTipo(String tipo);
    
    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END " +
           "FROM Viaje v WHERE v.bus.id = :busId AND v.fechaHoraSalida > CURRENT_DATE")
    boolean existsByBusIdAndViajesFuturos(Long busId, LocalDate fechaActual);
}