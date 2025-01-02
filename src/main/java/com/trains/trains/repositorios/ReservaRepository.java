package com.trains.trains.repositorios;

import com.trains.trains.entidades.Reserva;
import com.trains.trains.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Optional<Usuario> findReservaByUsuario(Usuario usuario);
}
