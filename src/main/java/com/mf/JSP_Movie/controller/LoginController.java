package com.mf.JSP_Movie.controller;

import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
    private static final int MAX_ATTEMPTS = 3;
    private static final String ATTEMPTS_SESSION_KEY = "loginAttempts";

//    @Autowired
//    private UserService userService; // Asegúrate de tener un servicio que maneje la autenticación

//    @RequestMapping("/login")
//    public String showLoginPage(@RequestParam(value = "errorMessage", required = false) String errorMessage, Model model) {
//        model.addAttribute("errorMessage", errorMessage);
//        return "login";  // La vista de login
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@RequestParam("username") String username,
//                        @RequestParam("password") String password,
//                        HttpServletRequest request, Model model) {
//
//        Integer attempts = (Integer) request.getSession().getAttribute(ATTEMPTS_SESSION_KEY);
//        if (attempts == null) {
//            attempts = 0;
//        }
//
//        if (attempts >= MAX_ATTEMPTS) {
//            model.addAttribute("errorMessage", "Has superado el número máximo de intentos. Intenta de nuevo más tarde.");
//            return "login";
//        }
//
//        // Verifica las credenciales del usuario
//        User user = userService.authenticate(username, password);
//        if (user != null) {
//            // Si el login es exitoso, restablecemos los intentos fallidos
//            request.getSession().setAttribute(ATTEMPTS_SESSION_KEY, 0);
//            return "redirect:/index"; // Página de inicio después de login exitoso
//        } else {
//            // Si las credenciales son incorrectas, incrementamos los intentos fallidos
//            request.getSession().setAttribute(ATTEMPTS_SESSION_KEY, attempts + 1);
//            model.addAttribute("errorMessage", "Usuario o contraseña incorrectos.");
//            return "login";
//        }
//    }
//
//    @RequestMapping("/forgot-password")
//    public String showForgotPasswordPage() {
//        return "forgot-password";  // Vista para recuperar la contraseña
//    }

}
