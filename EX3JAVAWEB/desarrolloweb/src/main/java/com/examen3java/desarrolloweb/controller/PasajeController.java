package com.examen3java.desarrolloweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examen3java.desarrolloweb.Entity.Pasaje;
import com.examen3java.desarrolloweb.service.IPasajeService;
import java.util.List;

@RestController
@RequestMapping("/api/pasajes")
public class PasajeController {

    private final IPasajeService pasajeService;

    public PasajeController(IPasajeService pasajeService) {
        this.pasajeService = pasajeService;
    }

    @GetMapping
    public ResponseEntity<List<Pasaje>> listarPasajes() {
        return ResponseEntity.ok(pasajeService.obtenerTodosPasajes());
    }

    @PostMapping
    public ResponseEntity<Pasaje> crearPasaje(@RequestBody Pasaje pasaje) {
        return ResponseEntity.ok(pasajeService.crearPasaje(pasaje));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pasaje> obtenerPasaje(@PathVariable Long id) {
        return ResponseEntity.ok(pasajeService.obtenerPasajePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pasaje> actualizarPasaje(@PathVariable Long id, @RequestBody Pasaje pasaje) {
        return ResponseEntity.ok(pasajeService.actualizarPasaje(id, pasaje));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPasaje(@PathVariable Long id) {
        pasajeService.eliminarPasaje(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/buscar-por-asiento/{numeroAsiento}")
public ResponseEntity<Pasaje> buscarPorNumeroAsiento(@PathVariable String numeroAsiento) {
    return pasajeService.buscarPasajePorNumeroAsiento(numeroAsiento)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
}
