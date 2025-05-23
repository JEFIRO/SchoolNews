package com.SchoolNews.jefiro.br.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfg {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/v1/singin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/v1/singup").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/newSchool").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/loginSchool").permitAll()
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",          // <- ESSENCIAL para funcionar
                                "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/news/like/**").hasRole("STUDENT")
                        .requestMatchers("/auth/v1/allMember/**").hasRole("ADMIN")
                        .requestMatchers("/api/permission/**").hasRole("ADMIN")
                        .requestMatchers("/api/news/**").hasRole("EDITOR")
                        .requestMatchers(HttpMethod.GET, "/find/**").hasRole("ADMIN")

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)

                .cors(cors -> cors.configurationSource(request -> {
                    org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();

                    config.setAllowedOrigins(List.of("http://192.168.86.17"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);

                    return config;
                }))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}