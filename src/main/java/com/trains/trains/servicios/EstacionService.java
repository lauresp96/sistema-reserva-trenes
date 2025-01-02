package com.trains.trains.servicios;

import com.trains.trains.entidades.Estacion;
import com.trains.trains.repositorios.EstacionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstacionService extends AbstractService<Estacion, Long> {

    private final EstacionRepository estacionRepository;


    public EstacionService(JpaRepository<Estacion, Long> repository,
                           EstacionRepository estacionRepository) {
        super(repository);
        this.estacionRepository = estacionRepository;
    }

    @Override
    public Estacion editar(Long id, Estacion entidadEditada) throws Exception {
        return estacionRepository.save(entidadEditada);
    }

    public List<Estacion> encontrarEstacionPorIds(List<Long> ids) {
        return estacionRepository.findAllById(ids);
    }
}
