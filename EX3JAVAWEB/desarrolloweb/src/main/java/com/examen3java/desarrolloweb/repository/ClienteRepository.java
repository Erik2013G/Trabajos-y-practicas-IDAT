package com.examen3java.desarrolloweb.repository;

import com.examen3java.desarrolloweb.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDni(String dni);
    boolean existsByDni(String dni);
    List<Cliente> findByApellidosContainingIgnoreCase(String apellidos);
    List<Cliente> findByNombresContainingIgnoreCase(String nombres);
    Optional<Cliente> findByEmail(String email);
} 