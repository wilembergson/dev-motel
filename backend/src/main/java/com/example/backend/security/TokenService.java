package com.example.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.backend.exceptions.DefaultError;
import com.example.backend.model.entity.Customer;
import com.example.backend.model.entity.GeneralUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Customer usr){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("autorizacao-api")
                    .withSubject(usr.getLogin())
                    .withExpiresAt(tempoExpiracao())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro na geração do token.", exception);
        }
    }

    public String validarToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("devmotel-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (TokenExpiredException e){
            throw new DefaultError("Sessão expirada.", HttpStatus.FORBIDDEN);
        }catch (JWTDecodeException e){
            throw new DefaultError("Token inválido.", HttpStatus.FORBIDDEN);
        }
    }

    private Instant tempoExpiracao(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
