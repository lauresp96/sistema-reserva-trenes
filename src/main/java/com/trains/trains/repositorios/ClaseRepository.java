package com.trains.trains.repositorios;

import com.trains.trains.entidades.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {
    List<Clase> findByNombreClase(String nombreClase);
}
