package com.example.Practica_IV_PSP_tema_2_eclipse.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Practica_IV_PSP_tema_2_eclipse.dto.LoginRequest;
import com.example.Practica_IV_PSP_tema_2_eclipse.dto.LoginResponse;
import com.example.Practica_IV_PSP_tema_2_eclipse.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager auth;
    private final JwtService jwt;
    private final UserDetailsService users;
    public AuthController(AuthenticationManager auth,
                      JwtService jwt,
                      UserDetailsService users) {
        this.auth = auth;
        this.jwt = jwt;
        this.users = users;
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest datos) {
        auth.authenticate(new UsernamePasswordAuthenticationToken(
            datos.username(), datos.password()));
        UserDetails u = users.loadUserByUsername(datos.username());
        return new LoginResponse(jwt.generar(u));
    }
}

