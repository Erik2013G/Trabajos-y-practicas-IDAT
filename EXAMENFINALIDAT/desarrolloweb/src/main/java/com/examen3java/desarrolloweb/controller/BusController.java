package com.examen3java.desarrolloweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examen3java.desarrolloweb.Entity.Bus;
import com.examen3java.desarrolloweb.service.BusService;
import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public ResponseEntity<List<Bus>> listarBuses() {
        return ResponseEntity.ok(busService.obtenerTodosBuses());
    }

    @PostMapping
    public ResponseEntity<Bus> crearBus(@RequestBody Bus bus) {
        return ResponseEntity.ok(busService.crearBus(bus));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> obtenerBus(@PathVariable Long id) {
        return ResponseEntity.ok(busService.obtenerBusPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bus> actualizarBus(@PathVariable Long id, @RequestBody Bus bus) {
        return ResponseEntity.ok(busService.actualizarBus(id, bus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBus(@PathVariable Long id) {
        busService.eliminarBus(id);
        return ResponseEntity.noContent().build();
    }
}
