package com.trains.trains.servicios;

import com.trains.trains.entidades.Pago;
import com.trains.trains.repositorios.PagoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PagoService extends AbstractService<Pago, Long> {

    private final PagoRepository pagoRepository;

    public PagoService(JpaRepository<Pago, Long> repository,
                       PagoRepository pagoRepository) {
        super(repository);
        this.pagoRepository = pagoRepository;
    }

    @Override
    public Pago editar(Long id, Pago entidadEditada) throws Exception {
        return pagoRepository.save(entidadEditada);
    }
}
