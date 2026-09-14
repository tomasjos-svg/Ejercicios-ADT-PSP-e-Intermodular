package com.example.Practica_IV_PSP_tema_2_eclipse.service;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwt;
    private final UserDetailsService users;

    public JwtAuthFilter(JwtService jwt, UserDetailsService users) {
        this.jwt = jwt;
        this.users = users;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String h = request.getHeader("Authorization");
        if (h != null && h.startsWith("Bearer ")) {
            try {
                String token = h.substring(7);
                String nombre = jwt.usuario(token);
                if (SecurityContextHolder.getContext()
                        .getAuthentication() == null) {
                    UserDetails u = users.loadUserByUsername(nombre);
                    var auth = new UsernamePasswordAuthenticationToken(
                        u, null, u.getAuthorities());
                    SecurityContextHolder.getContext()
                        .setAuthentication(auth);
                }
            } catch (JwtException ex) {
                // token inválido o caducado: continuará sin autenticar
            }
        }
        chain.doFilter(request, response);
    }
}
