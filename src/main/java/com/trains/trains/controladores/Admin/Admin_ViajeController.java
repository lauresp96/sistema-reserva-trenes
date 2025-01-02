package com.trains.trains.controladores.Admin;

import com.trains.trains.entidades.Estacion;
import com.trains.trains.entidades.Viaje;
import com.trains.trains.servicios.EstacionService;
import com.trains.trains.servicios.RutaService;
import com.trains.trains.servicios.TrenService;
import com.trains.trains.servicios.ViajeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/viajes")
public class Admin_ViajeController {

    private final ViajeService viajeService;
    private final EstacionService estacionService;
    private final TrenService trenService;
    private final RutaService rutaService;

    public Admin_ViajeController(ViajeService viajeService, EstacionService estacionService, TrenService trenService, RutaService rutaService) {
        this.viajeService = viajeService;
        this.estacionService = estacionService;
        this.trenService = trenService;
        this.rutaService = rutaService;
    }

    @GetMapping
    public String mostrarViajes(Model model) {
        List<Viaje> viajes = viajeService.mostrarTodos();
        model.addAttribute("viajes", viajes);
        return "admin/viajes";
    }

    @GetMapping("/crear")
    public String formularioCreacionViaje(Model model) {
        model.addAttribute("viaje", new Viaje());
        model.addAttribute("estaciones", estacionService.mostrarTodos());
        model.addAttribute("trenes", trenService.mostrarTodos());
        model.addAttribute("rutas", rutaService.mostrarTodos());
        return "admin/viaje-form";
    }

    @PostMapping("/guardar")
    public String guardarViaje(@ModelAttribute Viaje viaje) throws Exception {
        Estacion origen = estacionService.encontrarPorId(viaje.getOrigen().getId()).orElseThrow(() -> new RuntimeException("Origen no encontrado"));
        Estacion destino = estacionService.encontrarPorId(viaje.getDestino().getId()).orElseThrow(() -> new RuntimeException("Destino no encontrado"));

        viaje.setOrigen(origen);
        viaje.setDestino(destino);
        if (viaje.getEstaciones() == null) {
            viaje.setEstaciones(new ArrayList<>());
        }
        viaje.getEstaciones().add(origen);
        viaje.getEstaciones().add(destino);

        viajeService.guardar(viaje);
        return "redirect:/admin/viajes";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarViaje(@PathVariable Long id, Model model) {
        Viaje viaje = viajeService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
        model.addAttribute("viaje", viaje);
        model.addAttribute("estaciones", estacionService.mostrarTodos());
        model.addAttribute("trenes", trenService.mostrarTodos());
        model.addAttribute("rutas", rutaService.mostrarTodos());
        return "admin/viaje-form";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Viaje viaje) throws Exception {
        Viaje viajeExistente = viajeService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        viaje.setDestino(viaje.getDestino());
        viaje.setEstaciones(viaje.getEstaciones());
        viaje.setOrigen(viaje.getOrigen());
        viaje.setDestino(viaje.getDestino());

        viajeService.editar(id, viaje);
        return "redirect:/admin/viajes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarViaje(@PathVariable Long id) {
        viajeService.eliminar(id);
        return "redirect:/admin/viajes";
    }
}
