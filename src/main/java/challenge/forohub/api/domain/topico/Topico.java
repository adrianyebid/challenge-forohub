package challenge.forohub.api.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "topicos")
@Entity(name = "Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//sirve para indicar que este atributo es autoincrementable
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private Boolean resuelto;
    private String autor;
    @Enumerated(EnumType.STRING)//sirve para indicar que este atributo es un enumerado
    private NombreCurso curso;


    public Topico() {
    }

    public Topico(DtoRegistroTopico dtoRegistroTopico) {
        this.titulo = dtoRegistroTopico.titulo();
        this.mensaje = dtoRegistroTopico.mensaje();
        this.fecha = LocalDateTime.now();
        this.resuelto = false;
        this.autor = dtoRegistroTopico.autor();
        this.curso = dtoRegistroTopico.curso();
    }

    public void actualizarDatos(DtoActualizarTopico dtoActualizarTopico) {

        if (dtoActualizarTopico.titulo() != null) {
            this.titulo = dtoActualizarTopico.titulo();
        }
        if (dtoActualizarTopico.mensaje() != null) {
            this.mensaje = dtoActualizarTopico.mensaje();
        }
        if (dtoActualizarTopico.resuelto() != null) {
            this.resuelto = dtoActualizarTopico.resuelto();
        }

    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Boolean getResuelto() {
        return resuelto;
    }

    public String getAutor() {
        return autor;
    }

    public NombreCurso getCurso() {
        return curso;
    }
}
