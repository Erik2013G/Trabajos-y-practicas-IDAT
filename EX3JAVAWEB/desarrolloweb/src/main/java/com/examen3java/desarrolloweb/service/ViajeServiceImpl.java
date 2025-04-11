package com.examen3java.desarrolloweb.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen3java.desarrolloweb.Entity.Viaje;
import com.examen3java.desarrolloweb.Entity.Viaje.EstadoViaje;
import com.examen3java.desarrolloweb.repository.ViajeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class ViajeServiceImpl implements ViajeService {

    private final ViajeRepository viajeRepository;

    @Autowired
    public ViajeServiceImpl(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Viaje> findAll() {
        return viajeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Viaje findById(Long id) {
        return viajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con ID: " + id));
    }

    @Override
    public Viaje save(Viaje viaje) {
        validateViaje(viaje);
        return viajeRepository.save(viaje);
    }

    @Override
    public void deleteById(Long id) {
        if (!viajeRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar, viaje no encontrado con ID: " + id);
        }
        viajeRepository.deleteById(id);
    }

    @Override
    public void cancelar(Long id) {
        Viaje viaje = findById(id);
        if ( (viaje.getEstado() != EstadoViaje.PROGRAMADO)) {
            throw new IllegalStateException("Solo se pueden cancelar viajes en estado 'programado'");
        }
        viaje.setEstado(EstadoViaje.CANCELADO);
        viajeRepository.save(viaje);
    }

    @Transactional(readOnly = true)
    public List<Viaje> buscarPorClienteOViajes(String buscar) {
        if (buscar == null || buscar.trim().isEmpty()) {
            throw new IllegalArgumentException("El término de búsqueda no puede estar vacío");
        }
        return viajeRepository.(buscar);
    }

    @Override
    public Viaje update(Viaje viajeActualizado) {
        Viaje viajeExistente = findById(viajeActualizado.getId());
        validateFechasViaje(viajeActualizado);
        updateViajeFields(viajeExistente, viajeActualizado);
        return viajeRepository.save(viajeExistente);
    }

    // Métodos auxiliares privados
    private void validateViaje(Viaje viaje) {
        if (viaje.getFechaHoraSalida() == null) {
            throw new IllegalArgumentException("La fecha/hora de salida es obligatoria");
        }
        if (viaje.getRuta() == null || viaje.getRuta().getId() == null) {
            throw new IllegalArgumentException("La ruta es obligatoria");
        }
        if (viaje.getFechaHoraSalida().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("La fecha de salida no puede ser en el pasado");
        }
    }

    private void validateFechasViaje(Viaje viaje) {
        if (viaje.getFechaHoraSalida() != null && viaje.getFechaHoraLlegada() != null
                && viaje.getFechaHoraSalida().isAfter(viaje.getFechaHoraLlegada())) {
            throw new IllegalArgumentException("La fecha de salida no puede ser después de la fecha de llegada");
        }
    }

    private void updateViajeFields(Viaje existente, Viaje actualizado) {
        if (actualizado.getRuta() != null) {
            existente.setRuta(actualizado.getRuta());
        }
        if (actualizado.getBus() != null) {
            existente.setBus(actualizado.getBus());
        }
        if (actualizado.getFechaHoraSalida() != null) {
            existente.setFechaHoraSalida(actualizado.getFechaHoraSalida());
        }
        if (actualizado.getFechaHoraLlegada() != null) {
            existente.setFechaHoraLlegada(actualizado.getFechaHoraLlegada());
        }
        if (actualizado.getPrecioBase() != null && actualizado.getPrecioBase().compareTo(BigDecimal.ZERO)>0) {
            existente.setPrecioBase(actualizado.getPrecioBase());
        }
    }

    @Override
    public List<Viaje> obtenerTodosViajes() {
        return viajeRepository.findAll();
    }

    @Override
    public Viaje obtenerViajePorId(Long id) {
        return viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public Viaje crearViaje(Viaje viaje) {
        viaje.setEstado(Viaje.EstadoViaje.PROGRAMADO);
        viaje.setAsientosDisponibles(viaje.getBus().getCapacidad());
        return viajeRepository.save(viaje);
    }

    @Override
    @Transactional
    public Viaje actualizarViaje(Long id, Viaje viajeActualizado) {
        Viaje viaje = obtenerViajePorId(id);
        
        viaje.setRuta(viajeActualizado.getRuta());
        viaje.setBus(viajeActualizado.getBus());
        viaje.setFechaHoraSalida(viajeActualizado.getFechaHoraSalida());
        viaje.setFechaHoraLlegada(viajeActualizado.getFechaHoraLlegada());
        viaje.setPrecioBase(viajeActualizado.getPrecioBase());
        
        return viajeRepository.save(viaje);
    }

    @Override
    @Transactional
    public void eliminarViaje(Long id) {
        Viaje viaje = obtenerViajePorId(id);
        if (viaje.getEstado() == Viaje.EstadoViaje.EN_CURSO) {
            throw new IllegalStateException("No se puede eliminar un viaje en curso");
        }
        viajeRepository.deleteById(id);
    }

    @Override
    public List<Viaje> buscarViajesPorRuta(Long rutaId) {
        return viajeRepository.findByRutaId(rutaId);
    }

    @Override
    public List<Viaje> buscarViajesPorFecha(LocalDateTime fecha) {
        return viajeRepository.findByFechaHoraSalidaBetween(
            fecha.toLocalDate().atStartOfDay(),
            fecha.toLocalDate().plusDays(1).atStartOfDay()
        );
    }

    @Override
    public List<Viaje> buscarViajesPorEstado(Viaje.EstadoViaje estado) {
        return viajeRepository.findByEstado(estado);
    }

    @Override
    @Transactional
    public void cancelarViaje(Long id) {
        Viaje viaje = obtenerViajePorId(id);
        viaje.cancelar();
        viajeRepository.save(viaje);
    }

    @Override
    @Transactional
    public void iniciarViaje(Long id) {
        Viaje viaje = obtenerViajePorId(id);
        viaje.iniciar();
        viajeRepository.save(viaje);
    }

    @Override
    @Transactional
    public void completarViaje(Long id) {
        Viaje viaje = obtenerViajePorId(id);
        viaje.completar();
        viajeRepository.save(viaje);
    }

    @Override
    public boolean verificarDisponibilidad(Long id) {
        Viaje viaje = obtenerViajePorId(id);
        return viaje.tieneAsientosDisponibles();
    }

    @Override
    @Transactional
    public void reservarAsiento(Long viajeId) {
        Viaje viaje = obtenerViajePorId(viajeId);
        viaje.reservarAsiento();
        viajeRepository.save(viaje);
    }

    @Override
    @Transactional
    public void liberarAsiento(Long viajeId) {
        Viaje viaje = obtenerViajePorId(viajeId);
        viaje.liberarAsiento();
        viajeRepository.save(viaje);
    }
}