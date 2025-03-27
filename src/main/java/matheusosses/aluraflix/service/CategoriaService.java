package matheusosses.aluraflix.service;

import matheusosses.aluraflix.dto.categoria.CategoriaDto;
import matheusosses.aluraflix.exception.ValidacaoException;
import matheusosses.aluraflix.model.Categoria;
import matheusosses.aluraflix.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
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
}
