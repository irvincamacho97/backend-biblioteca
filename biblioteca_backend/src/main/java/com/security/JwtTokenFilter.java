package com.security;

import java.util.ArrayList;

import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.response.BaseResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

@Autowired
private JwtUtil jwtUtil;

@Override
protected void doFilterInternal(@NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain)
        throws ServletException, IOException {
    try {
        // Lista de las url permitidas para el publico
        String[] urlPermitidas = { "/api/biblioteca/usuario/autenticar"  };

        for (String url : urlPermitidas) {
            if (request.getRequestURI().contains(url)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String token = jwtUtil.extraerToken(request);

        if (token != null) {
            // Se valida el  token
            Claims claims = jwtUtil.validarToken(token);
            // Se guarda el contenido del token
            request.setAttribute("claims", claims);
            
            filterChain.doFilter(request, response);
        } else {
            throw new JwtException("Token no proporcionado");
        }

    } catch (JwtException ex) {
        // Capturar y manejar JwtException
        log.error("Error al procesar el token: {}", ex.getMessage());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper objectMapper = new ObjectMapper();

        List<String> list = new ArrayList<>();
        list.add(ex.getMessage());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMensaje("Credenciales proporcionadas inválidas o expiradas.");
        String jsonResponse = objectMapper.writeValueAsString(baseResponse);
        response.getWriter().write(jsonResponse);
    } catch (ServletException e) {
        log.error("ServletException:", e);

    } catch (IOException e) {
        log.error("IOException:", e);
    }
}
}
