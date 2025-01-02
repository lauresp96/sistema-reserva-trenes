package com.trains.trains.repositorios;

import com.trains.trains.entidades.Tren;
import com.trains.trains.entidades.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    List<Viaje> findByTren(Tren tren);

}
