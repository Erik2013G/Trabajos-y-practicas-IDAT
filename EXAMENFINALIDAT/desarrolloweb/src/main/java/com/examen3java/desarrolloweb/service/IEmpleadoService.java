package com.examen3java.desarrolloweb.service;

import java.util.List;
import java.util.Optional;

import com.examen3java.desarrolloweb.Entity.Empleado;

public interface IEmpleadoService {
    List<Empleado> listarTodos();
    Optional<Empleado> obtenerPorId(Long id);
    Optional<Empleado> obtenerPorDni(String dni);
    Optional<Empleado> obtenerPorEmail(String email);
    List<Empleado> listarPorCargo(String cargo);
    List<Empleado> listarPorEstado(String estado);
    List<Empleado> buscarPorApellidos(String apellidos);
    Empleado guardar(Empleado empleado);
    void eliminar(Long id);
} 