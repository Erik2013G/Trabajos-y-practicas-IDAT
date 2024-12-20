package com.ex4javaweb.Controller;

import com.ex4javaweb.Entity.Videojuego;
import com.ex4javaweb.Repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    // Listar Videojuegos
    @GetMapping
    public String listarVideojuegos(Model model) {
        List<Videojuego> videojuegos = videojuegoRepository.findAll();
        model.addAttribute("videojuegos", videojuegos);
        return "ListadodevideoJuegos";
    }

    // Mostrar Detalle de un Videojuego
    @GetMapping("/detalle/{id}")
    public String detalleVideojuego(@PathVariable Integer id, Model model) {
        Videojuego videojuego = videojuegoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID no válido: " + id));
        model.addAttribute("videojuego", videojuego);
        return "DetalleVideojuego";
    }

    // Crear un Nuevo Videojuego
    @GetMapping("/nuevo")
    public String nuevoVideojuego(Model model) {
        model.addAttribute("videojuego", new Videojuego());
        return "FormVideojuego";
    }

    // Editar un Videojuego
    @GetMapping("/editar/{id}")
    public String editarVideojuego(@PathVariable Integer id, Model model) {
        Videojuego videojuego = videojuegoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID no válido: " + id));
        model.addAttribute("videojuego", videojuego);
        return "formulario-videojuego";
    }

    // Guardar Videojuego (Crear o Actualizar)
    @PostMapping("/guardar")
    public String guardarVideojuego(@ModelAttribute Videojuego videojuego) {
        videojuegoRepository.save(videojuego);
        return "redirect:/videojuegos";
    }

    // Eliminar un Videojuego
    @GetMapping("/eliminar/{id}")
    public String eliminarVideojuego(@PathVariable Integer id) {
        videojuegoRepository.deleteById(id);
        return "redirect:/videojuegos";
    }

    // Buscar Videojuegos
    @GetMapping("/buscar")
    public String buscarVideojuegos(@RequestParam("query") String query, Model model) {
        List<Videojuego> videojuegos = videojuegoRepository.findByTituloContainingIgnoreCaseOrGeneroContainingIgnoreCaseOrPlataformaContainingIgnoreCase(query, query, query);
        model.addAttribute("videojuegos", videojuegos);
        return "BuscarVideojuego";
    }
}
