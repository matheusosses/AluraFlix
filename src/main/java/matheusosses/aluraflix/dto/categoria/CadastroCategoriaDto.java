package matheusosses.aluraflix.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import matheusosses.aluraflix.validation.HexOrColor;

public record CadastroCategoriaDto(
        @NotBlank
        String titulo,

        @NotBlank
        @HexOrColor
        String cor) {
}
