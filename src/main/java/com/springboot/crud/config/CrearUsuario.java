package com.springboot.crud.config;

import com.springboot.crud.model.Usuario;
import com.springboot.crud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CrearUsuario implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {
        
        if (usuarioService.findByNombre("admin") == null) {
            Usuario admin = new Usuario("admin", "admin123", "ROLE_ADMIN");
            usuarioService.saveUsuario(admin);
        }
        
        if (usuarioService.findByNombre("user") == null) {
            Usuario user = new Usuario("user", "user123", "ROLE_USER");
            usuarioService.saveUsuario(user);
        }
        if (usuarioService.findByNombre("Sara") == null) {
            Usuario user = new Usuario("Sara", "1234", "ROLE_ADMIN");
            usuarioService.saveUsuario(user);
        }
        if (usuarioService.findByNombre("Alejandra") == null) {
            Usuario admin = new Usuario("Alejandra", "1234", "ROLE_ADMIN");
            usuarioService.saveUsuario(admin);
        }
        if (usuarioService.findByNombre("Fernando") == null) {
            Usuario user = new Usuario("Fernando", "1234", "ROLE_USER");
            usuarioService.saveUsuario(user);
        }
        if (usuarioService.findByNombre("Emmersson") == null) {
            Usuario admin = new Usuario("Emmersson", "1234", "ROLE_USER");
            usuarioService.saveUsuario(admin);
        }
         if (usuarioService.findByNombre("Víctor") == null) {
            Usuario admin = new Usuario("Víctor", "1234", "ROLE_USER");
            usuarioService.saveUsuario(admin);
        }
    }
}