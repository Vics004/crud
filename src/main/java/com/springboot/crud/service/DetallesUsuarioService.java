package com.springboot.crud.service;

import com.springboot.crud.model.Usuario;
import com.springboot.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class DetallesUsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombre(nombre);
        
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + nombre);
        }
        
        return new User(
            usuario.getNombre(),
            usuario.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol()))
        );
    }
}
