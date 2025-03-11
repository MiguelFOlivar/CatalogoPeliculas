package com.mf.JSP_Movie.controller;

import com.mf.JSP_Movie.model.Usuario;
import com.mf.JSP_Movie.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

//    @GetMapping("/login")
//    public String showLogin() {
//        return "login";
//    }


//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password, Model model) {
//
//        boolean authenticated = usuarioService.authemticate(username, password);
//
//        if(authenticated) {
//            String token = usuarioService.generateToken(username);
//            model.addAttribute("token", token);
//            return "index2";
//        }else {
//            model.addAttribute("error", "Credenciales incorrectas o intentos fallidos");
//            return "login";
//        }
//
//    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

//    @PostMapping("/register")
//    public String register(@RequestParam String username, @RequestParam String password) {
//
//        Usuario usuario = new Usuario(username, password, "USER");
//        usuarioService.registerUser(usuario);
//
//        return "redirect:/login";
//    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email, Model model) {

        model.addAttribute("message", "Revisa tu correo para instrucciones de recuperacion");
        return "forgot-password";
    }

}
