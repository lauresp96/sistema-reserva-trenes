package com.trains.trains.servicios;

import com.trains.trains.entidades.Clase;
import com.trains.trains.repositorios.ClaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ClaseService extends AbstractService<Clase, Long>  {

    private final ClaseRepository claseRepository;

    public ClaseService(JpaRepository<Clase, Long> repository,
                        ClaseRepository claseRepository) {
        super(repository);
        this.claseRepository = claseRepository;
    }

    @Override
    public Clase editar(Long id, Clase entidadEditada) throws Exception {
        return claseRepository.save(entidadEditada);
    }
}
