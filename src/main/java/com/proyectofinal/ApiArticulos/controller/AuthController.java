package com.proyectofinal.ApiArticulos.controller;

import com.proyectofinal.ApiArticulos.dto.AuthResponseDTO;
import com.proyectofinal.ApiArticulos.dto.LoginRequestDTO;
import com.proyectofinal.ApiArticulos.dto.RegisterRequestDTO;
import com.proyectofinal.ApiArticulos.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
            return ResponseEntity.ok(authService.login(request));
    }
}
