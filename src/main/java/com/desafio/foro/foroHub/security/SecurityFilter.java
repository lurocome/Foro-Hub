package com.desafio.foro.foroHub.security;

import com.desafio.foro.foroHub.topico.TopicoRepository;
import com.desafio.foro.foroHub.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();
//       Ignorar la validación de token para la ruta de login
        if ("/login".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        var tokenJWT = recuperarToken(request);
        if (tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);

            //var usuario = usuarioRepository.findByLogin(subject);
            var usuario = usuarioRepository.findByLogin(subject)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            //var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            var authentication = new UsernamePasswordAuthenticationToken(
                    usuario,
                    null,
                    usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
