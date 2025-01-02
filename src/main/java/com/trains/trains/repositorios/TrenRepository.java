package com.trains.trains.repositorios;

import com.trains.trains.entidades.Tren;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrenRepository extends JpaRepository<Tren, Long> {

    List<Tren> findByTipoTren(String tipoTren);
}
