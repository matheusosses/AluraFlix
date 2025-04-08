package matheusosses.aluraflix.dto.video;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record AtualizacaoVideoDTO(
        String titulo,

        String descricao,

        @URL
        @Size(min = 1, max = 350)
        String url,

        Long categoria) {
}
