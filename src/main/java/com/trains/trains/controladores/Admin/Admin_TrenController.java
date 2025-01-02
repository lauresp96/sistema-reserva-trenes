package com.trains.trains.controladores.Admin;


import com.trains.trains.entidades.Tren;
import com.trains.trains.servicios.TrenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/trenes")

public class Admin_TrenController {

    private final TrenService trenService;

    public Admin_TrenController(TrenService trenService) {
        this.trenService = trenService;
    }

    @GetMapping
    public String mostrarTrenes(Model model) {
        List<Tren> trenes = trenService.mostrarTodos();
        model.addAttribute("usuarios", trenes);
        return "admin/trenes";
    }

    @GetMapping("/crear")
    public String formularioCreacionTren(Model model) {
        model.addAttribute("usuario", new Tren());
        return "admin/tren-form";
    }

    @PostMapping("/guardar")
    public String guardarTren(@ModelAttribute Tren tren) throws Exception {
        Tren trenCreado = trenService.guardar(tren);
        return "redirect:/admin/trenes";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarTren(@PathVariable Long id, Model model) throws Exception {
        Tren tren = trenService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Tren no encontrado"));
        if (tren != null) {
            model.addAttribute("usuario", tren);
        }
        return "admin/tren-form";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Tren tren) throws Exception {
        tren.setId(id);
        trenService.editar(id, tren);
        return "redirect:/admin/trenes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTren(@PathVariable Long id) {
        trenService.eliminar(id);
        return "redirect:/admin/trenes";
    }
}
