package com.trains.trains.apis;

import com.trains.trains.DTO.EstacionDTO;
import com.trains.trains.DTO.RutaDTO;
import com.trains.trains.entidades.Estacion;
import com.trains.trains.entidades.Ruta;
import com.trains.trains.servicios.EstacionService;
import com.trains.trains.servicios.RutaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rutas")
public class API_Ruta {

    private final RutaService rutaService;
    private final EstacionService estacionService;

    public API_Ruta(RutaService rutaService, EstacionService estacionService) {
        this.rutaService = rutaService;
        this.estacionService = estacionService;
    }

    @GetMapping
    public ResponseEntity<List<RutaDTO>> listarRutas() {
        List<Ruta> rutas = rutaService.mostrarTodos();

        // Convertir las rutas a DTOs
        List<RutaDTO> rutaDTOs = rutas.stream().map(ruta -> {
            List<EstacionDTO> estacionDTOs = ruta.getEstaciones().stream()
                    .map(estacion -> new EstacionDTO(estacion.getId(), estacion.getNombre(), estacion.getCiudad(),
                            estacion.getProvincia(), estacion.getCodigoPostal()))
                    .collect(Collectors.toList());

            return new RutaDTO(ruta.getId(), ruta.getNombreRuta(), ruta.getDescripcion(), estacionDTOs);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(rutaDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ruta> encontrarRutaPorId(@PathVariable Long id) {
        Optional<Ruta> ruta = rutaService.encontrarPorId(id);
        return ruta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RutaDTO> crearRuta(@RequestBody RutaDTO rutaDTO) throws Exception {
        List<Long> estacionIds = rutaDTO.getEstaciones().stream()
                .map(EstacionDTO::getId) //
                .collect(Collectors.toList());

        // Llamar al servicio pasando los IDs de las estaciones
        List<Estacion> estaciones = estacionService.encontrarEstacionPorIds(estacionIds);

        // Crear la ruta
        Ruta ruta = new Ruta();
        ruta.setNombreRuta(rutaDTO.getNombreRuta());
        ruta.setDescripcion(rutaDTO.getDescripcion());

        // Asignar las estaciones a la ruta
        ruta.setEstaciones(estaciones);

        // Asegurarse de que las estaciones se asignen de vuelta a la ruta
        for (Estacion estacion : estaciones) {
            if (!estacion.getRutas().contains(ruta)) {
                estacion.getRutas().add(ruta);  // Esto asegura la relación bidireccional
            }
        }

        // Guardar la ruta
        Ruta rutaCreada = rutaService.guardar(ruta);

        // Convertir la ruta guardada a RutaDTO
        List<EstacionDTO> estacionDTOs = rutaCreada.getEstaciones().stream()
                .map(estacion -> new EstacionDTO(estacion.getId(), estacion.getNombre(), estacion.getCiudad(),
                        estacion.getProvincia(), estacion.getCodigoPostal()))
                .collect(Collectors.toList());

        RutaDTO rutaResponse = new RutaDTO(rutaCreada.getId(), rutaCreada.getNombreRuta(), rutaCreada.getDescripcion(), estacionDTOs);

        return ResponseEntity.status(HttpStatus.CREATED).body(rutaResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RutaDTO> editarRuta(@PathVariable Long id, @RequestBody RutaDTO rutaDTO) throws Exception {
        // Buscar la ruta existente por su ID
        Optional<Ruta> rutaOpt = rutaService.encontrarPorId(id);
        if (rutaOpt.isEmpty()) {
            return ResponseEntity.notFound().build(); // Si no se encuentra la ruta
        }

        // Obtener la ruta existente
        Ruta rutaExistente = rutaOpt.get();

        // Obtener los IDs de las estaciones desde el DTO
        List<Long> estacionIds = rutaDTO.getEstaciones().stream()
                .map(EstacionDTO::getId)
                .collect(Collectors.toList());

        // Buscar las estaciones por sus IDs
        List<Estacion> estaciones = estacionService.encontrarEstacionPorIds(estacionIds);

        // Actualizar los campos de la ruta
        rutaExistente.setNombreRuta(rutaDTO.getNombreRuta());
        rutaExistente.setDescripcion(rutaDTO.getDescripcion());
        rutaExistente.setEstaciones(estaciones);

        // Asegurarse de que las estaciones estén actualizadas con la ruta
        for (Estacion estacion : estaciones) {
            if (!estacion.getRutas().contains(rutaExistente)) {
                estacion.getRutas().add(rutaExistente);
            }
        }

        // Guardar la ruta editada
        Ruta rutaEditada = rutaService.guardar(rutaExistente);

        // Convertir la ruta editada a DTO
        List<EstacionDTO> estacionDTOs = rutaEditada.getEstaciones().stream()
                .map(estacion -> new EstacionDTO(estacion.getId(), estacion.getNombre(), estacion.getCiudad(),
                        estacion.getProvincia(), estacion.getCodigoPostal()))
                .collect(Collectors.toList());

        RutaDTO rutaResponse = new RutaDTO(rutaEditada.getId(), rutaEditada.getNombreRuta(), rutaEditada.getDescripcion(), estacionDTOs);

        return ResponseEntity.ok(rutaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRuta(@PathVariable Long id) {
        rutaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
