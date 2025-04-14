package com.examen3java.desarrolloweb.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen3java.desarrolloweb.Entity.Proveedor;
import com.examen3java.desarrolloweb.repository.ProveedoresRepository;

@Service
public class ProveedoresServiceImpl implements ProveedoresService {

    @Autowired
    private ProveedoresRepository proveedoresRepository;

    @Override
    public List<Proveedor> findAll() {
        return proveedoresRepository.findAll();
    }

    @Override
    public Proveedor findById(Long id) {
        return proveedoresRepository.findById(id).orElse(null);
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return proveedoresRepository.save(proveedor);
    }

    @Override
    public void deleteById(Long id) {
        proveedoresRepository.deleteById(id);
    }

    @Override
    public List<Proveedor> findByPais(String pais) {
        return proveedoresRepository.findByPais(pais);
    }

    @Override
    public List<Proveedor> findByEstado(String estado) {
        return proveedoresRepository.findByEstado(estado);
    }

    @Override
    public List<Proveedor> findByMontoCredito(BigDecimal monto) {
        return proveedoresRepository.findByMontoCreditoGreaterThanEqual(monto);
    }

    @Override
    public List<Proveedor> buscarPorIdYPais(String iddistribuidor, String pais) {
        if (iddistribuidor != null && pais != null) {
            return proveedoresRepository.findByNombredistribuidorContainingAndPais(iddistribuidor, pais);
        } else if (iddistribuidor != null) {
            return proveedoresRepository.findByIddistribuidorContaining(iddistribuidor);
        } else if (pais != null) {
            return proveedoresRepository.findByPais(pais);
        }
        return findAll();
    }
    @Override
    public List<Proveedor> findByNombredistribuidor(String nombredistribuidor) {
        return proveedoresRepository.findByNombredistribuidorContainingIgnoreCase(nombredistribuidor);
    }
}
