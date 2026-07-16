package com.proyectofinal.ApiArticulos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequestDTO {

    @NotBlank(message = "El nombre es obligatorio") // Validación para que el nombre no esté vacío.
    @Size(min = 3, max = 60, message = "El nombre debe tener entre 3 y 60 caracteres") // Validación para que el nombre tenga entre 3 y 60 caracteres..
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ingresar un email válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 50, message = "La contraseña debe tener entre 6 y 50 caracteres")
    private String password;

    @NotBlank(message = "Debe seleccionar un rol")
    @Pattern(
            regexp = "USER|ADMIN", // Expresión regular para validar que el rol sea USER o ADMIN.
            message = "El rol debe ser USER o ADMIN") // Validación para que el rol sea USER o ADMIN.
    private String rol;

    private String codigoAdmin;

    public RegisterRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCodigoAdmin() {
        return codigoAdmin;
    }

    public void setCodigoAdmin(String codigoAdmin) {
        this.codigoAdmin = codigoAdmin;
    }
}
