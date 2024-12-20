package com.examen3java.desarrolloweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;

import com.examen3java.desarrolloweb.Entity.Pedidos;
import com.examen3java.desarrolloweb.service.PedidosService;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;

    // Página principal con la lista de pedidos
    @GetMapping
    public String listarPedidos(Model model, @RequestParam(value = "buscar", required = false) String buscar) {
        List<Pedidos> pedidos;
        if (buscar != null && !buscar.isEmpty()) {
            pedidos = pedidosService.buscarPorClienteOProducto(buscar);
        } else {
            pedidos = pedidosService.findAll();
        }
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("buscar", buscar);

        // Mostrar mensaje si no hay resultados
        if (pedidos.isEmpty()) {
            model.addAttribute("mensaje", "No se encontraron resultados para la búsqueda.");
        }

        return "index";
    }

    // Formulario para crear un nuevo pedido
    @GetMapping("/Crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("pedido", new Pedidos());
        return "Crear"; // Ajustado a la ruta de la plantilla para crear
    }

    // Guardar un nuevo pedido
    @PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute("pedido") Pedidos pedido, RedirectAttributes redirectAttributes) {
        try {
            pedidosService.save(pedido);
            redirectAttributes.addFlashAttribute("mensaje", "Pedido creado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el pedido.");
        }
        return "redirect:/pedidos";
    }

    // Formulario para editar un pedido existente
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Pedidos pedido = pedidosService.findById(id);
        if (pedido == null) {
            redirectAttributes.addFlashAttribute("error", "El pedido no existe.");
            return "redirect:/pedidos";
        }
        pedido.setfechaBase(pedido.getFechaPedido());
        model.addAttribute("pedido", pedido);
        return "Actualizar"; // Ajustado a la ruta de la plantilla para editar
    }

    // Actualizar un pedido existente
    @PostMapping("/{id}/actualizar")
    public String actualizarPedido(@PathVariable Long id, @ModelAttribute("pedido") Pedidos updatedPedido, RedirectAttributes redirectAttributes) {
        Pedidos pedido = pedidosService.findById(id);
        if (pedido != null) {
            pedido.setCliente(updatedPedido.getCliente());
            pedido.setProducto(updatedPedido.getProducto());
            pedido.setCantidad(updatedPedido.getCantidad());
            pedido.setFechaPedido(updatedPedido.getFechaPedido());
            pedido.setEstado(updatedPedido.getEstado());
            pedido.setPrecio(updatedPedido.getPrecio()); // Si el pedido tiene un campo de precio
            pedidosService.save(pedido);

            redirectAttributes.addFlashAttribute("mensaje", "Pedido actualizado exitosamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "El pedido no existe.");
        }
        return "redirect:/pedidos";
    }

    // Eliminar un pedido existente
    @PostMapping("/{id}/eliminar")
    public String eliminarPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Pedidos pedido = pedidosService.findById(id);
        if (pedido != null) {
            pedidosService.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Pedido eliminado exitosamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "El pedido no existe.");
        }
        return "redirect:/pedidos";
    }

    
}
