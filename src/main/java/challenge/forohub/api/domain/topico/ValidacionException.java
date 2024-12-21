package challenge.forohub.api.domain.topico;

// Excepción personalizada para errores de validación
public class ValidacionException extends RuntimeException {
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
