
package com.practica.procesamientoia.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.practica.procesamientoia.model.Rol;
import com.practica.procesamientoia.model.Usuario;
import com.practica.procesamientoia.repository.UsuarioRepository;
import com.practica.procesamientoia.service.UsuarioDetailsService;

@Configuration
public class SecurityConfig {

    private final UsuarioDetailsService usuarioDetailsService;

    public SecurityConfig(UsuarioDetailsService usuarioDetailsService) {
        this.usuarioDetailsService = usuarioDetailsService;
    }

    /*
     * Codificador de contraseñas.
     * Las contraseñas nunca se almacenan en texto claro.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Indica a Spring Security que los usuarios se obtienen
     * mediante UsuarioDetailsService y que las contraseñas
     * se comprueban utilizando BCrypt.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(usuarioDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    /*
     * Configuración de seguridad de la API.
     */
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(authenticationProvider())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/trabajos/**")
                    .hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/trabajos/importar/**")
                    .hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/trabajos/procesar/**")
                    .hasRole("ADMIN")
                .anyRequest()
                    .authenticated()
            )
            .httpBasic(basic -> {});

        return http.build();
    }
    /*
     * Crea los usuarios iniciales únicamente si todavía
     * no existen en la base de datos.
     */
  
    @Bean
    public CommandLineRunner crearUsuariosIniciales(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            @Value("${app.admin.password}") String adminPassword,
            @Value("${app.user.password}") String userPassword) {
        return args -> {
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(
                        passwordEncoder.encode(adminPassword));
                admin.setRol(Rol.ROLE_ADMIN);
                usuarioRepository.save(admin);
            }

            if (usuarioRepository.findByUsername("user").isEmpty()) {

                Usuario user = new Usuario();

                user.setUsername("user");
                user.setPassword(
                        passwordEncoder.encode(userPassword));
                user.setRol(Rol.ROLE_USER);

                usuarioRepository.save(user);
            }
        };
    }
}