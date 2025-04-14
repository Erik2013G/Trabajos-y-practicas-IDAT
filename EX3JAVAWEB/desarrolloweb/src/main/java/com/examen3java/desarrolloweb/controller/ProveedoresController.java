package com.examen3java.desarrolloweb.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.examen3java.desarrolloweb.Entity.Proveedor;
import com.examen3java.desarrolloweb.service.ProveedoresService;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedoresController {

    @Autowired
    private ProveedoresService proveedoresService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Obtener todos los proveedores
    @GetMapping
    public ResponseEntity<List<Proveedor>> getAllProveedores() {
        try {
            List<Proveedor> proveedores = proveedoresService.findAll();
            return new ResponseEntity<>(proveedores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ruta para crear un proveedor
    @PostMapping("/crear")
    public ResponseEntity<Proveedor> createProveedor(@RequestBody Proveedor proveedor) {
        try {
            return new ResponseEntity<>(proveedoresService.save(proveedor), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ruta para obtener proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable Long id) {
        try {
            Proveedor proveedor = proveedoresService.findById(id);
            if (proveedor == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(proveedor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar")
    public ResponseEntity<Proveedor>buscarpornombre(@RequestParam String nombre){
        try {
            List<Proveedor> proveedores = proveedoresService.findByNombredistribuidor(nombre);
            if (proveedores.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar proveedor por ID
// Actualizar proveedor por ID
@PutMapping("/{id}")
public ResponseEntity<Proveedor> updateProveedor(
        @PathVariable Long id, 
        @RequestBody Proveedor updateProveedor) {
    
    try {
        Proveedor proveedorExistente = proveedoresService.findById(id);
        
        if (proveedorExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualizar campos permitidos
        proveedorExistente.setNombreDistribuidor(updateProveedor.getNombreDistribuidor());
        proveedorExistente.setContacto(updateProveedor.getContacto());
        proveedorExistente.setEmail(updateProveedor.getEmail());
        proveedorExistente.setTelefono(updateProveedor.getTelefono());
        proveedorExistente.setDireccion(updateProveedor.getDireccion());
        proveedorExistente.setPais(updateProveedor.getPais());
        proveedorExistente.setTipoProveedor(updateProveedor.getTipoProveedor());
        proveedorExistente.setRuc(updateProveedor.getRuc());
        proveedorExistente.setEstado(updateProveedor.getEstado());
        proveedorExistente.setMontoCredito(updateProveedor.getMontoCredito());

        // No actualizamos fechaRegistro (se mantiene la original)
        
        Proveedor proveedorActualizado = proveedoresService.save(proveedorExistente);
        return ResponseEntity.ok(proveedorActualizado);
        
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    // Eliminar un proveedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Long id) {
        try {
            Proveedor proveedores =proveedoresService.findById(id);
            if (proveedores == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            proveedoresService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Manejar parámetros inválidos en la URL
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>("El ID debe ser un número válido.", HttpStatus.BAD_REQUEST);
    }
}
