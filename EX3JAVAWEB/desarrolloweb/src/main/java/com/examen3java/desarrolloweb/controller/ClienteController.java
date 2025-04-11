package com.examen3java.desarrolloweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examen3java.desarrolloweb.Entity.Cliente;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(null); // TODO: Implementar servicio
    }
    
    @PostMapping
    public ResponseEntity<Cliente> registrarCliente(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(null); // TODO: Implementar servicio
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok(null); // TODO: Implementar servicio
    }
    
    @GetMapping("/{id}/historial")
    public ResponseEntity<?> verHistorial(@PathVariable Long id) {
        return ResponseEntity.ok(null); // TODO: Implementar servicio
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(null); // TODO: Implementar servicio
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        return ResponseEntity.noContent().build(); // TODO: Implementar servicio
    }
}