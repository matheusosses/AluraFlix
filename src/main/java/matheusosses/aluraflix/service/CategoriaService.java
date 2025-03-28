package matheusosses.aluraflix.service;

import matheusosses.aluraflix.dto.categoria.AtualizacaoCategoriaDto;
import matheusosses.aluraflix.dto.categoria.CadastroCategoriaDto;
import matheusosses.aluraflix.dto.categoria.CategoriaDto;
import matheusosses.aluraflix.dto.video.VideoDto;
import matheusosses.aluraflix.exception.ValidacaoException;
import matheusosses.aluraflix.model.Categoria;
import matheusosses.aluraflix.repository.CategoriaRepository;
import matheusosses.aluraflix.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;
    private final VideoRepository videoRepository;

    public CategoriaService(CategoriaRepository repository, VideoRepository videoRepository) {
        this.repository = repository;
        this.videoRepository = videoRepository;
    }

    public List<CategoriaDto> listarCategorias() {
        List<Categoria> categorias = repository.findAll();

        if (categorias.isEmpty()) {
            throw new ValidacaoException("Nao há categorias cadastradas");
        }

        return categorias.stream()
                .map(CategoriaDto::new)
                .toList();
    }

    public CategoriaDto buscarporId(Long id) {
        Categoria categoria =  repository.findById(id).orElseThrow(() -> new ValidacaoException("Categoria com id fornecido não encontrado"));
        return new CategoriaDto(categoria);
    }

    @Transactional
    public CategoriaDto cadastrarCategoria(CadastroCategoriaDto dto) {
        Categoria categoria = new Categoria(dto);
        repository.save(categoria);
        return new CategoriaDto(categoria);
    }

    @Transactional
    public CategoriaDto atualizarCategoria(Long id, AtualizacaoCategoriaDto dto) {
        Categoria categoria = repository.findById(id).orElseThrow(() -> new ValidacaoException("Nao há categoria com id fornecido"));
        categoria.atualizar(dto);
        repository.save(categoria);
        return new CategoriaDto(categoria);
    }

    @Transactional
    public void inativarCategoria(Long id) {
        Categoria categoria = repository.findById(id).orElseThrow(() -> new ValidacaoException("Nao há categoria com id fornecido"));
        categoria.inativar();
        repository.save(categoria);
    }

    public List<VideoDto> listarPorCategoria(Long id) {
        return videoRepository.listarPorCategoria(id).stream().
                map(VideoDto::new)
                .toList();
    }
}
