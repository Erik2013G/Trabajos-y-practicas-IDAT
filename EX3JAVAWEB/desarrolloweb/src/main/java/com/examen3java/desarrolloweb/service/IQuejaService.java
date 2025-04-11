package com.examen3java.desarrolloweb.service;

import com.examen3java.desarrolloweb.model.Queja;
import java.util.List;
import java.util.Optional;

public interface IQuejaService {
    List<Queja> listarTodas();
    Optional<Queja> obtenerPorId(Long id);
    List<Queja> listarPorEstado(String estado);
    List<Queja> listarPorTipo(String tipo);
    List<Queja> listarPorPrioridad(String prioridad);
    List<Queja> listarPorCliente(Long clienteId);
    List<Queja> listarPorViaje(Long viajeId);
    Queja guardar(Queja queja);
    void eliminar(Long id);
} 