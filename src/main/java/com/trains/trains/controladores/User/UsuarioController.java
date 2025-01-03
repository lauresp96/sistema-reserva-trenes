package com.trains.trains.controladores.User;

import com.trains.trains.entidades.Usuario;
import com.trains.trains.servicios.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String formularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "user/registro";
    }

    @PostMapping("/guardar")
    public String registroUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            return "user/registro";
        }
        usuario.setRol("USER");
        usuarioService.guardar(usuario);
        return "redirect: /login";
    }


}
