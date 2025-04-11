package com.examen3java.desarrolloweb.service;

import com.examen3java.desarrolloweb.model.Queja;
import com.examen3java.desarrolloweb.repository.QuejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuejaService {

    @Autowired
    private QuejaRepository quejaRepository;

    public List<Queja> listarTodas() {
        return quejaRepository.findAll();
    }

    public Optional<Queja> obtenerPorId(Long id) {
        return quejaRepository.findById(id);
    }

    public List<Queja> listarPorEstado(String estado) {
        return quejaRepository.findByEstado(estado);
    }

    public List<Queja> listarPorTipo(String tipo) {
        return quejaRepository.findByTipo(tipo);
    }

    public List<Queja> listarPorPrioridad(String prioridad) {
        return quejaRepository.findByPrioridad(prioridad);
    }

    public List<Queja> listarPorCliente(Long clienteId) {
        return quejaRepository.findByClienteId(clienteId);
    }

    public List<Queja> listarPorViaje(Long viajeId) {
        return quejaRepository.findByViajeId(viajeId);
    }

    public Queja guardar(Queja queja) {
        validarQueja(queja);
        if (queja.getId() == null) {
            queja.setEstado("PENDIENTE");
        }
        return quejaRepository.save(queja);
    }

    public void eliminar(Long id) {
        quejaRepository.deleteById(id);
    }

    private void validarQueja(Queja queja) {
        if (queja.getDescripcion() == null || queja.getDescripcion().length() < 20) {
            throw new IllegalArgumentException("La descripción debe tener al menos 20 caracteres");
        }

        if (queja.getCliente() == null) {
            throw new IllegalArgumentException("Debe especificar un cliente");
        }

        if (queja.getTipo() == null) {
            throw new IllegalArgumentException("Debe especificar un tipo de queja");
        }

        if (queja.getPrioridad() == null) {
            throw new IllegalArgumentException("Debe especificar una prioridad");
        }
    }
} 