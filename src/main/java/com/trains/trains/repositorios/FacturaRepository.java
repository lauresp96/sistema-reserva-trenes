package com.trains.trains.repositorios;

import com.trains.trains.entidades.Factura;
import com.trains.trains.entidades.Pago;
import com.trains.trains.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
