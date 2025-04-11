package com.examen3java.desarrolloweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen3java.desarrolloweb.Entity.Queja;

@Repository
public interface QuejaRepository extends JpaRepository<Queja, Long> {
    List<Queja> findByEstado(String estado);
    List<Queja> findByTipo(String tipo);
    List<Queja> findByPrioridad(String prioridad);
    List<Queja> findByClienteId(Long clienteId);
    List<Queja> findByViajeId(Long viajeId);
} 