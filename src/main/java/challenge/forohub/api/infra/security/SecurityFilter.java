package challenge.forohub.api.infra.security;


import challenge.forohub.api.domain.usuarios.UsuarioRepository;
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

// Clase que filtra las peticiones para validar el token de autorización y forzar el inicio de sesión
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;// Inyecta el servicio de tokens
    @Autowired
    private UsuarioRepository usuarioRepository;// Inyecta el repositorio de usuarios

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Obtiene el token de autorización de los headers de la request
        var authHeader = request.getHeader("Authorization");// Obtiene el token de autorización de los headers de la request
        if(authHeader != null) {
            var token = authHeader.replace("Bearer ", ""); // Elimina la palabra "Bearer " del token
            var username = tokenService.getSubject(token); // Obtiene el sujeto del token
            if(username != null) {
                //token valido
                var usuario = usuarioRepository.findByLogin(username); // Busca el usuario por su login
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities()); // Forzamos un inicio de sesión con el usuario encontrado
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
