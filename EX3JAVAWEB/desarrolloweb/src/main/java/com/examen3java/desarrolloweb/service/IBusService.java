package com.examen3java.desarrolloweb.service;

import com.examen3java.desarrolloweb.model.Bus;
import java.util.List;
import java.util.Optional;

public interface IBusService {
    List<Bus> listarTodos();
    Optional<Bus> obtenerPorId(Long id);
    List<Bus> listarPorEstado(String estado);
    List<Bus> listarPorTipo(String tipo);
    Bus guardar(Bus bus);
    void eliminar(Long id);
} 