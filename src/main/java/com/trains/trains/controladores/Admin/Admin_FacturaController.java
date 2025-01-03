package com.trains.trains.controladores.Admin;

import com.trains.trains.entidades.Factura;
import com.trains.trains.entidades.Reserva;
import com.trains.trains.servicios.FacturaService;
import com.trains.trains.servicios.ReservaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/facturas")
public class Admin_FacturaController {

    private final FacturaService facturaService;
    private final ReservaService reservaService;

    public Admin_FacturaController(FacturaService facturaService, ReservaService reservaService) {
        this.facturaService = facturaService;
        this.reservaService = reservaService;
    }

    @GetMapping
    public String mostrarFacturas(Model model) {
        List<Factura> facturas = facturaService.mostrarTodos();
        model.addAttribute("facturas", facturas);
        return "admin/facturas";
    }

    @GetMapping("/crear")
    public String formularioCreacionFactura(Model model) {
        List<Reserva> reservas = reservaService.mostrarTodos();

        model.addAttribute("factura", new Factura());
        model.addAttribute("reservas", reservas);
        return "admin/factura-form";
    }

    @PostMapping("/guardar")
    public String guardarFactura(@ModelAttribute Factura factura) throws Exception {
        factura.getReserva();
        facturaService.guardar(factura);
        return "redirect:/admin/facturas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarFactura(@PathVariable Long id, Model model) {
        Factura factura = facturaService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        model.addAttribute("factura", factura);
        model.addAttribute("reservas", reservaService.mostrarTodos());
        return "admin/factura-form";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Factura factura) throws Exception {
        Factura facturaEditada = facturaService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        facturaEditada.setReserva(factura.getReserva());

        facturaService.editar(id, factura);
        return "redirect:/admin/facturas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        facturaService.eliminar(id);
        return "redirect:/admin/facturas";
    }

}
