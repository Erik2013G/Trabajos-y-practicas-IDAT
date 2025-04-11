package com.examen3java.desarrolloweb.service.impl;

import com.examen3java.desarrolloweb.model.Cliente;
import com.examen3java.desarrolloweb.repository.ClienteRepository;
import com.examen3java.desarrolloweb.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<Cliente> obtenerPorDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    @Override
    public Optional<Cliente> obtenerPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    @Override
    public List<Cliente> buscarPorApellidos(String apellidos) {
        return clienteRepository.findByApellidosContainingIgnoreCase(apellidos);
    }

    @Override
    public List<Cliente> buscarPorNombres(String nombres) {
        return clienteRepository.findByNombresContainingIgnoreCase(nombres);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getDni() == null || cliente.getDni().trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio");
        }

        if (cliente.getId() == null && clienteRepository.existsByDni(cliente.getDni())) {
            throw new IllegalArgumentException("Ya existe un cliente con el mismo DNI");
        }

        if (cliente.getNombres() == null || cliente.getNombres().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        if (cliente.getApellidos() == null || cliente.getApellidos().trim().isEmpty()) {
            throw new IllegalArgumentException("Los apellidos son obligatorios");
        }

        if (cliente.getEmail() != null && !cliente.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("El formato del email no es válido");
        }

        if (cliente.getTelefono() != null && !cliente.getTelefono().matches("^\\d{9}$")) {
            throw new IllegalArgumentException("El teléfono debe tener 9 dígitos");
        }
    }
} 