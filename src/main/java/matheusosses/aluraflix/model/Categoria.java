package matheusosses.aluraflix.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import matheusosses.aluraflix.dto.categoria.AtualizacaoCategoriaDto;
import matheusosses.aluraflix.dto.categoria.CadastroCategoriaDto;
import matheusosses.aluraflix.dto.categoria.CategoriaDto;

import java.util.Optional;

@Entity
@Table(name = "categorias")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String cor;

    private boolean ativo;

    public Categoria(CadastroCategoriaDto dto) {
        this.titulo = dto.titulo();
        this.cor = dto.cor();
    }

    public void atualizar(AtualizacaoCategoriaDto dto) {
        this.titulo = Optional.ofNullable(dto.titulo()).orElse(titulo);
        this.cor = Optional.ofNullable(dto.cor()).orElse(cor);
    }

    public void inativar(){
        this.ativo = false ;
    }
}
