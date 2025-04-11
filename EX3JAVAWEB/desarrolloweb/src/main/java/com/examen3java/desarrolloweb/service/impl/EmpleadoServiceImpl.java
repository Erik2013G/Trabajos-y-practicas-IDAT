package com.examen3java.desarrolloweb.service.impl;

import com.examen3java.desarrolloweb.model.Empleado;
import com.examen3java.desarrolloweb.repository.EmpleadoRepository;
import com.examen3java.desarrolloweb.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Optional<Empleado> obtenerPorDni(String dni) {
        return empleadoRepository.findByDni(dni);
    }

    @Override
    public Optional<Empleado> obtenerPorEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }

    @Override
    public List<Empleado> listarPorCargo(String cargo) {
        return empleadoRepository.findByCargo(cargo);
    }

    @Override
    public List<Empleado> listarPorEstado(String estado) {
        return empleadoRepository.findByEstado(estado);
    }

    @Override
    public List<Empleado> buscarPorApellidos(String apellidos) {
        return empleadoRepository.findByApellidosContainingIgnoreCase(apellidos);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        validarEmpleado(empleado);
        return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }

    private void validarEmpleado(Empleado empleado) {
        if (empleado.getDni() == null || empleado.getDni().trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio");
        }

        if (empleado.getId() == null && empleadoRepository.existsByDni(empleado.getDni())) {
            throw new IllegalArgumentException("Ya existe un empleado con el mismo DNI");
        }

        if (empleado.getNombres() == null || empleado.getNombres().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        if (empleado.getApellidos() == null || empleado.getApellidos().trim().isEmpty()) {
            throw new IllegalArgumentException("Los apellidos son obligatorios");
        }

        if (empleado.getCargo() == null || empleado.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("El cargo es obligatorio");
        }

        if (empleado.getEmail() != null && !empleado.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("El formato del email no es válido");
        }

        if (empleado.getTelefono() != null && !empleado.getTelefono().matches("^\\d{9}$")) {
            throw new IllegalArgumentException("El teléfono debe tener 9 dígitos");
        }
    }
} 