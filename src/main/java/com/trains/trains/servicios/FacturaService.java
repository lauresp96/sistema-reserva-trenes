package com.trains.trains.servicios;

import com.trains.trains.entidades.Factura;
import com.trains.trains.repositorios.FacturaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FacturaService extends AbstractService<Factura, Long> {

    private final FacturaRepository facturaRepository;


    public FacturaService(JpaRepository<Factura, Long> repository,
                          FacturaRepository facturaRepository) {
        super(repository);
        this.facturaRepository = facturaRepository;
    }

    @Override
    public Factura editar(Long id, Factura entidadEditada) throws Exception {
        return facturaRepository.save(entidadEditada);
    }
}
