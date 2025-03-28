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

    public Video(CadastroVideoDTO dto, Categoria categoria) {
        this.descricao = dto.descricao();
        this.titulo = dto.titulo();
        this.url = dto.url();
        this.categoria = categoria;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private String url;

    @ManyToOne
    private Categoria categoria;

    private boolean ativo;

    public void atualizar(AtualizacaoVideoDTO dto, Categoria categoria) {
        this.titulo = Optional.ofNullable(dto.titulo()).orElse(titulo);
        this.descricao = Optional.ofNullable(dto.descricao()).orElse(descricao);
        this.url = Optional.ofNullable(dto.url()).orElse(url);
        this.categoria = Optional.ofNullable(categoria).orElse(this.categoria);
    }

    public void inativar() {
        this.ativo = false;
    }
}
