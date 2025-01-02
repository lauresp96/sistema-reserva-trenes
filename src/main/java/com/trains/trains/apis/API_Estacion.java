package com.trains.trains.apis;

import com.trains.trains.entidades.Estacion;
import com.trains.trains.servicios.EstacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estaciones")
public class API_Estacion {

    private final EstacionService estacionService;

    public API_Estacion(EstacionService estacionService) {
        this.estacionService = estacionService;
    }

    @GetMapping
    public ResponseEntity<List<Estacion>> listarEstaciones() {
        List<Estacion> estaciones = estacionService.mostrarTodos();
        return ResponseEntity.ok(estaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estacion> encontrarEstacionPorId(@PathVariable Long id) {
        Optional<Estacion> estacion = estacionService.encontrarPorId(id);
        return estacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estacion> crearEstacion(@RequestBody Estacion estacion) throws Exception {
        Estacion estacionCreada = estacionService.guardar(estacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(estacionCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estacion> editarEstacion(@PathVariable Long id, @RequestBody Estacion estacion) throws Exception {
        estacion.setId(id);
        Estacion estacionEditada = estacionService.editar(id, estacion);
        return ResponseEntity.ok(estacionEditada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstacion(@PathVariable Long id) {
        estacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
