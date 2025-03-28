package matheusosses.aluraflix.dto.video;

import matheusosses.aluraflix.model.Video;

public record VideoDto(String titulo, String descricao, String url, Long categoriaId) {
    public VideoDto (Video video){
        this(video.getTitulo(), video.getDescricao(), video.getUrl(), video.getCategoria().getId());
    }
}
