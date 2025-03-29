package matheusosses.aluraflix.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import matheusosses.aluraflix.exception.CadastroCategoriaException;
import matheusosses.aluraflix.validation.HexOrColor;

public record CadastroCategoriaDto(
        @NotBlank String titulo,
        @NotBlank @HexOrColor String cor) {

        public CadastroCategoriaDto {
                if(titulo == null || titulo.isBlank()){
                        throw new CadastroCategoriaException();
                }
                if(cor == null || cor.isBlank()){
                        throw new CadastroCategoriaException();
                }
        }
}
