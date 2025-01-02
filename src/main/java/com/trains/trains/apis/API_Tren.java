package com.trains.trains.apis;

import com.trains.trains.entidades.Tren;
import com.trains.trains.servicios.TrenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trenes")
public class API_Tren {

    private final TrenService trenService;

    public API_Tren(TrenService trenService) {
        this.trenService = trenService;
    }

    @GetMapping
    public ResponseEntity<List<Tren>> mostrarTrenes() {
        List<Tren> trenes = trenService.mostrarTodos();
        return ResponseEntity.ok(trenes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tren> encontrarTrenPorId(@PathVariable Long id) {
        Optional<Tren> tren = trenService.encontrarPorId(id);
        return tren.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tren> crearTren(@RequestBody Tren tren) throws Exception {
        Tren trenCreado = trenService.guardar(tren);
        return ResponseEntity.status(HttpStatus.CREATED).body(trenCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tren> editarTren(@PathVariable Long id, @RequestBody Tren tren) throws Exception {
        tren.setId(id);
        Tren trenEditado = trenService.editar(id, tren);
        return ResponseEntity.ok(trenEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        trenService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
