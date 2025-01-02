package com.trains.trains.apis;

import com.trains.trains.entidades.Clase;
import com.trains.trains.entidades.Estacion;
import com.trains.trains.servicios.ClaseService;
import com.trains.trains.servicios.EstacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clase")
public class API_Clase {

    private final ClaseService claseService;

    public API_Clase(ClaseService claseService) {
        this.claseService = claseService;
    }

    @GetMapping
    public ResponseEntity<List<Clase>> listarClases() {
        List<Clase> clases = claseService.mostrarTodos();
        return ResponseEntity.ok(clases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clase> encontrarClasePorId(@PathVariable Long id) {
        Optional<Clase> clase = claseService.encontrarPorId(id);
        return clase.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Clase> crearClase(@RequestBody Clase clase) throws Exception {
        Clase claseCreada = claseService.guardar(clase);
        return ResponseEntity.status(HttpStatus.CREATED).body(claseCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clase> editarClase(@PathVariable Long id, @RequestBody Clase clase) throws Exception {
        clase.setId(id);
        Clase claseEditada = claseService.editar(id, clase);
        return ResponseEntity.ok(claseEditada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaClase(@PathVariable Long id) {
        claseService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
