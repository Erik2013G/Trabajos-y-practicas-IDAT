package com.examen3java.desarrolloweb.service.impl;

import com.examen3java.desarrolloweb.model.Pasaje;
import com.examen3java.desarrolloweb.repository.PasajeRepository;
import com.examen3java.desarrolloweb.service.IPasajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PasajeServiceImpl implements IPasajeService {

    @Autowired
    private PasajeRepository pasajeRepository;

    @Override
    public List<Pasaje> listarTodos() {
        return pasajeRepository.findAll();
    }

    @Override
    public Optional<Pasaje> obtenerPorId(Long id) {
        return pasajeRepository.findById(id);
    }

    @Override
    public List<Pasaje> listarPorViaje(Long viajeId) {
        return pasajeRepository.findByViajeId(viajeId);
    }

    @Override
    public List<Pasaje> listarPorCliente(Long clienteId) {
        return pasajeRepository.findByClienteId(clienteId);
    }

    @Override
    public List<Pasaje> listarPorEstado(String estado) {
        return pasajeRepository.findByEstado(estado);
    }

    @Override
    public List<Pasaje> listarPorRangoDeFechas(LocalDateTime inicio, LocalDateTime fin) {
        return pasajeRepository.findByFechaCompraBetween(inicio, fin);
    }

    @Override
    public List<Pasaje> buscarPorNumeroAsiento(Integer numeroAsiento) {
        return pasajeRepository.findByNumeroAsiento(numeroAsiento);
    }

    @Override
    public Pasaje guardar(Pasaje pasaje) {
        validarPasaje(pasaje);
        if (pasaje.getId() == null) {
            pasaje.setFechaCompra(LocalDateTime.now());
            pasaje.setEstado("RESERVADO");
        }
        return pasajeRepository.save(pasaje);
    }

    @Override
    public void eliminar(Long id) {
        pasajeRepository.deleteById(id);
    }

    @Override
    public boolean verificarDisponibilidadAsiento(Long viajeId, Integer numeroAsiento) {
        return !pasajeRepository.existsByViajeIdAndNumeroAsiento(viajeId, numeroAsiento);
    }

    private void validarPasaje(Pasaje pasaje) {
        if (pasaje.getViaje() == null) {
            throw new IllegalArgumentException("Debe especificar un viaje");
        }

        if (pasaje.getCliente() == null) {
            throw new IllegalArgumentException("Debe especificar un cliente");
        }

        if (pasaje.getNumeroAsiento() == null) {
            throw new IllegalArgumentException("Debe especificar un número de asiento");
        }

        if (!verificarDisponibilidadAsiento(pasaje.getViaje().getId(), pasaje.getNumeroAsiento())) {
            throw new IllegalArgumentException("El asiento seleccionado no está disponible");
        }

        if (pasaje.getPrecio() == null || pasaje.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
    }
} 