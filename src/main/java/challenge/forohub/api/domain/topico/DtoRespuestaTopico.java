package challenge.forohub.api.domain.topico;

import java.time.LocalDateTime;

public record DtoRespuestaTopico (
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String autor,
        NombreCurso curso,
        Boolean resuelto
){
    public DtoRespuestaTopico (Topico topico){
        this(topico.getTitulo(), topico.getMensaje(), topico.getFecha(), topico.getAutor(), topico.getCurso(), topico.getResuelto());
    }


}


