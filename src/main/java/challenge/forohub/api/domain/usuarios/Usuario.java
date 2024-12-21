package challenge.forohub.api.domain.usuarios;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {//Usuario es ahora tipo de dato UserDetails

    //Debmos indicarle a spring el alggoritmos de hash que usamos para encriptar la clave en este caso bcrypt
    //tambien debemos especificarle que campo es el login y cual es la clave

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String clave;


    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getClave() {
        return clave;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Debo Autorizar a los usuarios con el rol de usuario sino no podran acceder a la aplicacion
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }

}
