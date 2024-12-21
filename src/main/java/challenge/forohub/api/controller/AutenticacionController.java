package challenge.forohub.api.controller;

import challenge.forohub.api.domain.usuarios.DtoAutenticacionUsario;
import challenge.forohub.api.domain.usuarios.Usuario;
import challenge.forohub.api.infra.security.DtoJWTToken;
import challenge.forohub.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DtoAutenticacionUsario dtoAutenticacionUsario) {
        // Crea un token de autenticación con el usuario y la clave
        Authentication authToken = new UsernamePasswordAuthenticationToken(dtoAutenticacionUsario.login(),
                dtoAutenticacionUsario.clave());
        // Verifica si el usuario es correcto y si lo es, lo autentica
        var usuarioAutenticado =  authenticationManager.authenticate(authToken);
        // Genera un token JWT para el usuario autenticado
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());// Se castea el usuario autenticado a Usuario
        // Si el usuario es correcto, retorna un 200 con el DTO DatosJWTToken que contiene el token JWT
        return ResponseEntity.ok(new DtoJWTToken(JWTtoken));
    }

}
