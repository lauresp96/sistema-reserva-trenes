package com.trains.trains.servicios;

import com.trains.trains.entidades.Reserva;
import com.trains.trains.repositorios.ReservaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservaService extends AbstractService<Reserva, Long> {

    private final ReservaRepository reservaRepository;

    public ReservaService(JpaRepository<Reserva, Long> repository,
                          ReservaRepository reservaRepository) {
        super(repository);
        this.reservaRepository = reservaRepository;
    }

    @Override
    public Reserva editar(Long id, Reserva entidadEditada) throws Exception {
        return reservaRepository.save(entidadEditada);
    }
}
