package com.examen3java.desarrolloweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examen3java.desarrolloweb.Entity.Ruta;
import com.examen3java.desarrolloweb.service.RutaService;
import java.util.List;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {

    private final RutaService rutaService;

    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    @GetMapping
    public ResponseEntity<List<Ruta>> listarRutas() {
        return ResponseEntity.ok(rutaService.obtenerTodasRutas());
    }

    @PostMapping
    public ResponseEntity<Ruta> crearRuta(@RequestBody Ruta ruta) {
        return ResponseEntity.ok(rutaService.crearRuta(ruta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ruta> obtenerRuta(@PathVariable Long id) {
        return ResponseEntity.ok(rutaService.obtenerRutaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ruta> actualizarRuta(@PathVariable Long id, @RequestBody Ruta ruta) {
        return ResponseEntity.ok(rutaService.actualizarRuta(id, ruta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRuta(@PathVariable Long id) {
        rutaService.eliminarRuta(id);
        return ResponseEntity.noContent().build();
    }
}
