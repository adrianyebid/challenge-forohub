package challenge.forohub.api.domain.topico;

public record DtoActualizarTopico(
        String titulo,
        String mensaje,
        Boolean resuelto
) {
}
