package com.example.repository;

import com.example.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    List<Ruta> findByEstado(String estado);
    List<Ruta> findByOrigenAndDestino(String origen, String destino);
    boolean existsByOrigenAndDestino(String origen, String destino);
} 