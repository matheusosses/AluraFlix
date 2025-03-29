package matheusosses.aluraflix.exception;

public class CadastroCategoriaException extends RuntimeException {
    public CadastroCategoriaException() {
        super("O campo é obrigatorio");
    }
}
