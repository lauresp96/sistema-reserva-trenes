package com.trains.trains.apis;

import com.trains.trains.entidades.Horario;
import com.trains.trains.servicios.HorarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/horarios")
public class API_Horario {

    private final HorarioService horarioService;

    public API_Horario(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping
    public ResponseEntity<List<Horario>> listarHorarios() {
        List<Horario> horarios = horarioService.mostrarTodos();
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> encontrarHorarioPorId(@PathVariable Long id) {
        Optional<Horario> horario = horarioService.encontrarPorId(id);
        return horario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Horario> crearHorario(@RequestBody Horario horario) throws Exception {
        Horario horarioCreado = horarioService.guardar(horario);
        return ResponseEntity.status(HttpStatus.CREATED).body(horarioCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> editarHorario(@PathVariable Long id, @RequestBody Horario horario) throws Exception {
        horario.setId(id);
        Horario horarioEditado = horarioService.editar(id, horario);
        return ResponseEntity.ok(horarioEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaHorario(@PathVariable Long id) {
        horarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
