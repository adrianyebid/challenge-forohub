package challenge.forohub.api.infra.security;

import challenge.forohub.api.domain.usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")// Inyecta la clave secreta desde el archivo de configuración
    private String apiSecret;

    // Método que genera un token JWT
    public String generarToken(Usuario usuario){

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);// Valida la firma del token con la clave secreta

            // Crea el token JWT con el emisor y el sujeto, y lo firma con el algoritmo definido
            return JWT.create()
                    .withIssuer("ForoHub") // Establece el emisor del token
                    .withSubject(usuario.getUsername()) // Establece el sujeto del token
                    .withClaim("id", usuario.getId()) // Establece un claim personalizado con el id del usuario
                    .withExpiresAt(generateExpirationDate()) // Establece la fecha y hora de expiración del token
                    .sign(algorithm); // Firma el token con el algoritmo
        } catch (JWTCreationException exception){
            // Lanza una excepción en caso de error al crear el token
            throw new RuntimeException();
        }
    }

    //Método que verifica un token JWT y retorna el sujeto (subject) del token
    public String getSubject(String token) {

        if(token == null) {
            throw new RuntimeException("Token nulo");
        }

        DecodedJWT verifier = null; // Inicializa la variable que contendrá el token decodificado
        try {
            // Define el algoritmo de encriptación HMAC256 con la clave secreta
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);// Valida la firma del token con la clave secreta
            // Configura el verificador del token con el algoritmo y el emisor esperado
            verifier = JWT.require(algorithm)
                    .withIssuer("ForoHub")
                    .build()
                    .verify(token); // Verifica el token y lo decodifica
            verifier.getSubject(); // Obtiene el sujeto (subject) del token
        } catch (JWTVerificationException exception) {
            // Captura cualquier excepción de verificación del token (firma/claims inválidos)\
            System.out.println(exception.toString());
        }
        // Si el sujeto del token es nulo, lanza una excepción indicando que la verificación falló
        assert verifier != null;
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verificación de token fallida");
        }
        return verifier.getSubject(); // Retorna el sujeto (subject) del token
    }



    // Método que genera la fecha y hora de expiración del token a 2 horas a partir de ahora
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
