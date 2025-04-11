package com.examen3java.desarrolloweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen3java.desarrolloweb.Entity.Ruta;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    List<Ruta> findByEstado(String estado);
    List<Ruta> findByOrigenAndDestino(String origen, String destino);
    boolean existsByOrigenAndDestino(String origen, String destino);
} 