package com.trains.trains.controladores.Admin;

import com.trains.trains.entidades.Ruta;
import com.trains.trains.servicios.RutaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/rutas")
public class Admin_RutaController {

    private final RutaService rutaService;

    public Admin_RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }
    @GetMapping
    public String mostrarRutas(Model model) {
        List<Ruta> rutas = rutaService.mostrarTodos();
        model.addAttribute("rutas", rutas);
        return "admin/rutas";
    }

    @GetMapping("/crear")
    public String formularioCreacionRuta(Model model) {
        model.addAttribute("ruta", new Ruta());
        return "admin/ruta-form";
    }

    @PostMapping("/guardar")
    public String guardarRuta(@ModelAttribute Ruta ruta) throws Exception {
        rutaService.guardar(ruta);
        return "redirect:/admin/rutas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarRuta(@PathVariable Long id, Model model) {
        Ruta ruta = rutaService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
        model.addAttribute("ruta", ruta);
        return "admin/ruta-form";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Ruta ruta) throws Exception {
        ruta.setId(id);
        rutaService.editar(id, ruta);
        return "redirect:/admin/rutas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarRuta(@PathVariable Long id) {
        rutaService.eliminar(id);
        return "redirect:/admin/rutas";
    }

}
