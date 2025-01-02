package com.trains.trains.repositorios;

import com.trains.trains.entidades.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {
    List<Estacion> findAllByIdIn(List<Long> ids);
}
