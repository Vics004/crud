package com.springboot.crud.service;

import com.springboot.crud.model.Usuario;
import com.springboot.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Usuario findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }
    
    public Usuario saveUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
}