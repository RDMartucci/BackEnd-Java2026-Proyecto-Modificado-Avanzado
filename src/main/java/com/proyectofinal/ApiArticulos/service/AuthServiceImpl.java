package com.proyectofinal.ApiArticulos.service;

import com.proyectofinal.ApiArticulos.dto.AuthResponseDTO;
import com.proyectofinal.ApiArticulos.dto.LoginRequestDTO;
import com.proyectofinal.ApiArticulos.dto.RegisterRequestDTO;
import com.proyectofinal.ApiArticulos.model.Rol;
import com.proyectofinal.ApiArticulos.model.Usuario;
import com.proyectofinal.ApiArticulos.repository.UsuarioRepository;
import com.proyectofinal.ApiArticulos.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyectofinal.ApiArticulos.exception.CredencialesInvalidasException;
import com.proyectofinal.ApiArticulos.exception.EmailYaRegistradoException;
import com.proyectofinal.ApiArticulos.exception.UsuarioNoEncontradoException;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Value("${app.admin.secret}")
    private String adminSecret;

    public AuthServiceImpl(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            //throw new RuntimeException("El correo ya está registrado");
            throw new EmailYaRegistradoException("El correo ya está registrado");
        }

        Rol rol = Rol.USER;

        if ("ADMIN".equalsIgnoreCase(request.getRol())) {

            if (!adminSecret.equals(request.getCodigoAdmin())) {
                //throw new RuntimeException("Código de administrador incorrecto");
             throw new CredencialesInvalidasException("Código de administrador incorrecto");
            }

            rol = Rol.ADMIN;
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(rol);

        usuarioRepository.save(usuario);

        String token = jwtUtil.generateToken(
                usuario.getEmail(),
                usuario.getRol().name());

        return new AuthResponseDTO(
                token,
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol().name());
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

        } catch (AuthenticationException ex) {

            throw new CredencialesInvalidasException("Credenciales inválidas");

        }

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UsuarioNoEncontradoException("No existe un usuario con el email: " + request.getEmail()));
        String token = jwtUtil.generateToken(
                usuario.getEmail(),
                usuario.getRol().name());

        return new AuthResponseDTO(
                token,
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol().name());
    }
}