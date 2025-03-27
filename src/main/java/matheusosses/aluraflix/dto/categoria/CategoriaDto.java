package matheusosses.aluraflix.dto.categoria;

import matheusosses.aluraflix.model.Categoria;

public record CategoriaDto(String titulo, String cor) {

    public CategoriaDto(Categoria categoria) {
        this(categoria.getTitulo(), categoria.getCor());
    }
}
