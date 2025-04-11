package com.example.service;

import com.example.model.Bus;
import com.example.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public List<Bus> listarTodos() {
        return busRepository.findAll();
    }

    public Optional<Bus> obtenerPorId(Long id) {
        return busRepository.findById(id);
    }

    public List<Bus> listarPorEstado(String estado) {
        return busRepository.findByEstado(estado);
    }

    public List<Bus> listarPorTipo(String tipo) {
        return busRepository.findByTipo(tipo);
    }

    public Bus guardar(Bus bus) {
        validarBus(bus);
        return busRepository.save(bus);
    }

    public void eliminar(Long id) {
        busRepository.deleteById(id);
    }

    private void validarBus(Bus bus) {
        if (bus.getId() == null && busRepository.existsByPlaca(bus.getPlaca())) {
            throw new IllegalArgumentException("Ya existe un bus con la misma placa");
        }

        if (bus.getCapacidad() < 20 || bus.getCapacidad() > 80) {
            throw new IllegalArgumentException("La capacidad debe estar entre 20 y 80 pasajeros");
        }

        int anioActual = java.time.Year.now().getValue();
        if (bus.getAnioFabricacion() < 2000 || bus.getAnioFabricacion() > anioActual) {
            throw new IllegalArgumentException("El año de fabricación debe estar entre 2000 y el año actual");
        }
    }
} 