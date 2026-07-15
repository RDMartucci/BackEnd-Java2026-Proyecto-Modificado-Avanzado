package com.proyectofinal.ApiArticulos;

import com.proyectofinal.ApiArticulos.model.Rol;
import com.proyectofinal.ApiArticulos.model.Usuario;
import com.proyectofinal.ApiArticulos.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ApiArticulosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiArticulosApplication.class, args);
    }

    @Bean
    CommandLineRunner initAdminUser(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (!usuarioRepository.existsByEmail("admin@gmail.com")) {

                Usuario admin = new Usuario();
                admin.setNombre("Admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRol(Rol.ADMIN);
                admin.setActivo(true);

                usuarioRepository.save(admin);
            }

        };
    }
}