package com.trains.trains.controladores.Admin;

import com.trains.trains.entidades.Pago;
import com.trains.trains.servicios.PagoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/pagos")
public class Admin_PagoController {

    private final PagoService pagoService;

    public Admin_PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }
    @GetMapping
    public String mostrarPagos(Model model) {
        List<Pago> pagos = pagoService.mostrarTodos();
        model.addAttribute("pagos", pagos);
        return "admin/pagos";
    }

    @GetMapping("/crear")
    public String formularioCreacionPago(Model model) {
        model.addAttribute("pago", new Pago());
        return "admin/pago-form";
    }

    @PostMapping("/guardar")
    public String guardarPago(@ModelAttribute Pago pago) throws Exception {
        pagoService.guardar(pago);
        return "redirect:/admin/pagos";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarPago(@PathVariable Long id, Model model) {
        Pago pago = pagoService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        model.addAttribute("pago", pago);
        return "admin/pago-form";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Pago pago) throws Exception {
        pago.setId(id);
        pagoService.editar(id, pago);
        return "redirect:/admin/pagos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPago(@PathVariable Long id) {
        pagoService.eliminar(id);
        return "redirect:/admin/pagos";
    }

}
