package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${token.secret.api}")
    private String privateKey;

    public String generateToken(MembersModel membersModel) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            String token = JWT.create().withIssuer("Jefiro.dev")
                    .withSubject(membersModel.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            return null;
        }
    }

    public String isValidToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            return JWT.require(algorithm)
                    .withIssuer("Jefiro.dev")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (Exception e){return null;}
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
