package com.trains.trains.servicios;

import com.trains.trains.entidades.Usuario;
import com.trains.trains.repositorios.UsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends AbstractService<Usuario, Long> {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(JpaRepository<Usuario, Long> repository,
                          UsuarioRepository usuarioRepository) {
        super(repository);
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario editar(Long id, Usuario entidadEditada) throws Exception {
        return usuarioRepository.save(entidadEditada);
    }
}
