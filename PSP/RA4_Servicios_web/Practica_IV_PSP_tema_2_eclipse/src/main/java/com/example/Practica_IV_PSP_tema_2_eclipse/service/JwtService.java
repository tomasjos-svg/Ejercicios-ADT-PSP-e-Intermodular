package com.example.Practica_IV_PSP_tema_2_eclipse.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(
            secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generar(UserDetails user) {
        Date ahora = new Date();
        Date fin = new Date(ahora.getTime() + 15 * 60 * 1000);
        return Jwts.builder()
            .subject(user.getUsername())
            .issuedAt(ahora)
            .expiration(fin)
            .signWith(key())
            .compact();
    }

    public String usuario(String token) {
        return Jwts.parser().verifyWith(key()).build()
            .parseSignedClaims(token).getPayload().getSubject();
    }
}
