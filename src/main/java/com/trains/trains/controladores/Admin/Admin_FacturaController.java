package com.trains.trains.controladores.Admin;

import com.trains.trains.entidades.Factura;
import com.trains.trains.servicios.FacturaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/facturas")
public class Admin_FacturaController {

    private final FacturaService facturaService;

    public Admin_FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public String mostrarFacturas(Model model) {
        List<Factura> facturas = facturaService.mostrarTodos();
        model.addAttribute("facturas", facturas);
        return "admin/facturas";
    }

    @GetMapping("/crear")
    public String formularioCreacionFactura(Model model) {
        model.addAttribute("factura", new Factura());
        return "admin/factura-form";
    }

    @PostMapping("/guardar")
    public String guardarFactura(@ModelAttribute Factura factura) throws Exception {
        facturaService.guardar(factura);
        return "redirect:/admin/facturas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarFactura(@PathVariable Long id, Model model) {
        Factura factura = facturaService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        model.addAttribute("factura", factura);
        return "admin/factura-form";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Factura factura) throws Exception {
        factura.setId(id);
        facturaService.editar(id, factura);
        return "redirect:/admin/facturas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        facturaService.eliminar(id);
        return "redirect:/admin/facturas";
    }

}
