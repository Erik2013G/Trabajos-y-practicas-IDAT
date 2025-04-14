package com.examen3java.desarrolloweb.service;

import java.util.List;
import java.util.Optional;

import com.examen3java.desarrolloweb.Entity.Bus;

public interface IBusService {
    List<Bus> listarTodos();
    Optional<Bus> obtenerPorId(Long id);
    List<Bus> listarPorEstado(String estado);
    List<Bus> listarPorTipo(String tipo);
    Bus guardar(Bus bus);
    void eliminar(Long id);
} 