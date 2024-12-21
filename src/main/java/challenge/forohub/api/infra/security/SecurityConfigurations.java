package challenge.forohub.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Deshabilita la protección CSRF ya que se está utilizando un esquema de autenticación sin estado (stateless)
        return http.csrf(AbstractHttpConfigurer::disable)
                // Configura la política de creación de sesiones para que sea sin estado (stateless)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configura las reglas de autorización de solicitudes HTTP
                .authorizeHttpRequests(auth -> auth
                        // Permite todas las solicitudes POST a la ruta /login sin autenticación
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers("/v3/api-docs/**","/swagger-ui.html","/swagger-ui/**").permitAll()
                        // Permite todas las solicitudes GET a las rutas de documentación de Swagger sin autenticación
//                        .requestMatchers(HttpMethod.POST, "/medicos").hasRole("ADMIN")// Requiere rol ADMIN para solicitudes POST a la ruta /medicos
//                        .requestMatchers(HttpMethod.DELETE, "/pacientes").hasRole("ADMIN")// Requiere rol ADMIN para solicitudes DELETE a la ruta /pacientes
                        // Requiere autenticación para cualquier otra solicitud
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)// Agrega el filtro de seguridad personalizado
                // Construye y retorna la cadena de filtros de seguridad configurada
                .build();
    }

    @Bean // Indica que este método produce un bean que será administrado por el contenedor de Spring
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        // Obtiene y devuelve una instancia de AuthenticationManager desde la configuración de autenticación proporcionada
        return authenticationConfiguration.getAuthenticationManager();
    }


    //Sirve para encriptar las claves de los usuarios
    @Bean
    public PasswordEncoder passwordEncoder(){
        //
        return new BCryptPasswordEncoder();
    }




}
