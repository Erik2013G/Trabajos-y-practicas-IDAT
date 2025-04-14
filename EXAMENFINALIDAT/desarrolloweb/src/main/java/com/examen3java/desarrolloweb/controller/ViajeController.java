package com.examen3java.desarrolloweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examen3java.desarrolloweb.Entity.Viaje;
import com.examen3java.desarrolloweb.service.ViajeService;
import com.examen3java.desarrolloweb.service.RutaService;
import com.examen3java.desarrolloweb.service.BusService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/viajes")
public class ViajeController {

    private final ViajeService viajeService;
    private final RutaService rutaService;
    private final BusService busService;

    public ViajeController(ViajeService viajeService, RutaService rutaService, BusService busService) {
        this.viajeService = viajeService;
        this.rutaService = rutaService;
        this.busService = busService;
    }

    @GetMapping
    public ResponseEntity<List<Viaje>> listarViajes() {
        return ResponseEntity.ok(viajeService.obtenerTodosViajes());
    }

    @PostMapping
    public ResponseEntity<?> crearViaje(@Valid @RequestBody Viaje viaje) {
        try {
            Viaje viajeCreado = viajeService.crearViaje(viaje);
            return ResponseEntity.ok(viajeCreado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaje> obtenerViaje(@PathVariable Long id) {
        return ResponseEntity.ok(viajeService.obtenerViajePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarViaje(@PathVariable Long id, @Valid @RequestBody Viaje viaje) {
        try {
            Viaje viajeActualizado = viajeService.actualizarViaje(id, viaje);
            return ResponseEntity.ok(viajeActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarViaje(@PathVariable Long id) {
        try {
            viajeService.eliminarViaje(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarViaje(@PathVariable Long id) {
        try {
            viajeService.cancelarViaje(id);
            return ResponseEntity.ok(Map.of("mensaje", "Viaje cancelado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/{id}/iniciar")
    public ResponseEntity<?> iniciarViaje(@PathVariable Long id) {
        try {
            viajeService.iniciarViaje(id);
            return ResponseEntity.ok(Map.of("mensaje", "Viaje iniciado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/{id}/completar")
    public ResponseEntity<?> completarViaje(@PathVariable Long id) {
        try {
            viajeService.completarViaje(id);
            return ResponseEntity.ok(Map.of("mensaje", "Viaje completado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}