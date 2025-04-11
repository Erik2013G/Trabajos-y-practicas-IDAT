package com.example.repository;

import com.example.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findByEstado(String estado);
    List<Bus> findByTipo(String tipo);
    boolean existsByPlaca(String placa);
    Bus findByPlaca(String placa);
} 