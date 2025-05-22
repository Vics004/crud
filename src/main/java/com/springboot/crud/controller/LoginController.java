package com.springboot.crud.controller;

import com.springboot.crud.model.Usuario;
import com.springboot.crud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String loginForm() {
        return "inicio/login";
    }
    
    @GetMapping("/")
    public String home() {
        return "redirect:/productos";
    }
}