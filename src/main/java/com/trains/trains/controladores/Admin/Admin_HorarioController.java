package com.trains.trains.controladores.Admin;

import com.trains.trains.entidades.Horario;
import com.trains.trains.servicios.HorarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/horarios")
public class Admin_HorarioController {

    private final HorarioService horarioService;

    public Admin_HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping
    public String mostrarHorarios(Model model) {
        List<Horario> horarios = horarioService.mostrarTodos();
        model.addAttribute("horarios", horarios);
        return "admin/horarios";
    }

    @GetMapping("/crear")
    public String formularioCreacionHorario(Model model) {
        Horario nuevoHorario = new Horario();
        nuevoHorario.setDiasOperativos(new ArrayList<>());
        model.addAttribute("horario", nuevoHorario);
        return "admin/horario-form";
    }

    @PostMapping("/guardar")
    public String guardarHorario(@ModelAttribute Horario horario) throws Exception {
        horarioService.guardar(horario);
        return "redirect:/admin/horarios";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarHorario(@PathVariable Long id, Model model) {
        Horario horario = horarioService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
        model.addAttribute("horario", horario);
        return "admin/horario-form";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Horario horario) throws Exception {
        horario.setId(id);
        horarioService.editar(id, horario);
        return "redirect:/admin/horarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarHorario(@PathVariable Long id) {
        horarioService.eliminar(id);
        return "redirect:/admin/horarios";
    }

}
