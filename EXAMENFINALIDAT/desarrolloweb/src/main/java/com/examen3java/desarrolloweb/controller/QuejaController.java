package com.examen3java.desarrolloweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examen3java.desarrolloweb.Entity.Queja;
import com.examen3java.desarrolloweb.service.QuejaService;
import java.util.List;

@RestController
@RequestMapping("/api/quejas")
public class QuejaController {

    private final QuejaService quejaService;

    public QuejaController(QuejaService quejaService) {
        this.quejaService = quejaService;
    }

    @GetMapping
    public ResponseEntity<List<Queja>> listarQuejas() {
        return ResponseEntity.ok(quejaService.obtenerTodasQuejas());
    }

    @PostMapping
    public ResponseEntity<Queja> crearQueja(@RequestBody Queja queja) {
        return ResponseEntity.ok(quejaService.crearQueja(queja));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Queja> obtenerQueja(@PathVariable Long id) {
        return ResponseEntity.ok(quejaService.obtenerQuejaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Queja> actualizarQueja(@PathVariable Long id, @RequestBody Queja queja) {
        return ResponseEntity.ok(quejaService.actualizarQueja(id, queja));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarQueja(@PathVariable Long id) {
        quejaService.eliminarQueja(id);
        return ResponseEntity.noContent().build();
    }
}
