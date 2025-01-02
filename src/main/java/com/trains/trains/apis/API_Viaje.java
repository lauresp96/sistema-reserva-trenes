package com.trains.trains.apis;

import com.trains.trains.entidades.Pago;
import com.trains.trains.entidades.Viaje;
import com.trains.trains.servicios.ViajeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/viajes")
public class API_Viaje {

    private final ViajeService viajeService;

    public API_Viaje(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @GetMapping
    public ResponseEntity<List<Viaje>> mostrarTodosLosViajes() {
        List<Viaje> viajes = viajeService.mostrarTodos();
        return ResponseEntity.ok(viajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaje> viajesPorId(@PathVariable Long id) {
        Optional<Viaje> viaje = viajeService.encontrarPorId(id);
        return viaje.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Viaje> crearViaje(@RequestBody Viaje viaje) throws Exception {
        Viaje viajeCreado = viajeService.guardar(viaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(viajeCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaje> editarViaje(@PathVariable Long id, @RequestBody Viaje viaje) throws Exception {
        viaje.setId(id);
        Viaje viajeEditado = viajeService.editar(id, viaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(viajeEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarViaje(@PathVariable Long id) {
        viajeService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
