package com.examen3java.desarrolloweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen3java.desarrolloweb.Entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDni(String dni);
    boolean existsByDni(String dni);
    List<Cliente> findByApellidosContainingIgnoreCase(String apellidos);
    List<Cliente> findByNombresContainingIgnoreCase(String nombres);
    Optional<Cliente> findByEmail(String email);
} 