package com.trains.trains.apis;

import com.trains.trains.entidades.Factura;
import com.trains.trains.servicios.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
public class API_Factura {

    private final FacturaService facturaService;

    public API_Factura(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<List<Factura>> mostrarTodasFacturas() {
        List<Factura> facturas = facturaService.mostrarTodos();
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> facturasPorId(@PathVariable Long id) {
        Optional<Factura> factura = facturaService.encontrarPorId(id);
        return factura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) throws Exception {
        Factura facturaCreada = facturaService.guardar(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> editarFactura(@PathVariable Long id, @RequestBody Factura factura) throws Exception {
        factura.setId(id);
        Factura facturaEditada = facturaService.editar(id, factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaEditada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        facturaService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
