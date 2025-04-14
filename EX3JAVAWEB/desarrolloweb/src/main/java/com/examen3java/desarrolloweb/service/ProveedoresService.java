package com.examen3java.desarrolloweb.service;
import java.math.BigDecimal;
import java.util.List;

import com.examen3java.desarrolloweb.Entity.Proveedor;


public interface ProveedoresService {
    List<Proveedor> findAll();
    Proveedor findById(Long id);
    Proveedor save(Proveedor proveedores);
    void deleteById(Long id);
    List<Proveedor> findByPais(String pais);
    List<Proveedor> findByEstado(String estado);
    List<Proveedor> findByMontoCredito(BigDecimal monto);
    List<Proveedor> buscarPorIdYPais(String iddistribuidor, String pais);
    List<Proveedor> findByNombredistribuidor(String nombre);
}
