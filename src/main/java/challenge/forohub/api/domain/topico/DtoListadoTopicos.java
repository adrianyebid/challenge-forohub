package challenge.forohub.api.domain.topico;

import java.time.LocalDateTime;

public record DtoListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String autor,
        String curso,
        Boolean resuelto
) {

    public DtoListadoTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getFecha(), topico.getAutor(), topico.getCurso().name(), topico.getResuelto());
    }
}
