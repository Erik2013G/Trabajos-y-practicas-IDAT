package com.examen3java.desarrolloweb.service;

import java.util.List;
import java.util.Optional;

import com.examen3java.desarrolloweb.Entity.Cliente;

public interface IClienteService {
    List<Cliente> listarTodos();
    Optional<Cliente> obtenerPorId(Long id);
    Optional<Cliente> obtenerPorDni(String dni);
    Optional<Cliente> obtenerPorEmail(String email);
    List<Cliente> buscarPorApellidos(String apellidos);
    List<Cliente> buscarPorNombres(String nombres);
    Cliente guardar(Cliente cliente);
    void eliminar(Long id);
} 