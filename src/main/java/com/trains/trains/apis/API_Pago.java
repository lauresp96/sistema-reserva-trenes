package com.trains.trains.apis;

import com.trains.trains.entidades.Factura;
import com.trains.trains.entidades.Pago;
import com.trains.trains.servicios.FacturaService;
import com.trains.trains.servicios.PagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class API_Pago {

    private final PagoService pagoService;

    public API_Pago(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<Pago>> mostrarTodosPagos() {
        List<Pago> pagos = pagoService.mostrarTodos();
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> pagosPorId(@PathVariable Long id) {
        Optional<Pago> pago = pagoService.encontrarPorId(id);
        return pago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pago> crearPago(@RequestBody Pago pago) throws Exception {
        Pago pagoCreado = pagoService.guardar(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> editarPago(@PathVariable Long id, @RequestBody Pago pago) throws Exception {
        pago.setId(id);
        Pago pagoEditado = pagoService.editar(id, pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
