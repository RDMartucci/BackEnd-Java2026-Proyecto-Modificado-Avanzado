package com.proyectofinal.ApiArticulos.service;

import com.proyectofinal.ApiArticulos.dto.AuthResponseDTO;
import com.proyectofinal.ApiArticulos.dto.LoginRequestDTO;
import com.proyectofinal.ApiArticulos.dto.RegisterRequestDTO;

public interface AuthService {
    AuthResponseDTO register(RegisterRequestDTO request);
    AuthResponseDTO login(LoginRequestDTO request);
}
