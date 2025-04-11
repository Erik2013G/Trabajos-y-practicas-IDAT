package com.examen3java.desarrolloweb.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.examen3java.desarrolloweb.Entity.Pasaje;

public interface IPasajeService {
    List<Pasaje> listarTodos();
    Optional<Pasaje> obtenerPorId(Long id);
    List<Pasaje> listarPorViaje(Long viajeId);
    List<Pasaje> listarPorCliente(Long clienteId);
    List<Pasaje> listarPorEstado(String estado);
    List<Pasaje> listarPorRangoDeFechas(LocalDateTime inicio, LocalDateTime fin);
    List<Pasaje> buscarPorNumeroAsiento(Integer numeroAsiento);
    Pasaje guardar(Pasaje pasaje);
    void eliminar(Long id);
    boolean verificarDisponibilidadAsiento(Long viajeId, Integer numeroAsiento);
} 