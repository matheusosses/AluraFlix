package matheusosses.aluraflix.controller;

import jakarta.validation.Valid;
import matheusosses.aluraflix.dto.categoria.AtualizacaoCategoriaDto;
import matheusosses.aluraflix.dto.categoria.CadastroCategoriaDto;
import matheusosses.aluraflix.dto.categoria.CategoriaDto;
import matheusosses.aluraflix.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> listarCategorias() {
       return ResponseEntity.ok(service.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarporId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> cadastrarCategoria(@RequestBody @Valid CadastroCategoriaDto dto){
        return ResponseEntity.ok(service.cadastrarCategoria(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> atualizarCategoria(@RequestBody @Valid AtualizacaoCategoriaDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizarCategoria(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerCategoria(@PathVariable Long id) {
        service.inativarCategoria(id);
        return ResponseEntity.ok("Categoria removida com sucesso!");
    }
}
