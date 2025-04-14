package com.examen3java.desarrolloweb.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen3java.desarrolloweb.Entity.Proveedor;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByIddistribuidorContaining(String iddistribuidor);
    List<Proveedor> findByPais(String pais);
    // Búsqueda por estado
    List<Proveedor> findByEstado(String estado);
    List<Proveedor> findByMontoCreditoGreaterThanEqual(BigDecimal monto);
    List<Proveedor> findByNombredistribuidorContainingAndPais(String nombredistribuidor, String pais);
    // Nuevo método para buscar por nombre ignorando mayúsculas y minúsculas
    List<Proveedor> findByNombredistribuidorContainingIgnoreCase(String nombredistribuidor);
    // Búsqueda por nombre (usando Containing para LIKE)
    List<Proveedor> findByNombreDistribuidorContaining(String nombre);
    
    // Búsqueda por RUC exacto
    Optional<Proveedor> findByRuc(String ruc);
}