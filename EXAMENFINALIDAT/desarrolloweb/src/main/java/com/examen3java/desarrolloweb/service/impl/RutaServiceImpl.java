package com.examen3java.desarrolloweb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen3java.desarrolloweb.Entity.Ruta;
import com.examen3java.desarrolloweb.repository.RutaRepository;
import com.examen3java.desarrolloweb.service.IRutaService;

@Service
@Transactional
public class RutaServiceImpl implements IRutaService {

    @Autowired
    private RutaRepository rutaRepository;

    @Override
    public List<Ruta> listarTodas() {
        return rutaRepository.findAll();
    }

    @Override
    public Optional<Ruta> obtenerPorId(Long id) {
        return rutaRepository.findById(id);
    }

    @Override
    public List<Ruta> listarPorEstado(String estado) {
        return rutaRepository.findByEstado(estado);
    }

    @Override
    public Ruta guardar(Ruta ruta) {
        validarRuta(ruta);
        return rutaRepository.save(ruta);
    }

    @Override
    public void eliminar(Long id) {
        rutaRepository.deleteById(id);
    }

    private void validarRuta(Ruta ruta) {
        if (ruta.getOrigen().equalsIgnoreCase(ruta.getDestino())) {
            throw new IllegalArgumentException("El origen y destino no pueden ser iguales");
        }
        
        if (ruta.getId() == null && rutaRepository.existsByOrigenAndDestino(ruta.getOrigen(), ruta.getDestino())) {
            throw new IllegalArgumentException("Ya existe una ruta con el mismo origen y destino");
        }
    }
} 