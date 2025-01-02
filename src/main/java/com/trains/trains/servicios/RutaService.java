package com.trains.trains.servicios;

import com.trains.trains.entidades.Ruta;
import com.trains.trains.repositorios.RutaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RutaService extends AbstractService<Ruta, Long> {

    private final RutaRepository rutaRepository;

    public RutaService(JpaRepository<Ruta, Long> repository,
                       RutaRepository rutaRepository) {
        super(repository);
        this.rutaRepository = rutaRepository;
    }

    @Override
    public Ruta editar(Long id, Ruta entidadEditada) throws Exception {
        return rutaRepository.save(entidadEditada);
    }
}
