package com.examen3java.desarrolloweb.repository;

import com.examen3java.desarrolloweb.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByDni(String dni);
    boolean existsByDni(String dni);
    List<Empleado> findByCargo(String cargo);
    List<Empleado> findByEstado(String estado);
    Optional<Empleado> findByEmail(String email);
    List<Empleado> findByApellidosContainingIgnoreCase(String apellidos);
} 