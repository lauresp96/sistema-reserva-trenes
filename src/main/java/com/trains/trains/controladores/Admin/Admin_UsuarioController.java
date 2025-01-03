package com.trains.trains.controladores.Admin;


import com.trains.trains.entidades.Usuario;
import com.trains.trains.servicios.ReservaService;
import com.trains.trains.servicios.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/usuarios")

public class Admin_UsuarioController {

    private final UsuarioService usuarioService;

    public Admin_UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String mostrarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.mostrarTodos();
        model.addAttribute("usuarios", usuarios);
        return "admin/usuarios";
    }

    @GetMapping("/crear")
    public String formularioCreacionUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/usuario-form";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "admin/usuario-form";
        }
        Usuario usuarioCreado = usuarioService.guardar(usuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarUsuario(@PathVariable Long id, Model model) throws Exception {
        Usuario usuario = usuarioService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        }
        return "admin/usuario-form";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Usuario usuario, @Valid BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "admin/usuario-form";
        }
        usuario.setId(id);
        usuarioService.editar(id, usuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuarios(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/admin/usuarios";
    }

}
