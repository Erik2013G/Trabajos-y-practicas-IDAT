package com.examen3java.desarrolloweb.service.impl;

import com.examen3java.desarrolloweb.Entity.Bus;
import com.examen3java.desarrolloweb.exception.DuplicateResourceException;
import com.examen3java.desarrolloweb.exception.IllegalOperationException;
import com.examen3java.desarrolloweb.exception.ResourceNotFoundException;
import com.examen3java.desarrolloweb.repository.BusRepository;
import com.examen3java.desarrolloweb.service.IBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

@Service
@Transactional
public class BusServiceImpl implements IBusService {

    private final BusRepository busRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bus> listarTodos() {
        return busRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Bus obtenerPorId(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bus> listarPorEstado(String estado) {
        if (!Bus.EstadoBus.isValid(estado)) {
            throw new IllegalArgumentException("Estado de bus no válido: " + estado);
        }
        return busRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bus> listarPorTipo(String tipo) {
        if (!Bus.TipoBus.isValid(tipo)) {
            throw new IllegalArgumentException("Tipo de bus no válido: " + tipo);
        }
        return busRepository.findByTipo(tipo);
    }

    @Override
    public Bus guardar(Bus bus) {
        validarBus(bus);
        
        if (bus.getId() == null && busRepository.existsByPlaca(bus.getPlaca())) {
            throw new DuplicateResourceException("Ya existe un bus con la placa: " + bus.getPlaca());
        }
        
        return busRepository.save(bus);
    }

    @Override
    public Bus actualizar(Long id, Bus busActualizado) {
        Bus busExistente = obtenerPorId(id);
        
        // Validar que la placa no esté siendo usada por otro bus
        if (!busExistente.getPlaca().equals(busActualizado.getPlaca()) && 
            busRepository.existsByPlaca(busActualizado.getPlaca())) {
            throw new DuplicateResourceException("La placa " + busActualizado.getPlaca() + " ya está en uso");
        }
        
        // Actualizar campos permitidos
        busExistente.setPlaca(busActualizado.getPlaca());
        busExistente.setModelo(busActualizado.getModelo());
        busExistente.setMarca(busActualizado.getMarca());
        busExistente.setAnioFabricacion(busActualizado.getAnioFabricacion());
        busExistente.setCapacidad(busActualizado.getCapacidad());
        busExistente.setEstado(busActualizado.getEstado());
        busExistente.setTipo(busActualizado.getTipo());
        
        validarBus(busExistente);
        return busRepository.save(busExistente);
    }

    @Override
    public void eliminar(Long id) {
        Bus bus = obtenerPorId(id);
        
        // Verificar si el bus está asignado a algún viaje futuro
        if (busRepository.existsByBusIdAndViajesFuturos(id, LocalDate.now())) {
            throw new IllegalOperationException("No se puede eliminar el bus porque tiene viajes programados");
        }
        
        busRepository.delete(bus);
    }

    private void validarBus(Bus bus) {
        // Validar placa
        if (bus.getPlaca() == null || bus.getPlaca().trim().isEmpty()) {
            throw new IllegalArgumentException("La placa del bus es obligatoria");
        }

        // Validar capacidad
        if (bus.getCapacidad() < Bus.CAPACIDAD_MINIMA || bus.getCapacidad() > Bus.CAPACIDAD_MAXIMA) {
            throw new IllegalArgumentException("La capacidad debe estar entre " + 
                Bus.CAPACIDAD_MINIMA + " y " + Bus.CAPACIDAD_MAXIMA + " pasajeros");
        }

        // Validar año fabricación
        int anioActual = Year.now().getValue();
        if (bus.getAnioFabricacion() < Bus.ANIO_MINIMO_FABRICACION || 
            bus.getAnioFabricacion() > anioActual) {
            throw new IllegalArgumentException("El año de fabricación debe estar entre " + 
                Bus.ANIO_MINIMO_FABRICACION + " y " + anioActual);
        }

        // Validar estado y tipo
        if (!Bus.EstadoBus.isValid(bus.getEstado())) {
            throw new IllegalArgumentException("Estado de bus no válido: " + bus.getEstado());
        }
        
        if (!Bus.TipoBus.isValid(bus.getTipo())) {
            throw new IllegalArgumentException("Tipo de bus no válido: " + bus.getTipo());
        }
    }
}