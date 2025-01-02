package com.trains.trains.apis;

import com.trains.trains.entidades.Reserva;
import com.trains.trains.servicios.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class API_Reserva {

    private final ReservaService reservaService;

    public API_Reserva(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> mostrarTodasReservas() {
        List<Reserva> reservas = reservaService.mostrarTodos();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> reservasPorId(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.encontrarPorId(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) throws Exception {
        Reserva reservaCreada = reservaService.guardar(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> editarReserva(@PathVariable Long id, @RequestBody Reserva reserva) throws Exception {
        reserva.setId(id);
        Reserva reservaEditada = reservaService.editar(id, reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaEditada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
