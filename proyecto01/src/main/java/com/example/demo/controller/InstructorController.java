package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Instructor;
import com.example.demo.service.InstructorService;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorservice;
    
    @GetMapping
    public String listarInstructores(Model model, @RequestParam(value = "buscar", required = false) String buscar) {
        List<Instructor> instructor;
        if (buscar != null && !buscar.isEmpty()) {
            instructor = instructorservice.buscarPorInstructor(buscar);
        } else {
            instructor = instructorservice.findAll();
        }
        model.addAttribute("instructor", instructor);
        model.addAttribute("buscar", buscar);

        // Mostrar mensaje si no hay resultados
        if (instructor.isEmpty()) {
            model.addAttribute("mensaje", "No se encontraron resultados para la búsqueda.");
        }

        return "index";
    }

    // Formulario para crear un nuevo instructor
    @GetMapping("/Crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "Crear"; // Ajustado a la ruta de la plantilla para crear
    }

    // Guardar un nuevo instructor
    @PostMapping("/guardar")
    public String guardarInstructor(@ModelAttribute("nombreprof") Instructor instructor, RedirectAttributes redirectAttributes) {
        try {
            instructorservice.save(instructor);
            redirectAttributes.addFlashAttribute("mensaje", "Instructor creado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el instructor.");
        }
        return "redirect:/instructor";
    }

    // Formulario para editar un instructor existente
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Instructor instructor = instructorservice.findById(id);
        if (instructor == null) {
            redirectAttributes.addFlashAttribute("error", "El instructor no existe.");
            return "redirect:/instructor";
        }
        model.addAttribute("instructor", instructor);
        return "Actualizar"; // Ajustado a la ruta de la plantilla para editar
    }

    // Actualizar un instructor existente
    @PostMapping("/{id}/actualizar")
    public String actualizarInstructor(@PathVariable Long id, @ModelAttribute("instructor") Instructor updatedInstructor, RedirectAttributes redirectAttributes) {
        Instructor instructor = instructorservice.findById(id);
        if (instructor != null) {
            // Actualiza los campos del instructor con los nuevos valores
            instructor.setNombreprof(updatedInstructor.getNombreprof());
            instructor.setCorreo(updatedInstructor.getCorreo());
            instructor.setTelefono(updatedInstructor.getTelefono());
            instructor.setEspecialidad(updatedInstructor.getEspecialidad());
            instructorservice.save(instructor); // Guarda el instructor actualizado

            redirectAttributes.addFlashAttribute("mensaje", "Instructor actualizado exitosamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "El instructor no existe.");
        }
        return "redirect:/instructor";
    }

    // Eliminar un instructor existente
    @PostMapping("/{id}/eliminar")
    public String eliminarInstructor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Instructor instructor = instructorservice.findById(id);
        if (instructor != null) {
            instructorservice.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Instructor eliminado exitosamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "El instructor no existe.");
        }
        return "redirect:/instructor";
    }
}
