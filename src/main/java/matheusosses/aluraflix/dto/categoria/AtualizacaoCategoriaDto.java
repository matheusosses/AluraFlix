package matheusosses.aluraflix.dto.categoria;

import matheusosses.aluraflix.validation.HexOrColor;

public record AtualizacaoCategoriaDto(
        String titulo,

        @HexOrColor
        String cor) {
}
