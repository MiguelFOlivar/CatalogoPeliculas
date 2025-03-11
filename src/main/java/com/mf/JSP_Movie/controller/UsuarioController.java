package com.mf.JSP_Movie.controller;

import com.mf.JSP_Movie.model.Usuario;
import com.mf.JSP_Movie.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar el formulario de login
    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }

    // Procesar el formulario de login
    @PostMapping("/login")
    public String loginUsuario(Usuario usuario, String password, Model model) {

        String mensaje = usuarioService.verificarLogin(usuario, password);
        model.addAttribute("mensaje", mensaje);

        if (mensaje.equals("Login exitoso.")) {
            System.out.println("Logeando...");
            return "redirect:/index2";  // Redirigir al home después de un login exitoso
        } else {
            return "login";  // Si el login falla, mostrar el formulario de login de nuevo
        }

    }

    // Mostrar el formulario de registro
    @GetMapping("/register")
    public String mostrarFormularioRegistro() {
        return "register";
    }

    // Procesar el formulario de registro
    @PostMapping("/register")
    public String registrarUsuario(Usuario usuario, String confirmPassword, Model model) {

        if (!usuario.getPassword().equals(confirmPassword)) {
            model.addAttribute("mensaje", "Las contraseñas no coinciden.");
            return "register";
        }

        String mensaje = usuarioService.registrarUsuario(usuario);
        System.out.println("usuario registrado: " + usuario.getUsername());
        model.addAttribute("mensaje", mensaje);
        return "register";
    }

}
