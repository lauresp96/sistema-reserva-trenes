package com.trains.trains.controladores.Admin;

import com.trains.trains.entidades.Factura;
import com.trains.trains.entidades.Reserva;
import com.trains.trains.entidades.Usuario;
import com.trains.trains.entidades.Viaje;
import com.trains.trains.servicios.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/reservas")
public class Admin_ReservaController {

    private final ReservaService reservaService;
    private final ViajeService viajeService;
    private final UsuarioService usuarioService;

    public Admin_ReservaController(ReservaService reservaService, ViajeService viajeService, UsuarioService usuarioService) {
        this.reservaService = reservaService;
        this.viajeService = viajeService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String mostrarReservas(Model model) {
        List<Reserva> reservas = reservaService.mostrarTodos();
        List<Usuario> usuarios = usuarioService.mostrarTodos();
        List<Viaje> viajes = viajeService.mostrarTodos();

        model.addAttribute("reservas", reservas);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("viajes", viajes);

        return "admin/reservas";
    }

    @GetMapping("/crear")
    public String formularioCreacionReserva(Model model) {
        List<Viaje> viajes = viajeService.mostrarTodos();
        List<Usuario> usuarios = usuarioService.mostrarTodos();

        model.addAttribute("reserva", new Reserva());
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("viajes", viajes);

        return "admin/reserva-form";
    }

    @PostMapping("/guardar")
    public String guardarReserva(@ModelAttribute Reserva reserva) throws Exception {
        reserva.getUsuario();
        reserva.getViajes();

        reservaService.guardar(reserva);

        return "redirect:/admin/reservas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarReserva(@PathVariable Long id, Model model) {
        Reserva reserva = reservaService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        model.addAttribute("reserva", reserva);
        model.addAttribute("usuarios", usuarioService.mostrarTodos());
        model.addAttribute("viajes", viajeService.mostrarTodos());

        return "admin/reserva-form";
    }


    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Reserva reserva) throws Exception {
        Reserva reservaEditada = reservaService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reservaEditada.setUsuario(reserva.getUsuario());
        reservaEditada.setViajes(reserva.getViajes());

        reservaService.editar(id, reserva);

        return "redirect:/admin/reservas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarReserva(@PathVariable Long id) {
        reservaService.eliminar(id);
        return "redirect:/admin/reservas";
    }

}
