package com.trains.trains.servicios;

import com.trains.trains.entidades.Usuario;
import com.trains.trains.repositorios.UsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends AbstractService<Usuario, Long> {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(JpaRepository<Usuario, Long> repository,
                          UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario guardar(Usuario usuario) throws Exception {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario editar(Long id, Usuario entidadEditada) throws Exception {
        entidadEditada.setPassword(passwordEncoder.encode(entidadEditada.getPassword()));
        return usuarioRepository.save(entidadEditada);
    }
}
