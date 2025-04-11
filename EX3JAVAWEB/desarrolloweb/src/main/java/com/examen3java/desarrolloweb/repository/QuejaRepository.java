package com.examen3java.desarrolloweb.repository;

import com.examen3java.desarrolloweb.model.Queja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuejaRepository extends JpaRepository<Queja, Long> {
    List<Queja> findByEstado(String estado);
    List<Queja> findByTipo(String tipo);
    List<Queja> findByPrioridad(String prioridad);
    List<Queja> findByClienteId(Long clienteId);
    List<Queja> findByViajeId(Long viajeId);
} 