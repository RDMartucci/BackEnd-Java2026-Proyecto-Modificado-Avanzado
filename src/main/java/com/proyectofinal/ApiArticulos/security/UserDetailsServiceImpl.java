package com.proyectofinal.ApiArticulos.security;

import com.proyectofinal.ApiArticulos.model.Usuario;
import com.proyectofinal.ApiArticulos.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));


        return User.builder()
            .username(usuario.getEmail())
            .password(usuario.getPassword())
            .roles(usuario.getRol().name())
            .disabled(!usuario.isActivo())
            .build();
    }
}