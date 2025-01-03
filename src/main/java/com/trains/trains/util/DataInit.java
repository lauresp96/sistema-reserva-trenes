package com.trains.trains.util;

import com.trains.trains.entidades.Usuario;
import com.trains.trains.repositorios.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInit {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInit(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (usuarioRepository.findByEmail("admin@example.com").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombre("Admin");
                admin.setApellido("Livadaru");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setDireccion("Calle la sabina 1");
                admin.setTelefono("610988345");
                admin.setRol("ADMIN");

                usuarioRepository.save(admin);
                System.out.println("Administrador creado con éxito.");
            } else {
                System.out.println("El administrador ya existe.");
            }
        };
    }
}

