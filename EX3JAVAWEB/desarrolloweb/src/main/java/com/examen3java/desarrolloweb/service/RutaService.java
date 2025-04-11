package com.examen3java.desarrolloweb.service;

import com.examen3java.desarrolloweb.model.Ruta;
import com.examen3java.desarrolloweb.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    public List<Ruta> listarTodas() {
        return rutaRepository.findAll();
    }

    public Optional<Ruta> obtenerPorId(Long id) {
        return rutaRepository.findById(id);
    }

    public List<Ruta> listarPorEstado(String estado) {
        return rutaRepository.findByEstado(estado);
    }

    public Ruta guardar(Ruta ruta) {
        validarRuta(ruta);
        return rutaRepository.save(ruta);
    }

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