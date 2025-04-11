package com.examen3java.desarrolloweb.service;

import com.examen3java.desarrolloweb.Entity.Viaje;
import java.time.LocalDateTime;
import java.util.List;

public interface ViajeService {
    List<Viaje> obtenerTodosViajes();
    Viaje obtenerViajePorId(Long id);
    Viaje crearViaje(Viaje viaje);
    Viaje actualizarViaje(Long id, Viaje viaje);
    void eliminarViaje(Long id);
    List<Viaje> buscarViajesPorRuta(Long rutaId);
    List<Viaje> buscarViajesPorFecha(LocalDateTime fecha);
    List<Viaje> buscarViajesPorEstado(Viaje.EstadoViaje estado);
    void cancelarViaje(Long id);
    void iniciarViaje(Long id);
    void completarViaje(Long id);
    boolean verificarDisponibilidad(Long id);
    void reservarAsiento(Long viajeId);
    void liberarAsiento(Long viajeId);
}