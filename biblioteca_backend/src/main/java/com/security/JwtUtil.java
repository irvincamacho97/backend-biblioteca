package com.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JwtUtil {

@Value("${security.jwt.secret-key}")
private String secretKey;

/** Tiempo de expiración del JWT **/
@Value("${security.jwt.tiempo-expiracion}")
private long tiempoExpiracion;

public String generateToken(int idUsuario, String correo) {

return Jwts.builder()
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion))
        .claim("id_usuario", idUsuario)
        .claim("correo", correo)
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS512)
        .compact();
}

public String extraerToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");

    if (header != null && header.startsWith("Bearer ")) {
        return header.substring(7);
    }

    return null;
}

    public Claims validarToken(String token) throws JwtException {
        String msgErrorLog = "Error al procesar el token: {}";
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException ex) {
            log.error(msgErrorLog, ex.getMessage());
            throw new JwtException("Token invalido.");
        } catch (ExpiredJwtException ex) {
            log.error(msgErrorLog, ex.getMessage());
            throw new JwtException("Token expirado.");
        } catch (MalformedJwtException ex) {
            log.error(msgErrorLog, ex.getMessage());
            throw new JwtException("Token erroneo.");
        } catch (UnsupportedJwtException ex) {
            log.error(msgErrorLog, ex.getMessage());
            throw new JwtException("Token erroneo.");
        }
    }

}
