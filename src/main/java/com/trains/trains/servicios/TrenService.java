package com.trains.trains.servicios;

import com.trains.trains.entidades.Tren;
import com.trains.trains.repositorios.TrenRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TrenService extends AbstractService<Tren,Long> {

    private final TrenRepository trenRepository;

    public TrenService(JpaRepository<Tren, Long> repository,
                       TrenRepository trenRepository) {
        super(repository);
        this.trenRepository = trenRepository;
    }

    @Override
    public Tren editar(Long id, Tren entidadEditada) throws Exception {
        return trenRepository.save(entidadEditada);
    }
}
