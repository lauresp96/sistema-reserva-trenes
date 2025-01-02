package com.trains.trains.servicios;

import com.trains.trains.entidades.Viaje;
import com.trains.trains.repositorios.ViajeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ViajeService extends AbstractService<Viaje, Long> {

    private final ViajeRepository viajeRepository;

    public ViajeService(JpaRepository<Viaje, Long> repository,
                        ViajeRepository viajeRepository) {
        super(repository);
        this.viajeRepository = viajeRepository;
    }

    @Override
    public Viaje editar(Long id, Viaje entidadEditada) throws Exception {
        return viajeRepository.save(entidadEditada);
    }
}
