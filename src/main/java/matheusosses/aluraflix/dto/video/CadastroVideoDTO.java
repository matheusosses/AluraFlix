package matheusosses.aluraflix.dto.video;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record CadastroVideoDTO(
        @NotBlank
        String titulo,

        @NotBlank
        String descricao,

        @NotBlank
        @URL
        String url) {
}
