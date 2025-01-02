package com.trains.trains.controladores.Admin;


import com.trains.trains.entidades.Estacion;
import com.trains.trains.servicios.EstacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/estaciones")
public class Admin_EstacionController {

    private final EstacionService estacionService;

    public Admin_EstacionController(EstacionService estacionService) {
        this.estacionService = estacionService;
    }

    @GetMapping
    public String mostrarEstaciones(Model model) {
        List<Estacion> estaciones = estacionService.mostrarTodos();
        model.addAttribute("estaciones", estaciones);
        return "admin/estaciones";
    }

    @GetMapping("/crear")
    public String formularioCreacionEstacion(Model model) {
        model.addAttribute("estacion", new Estacion());
        return "admin/estacion-form";
    }

    @PostMapping("/guardar")
    public String guardarEstacion(@ModelAttribute Estacion estacion) throws Exception {
        estacionService.guardar(estacion);
        return "redirect:/admin/estaciones";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarEstacion(@PathVariable Long id, Model model) {
        Estacion estacion = estacionService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Estacion no encontrada"));
        model.addAttribute("estacion", estacion);
        return "admin/estacion-form";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Estacion estacion) throws Exception {
        estacion.setId(id);
        estacionService.editar(id, estacion);
        return "redirect:/admin/estaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstacion(@PathVariable Long id) {
        estacionService.eliminar(id);
        return "redirect:/admin/estaciones";
    }
}
