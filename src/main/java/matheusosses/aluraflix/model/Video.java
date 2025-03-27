package matheusosses.aluraflix.model;

import jakarta.persistence.*;
import lombok.*;
import matheusosses.aluraflix.dto.video.AtualizacaoVideoDTO;
import matheusosses.aluraflix.dto.video.CadastroVideoDTO;

import java.util.Optional;

@Entity
@Table(name = "videos")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Video {

    public Video(CadastroVideoDTO dto) {
        this.descricao = dto.descricao();
        this.titulo = dto.titulo();
        this.url = dto.url();
        this.ativo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private String url;

    private boolean ativo;

    public void atualizar(AtualizacaoVideoDTO dto) {
        this.titulo = Optional.ofNullable(dto.titulo()).orElse(titulo);
        this.descricao = Optional.ofNullable(dto.descricao()).orElse(descricao);
        this.url = Optional.ofNullable(dto.url()).orElse(url);
    }

    public void inativar() {
        this.ativo = false;
    }
}
