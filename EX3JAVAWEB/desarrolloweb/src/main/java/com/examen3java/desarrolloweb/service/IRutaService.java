package com.examen3java.desarrolloweb.service;

import java.util.List;
import java.util.Optional;

import com.examen3java.desarrolloweb.Entity.Ruta;

public interface IRutaService {
    List<Ruta> listarTodas();
    Optional<Ruta> obtenerPorId(Long id);
    List<Ruta> listarPorEstado(String estado);
    Ruta guardar(Ruta ruta);
    void eliminar(Long id);
} 