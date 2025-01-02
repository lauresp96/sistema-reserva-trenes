package com.trains.trains.servicios;

import com.trains.trains.entidades.Horario;
import com.trains.trains.repositorios.HorarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class HorarioService extends AbstractService<Horario, Long> {

    private final HorarioRepository horarioRepository;

    public HorarioService(JpaRepository<Horario, Long> repository,
                          HorarioRepository horarioRepository) {
        super(repository);
        this.horarioRepository = horarioRepository;
    }

    @Override
    public Horario editar(Long id, Horario entidadEditada) throws Exception {
        return horarioRepository.save(entidadEditada);
    }
}
