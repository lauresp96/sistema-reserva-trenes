package com.trains.trains.controladores.Admin;


import com.trains.trains.entidades.Clase;
import com.trains.trains.servicios.ClaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/clases")
public class Admin_ClaseController {

    private final ClaseService claseService;

    public Admin_ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @GetMapping
    public String mostrarClases(Model model) {
        List<Clase> clases = claseService.mostrarTodos();
        model.addAttribute("clases", clases);
        return "admin/clases";
    }

    @GetMapping("/crear")
    public String formularioCreacionClase(Model model) {
        model.addAttribute("clase", new Clase());
        return "admin/clase-form";
    }

    @PostMapping("/guardar")
    public String guardarClase(@ModelAttribute Clase clase) throws Exception {
        claseService.guardar(clase);
        return "redirect:/admin/clases";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarClase(@PathVariable Long id, Model model) {
        Clase clase = claseService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        model.addAttribute("clase", clase);
        return "admin/clase-form";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Clase clase) throws Exception {
        clase.setId(id);
        claseService.editar(id, clase);
        return "redirect:/admin/clases";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarClase(@PathVariable Long id) {
        claseService.eliminar(id);
        return "redirect:/admin/clases";
    }
}
