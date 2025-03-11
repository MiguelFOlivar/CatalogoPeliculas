package com.mf.JSP_Movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test")
    public String showTest() {
        return "test";  // El archivo test.jsp debe existir en /WEB-INF/views/test.jsp
    }
}
