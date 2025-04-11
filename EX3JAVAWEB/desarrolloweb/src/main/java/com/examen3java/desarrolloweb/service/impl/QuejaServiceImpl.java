package com.examen3java.desarrolloweb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen3java.desarrolloweb.Entity.Queja;
import com.examen3java.desarrolloweb.Entity.Queja.EstadoQueja;
import com.examen3java.desarrolloweb.repository.QuejaRepository;
import com.examen3java.desarrolloweb.service.IQuejaService;

@Service
@Transactional
public class QuejaServiceImpl implements IQuejaService {

    @Autowired
    private QuejaRepository quejaRepository;

    @Override
    public List<Queja> listarTodas() {
        return quejaRepository.findAll();
    }

    @Override
    public Optional<Queja> obtenerPorId(Long id) {
        return quejaRepository.findById(id);
    }

    @Override
    public List<Queja> listarPorEstado(String estado) {
        return quejaRepository.findByEstado(estado);
    }

    @Override
    public List<Queja> listarPorTipo(String tipo) {
        return quejaRepository.findByTipo(tipo);
    }

    @Override
    public List<Queja> listarPorPrioridad(String prioridad) {
        return quejaRepository.findByPrioridad(prioridad);
    }

    @Override
    public List<Queja> listarPorCliente(Long clienteId) {
        return quejaRepository.findByClienteId(clienteId);
    }

    @Override
    public List<Queja> listarPorViaje(Long viajeId) {
        return quejaRepository.findByViajeId(viajeId);
    }

    @Override
    public Queja guardar(Queja queja) {
        validarQueja(queja);
        if (queja.getId() == null) {
            queja.setEstado(EstadoQueja.PENDIENTE); // Usa el valor del enum
        }
        return quejaRepository.save(queja);
    }

    @Override
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
