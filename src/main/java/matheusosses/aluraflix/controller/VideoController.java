package matheusosses.aluraflix.controller;

import jakarta.validation.Valid;
import matheusosses.aluraflix.dto.AtualizacaoVideoDTO;
import matheusosses.aluraflix.dto.CadastroVideoDTO;
import matheusosses.aluraflix.dto.VideoDto;
import matheusosses.aluraflix.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService service;

    public VideoController(VideoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VideoDto> criarVideo(@RequestBody @Valid CadastroVideoDTO dto){
        return ResponseEntity.ok().body(service.cadastrarVideo(dto));
    }

    @GetMapping
    public ResponseEntity<List<VideoDto>> listarVideos(){
        return ResponseEntity.ok().body(service.listarVideos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> atualizarVideo(@RequestBody @Valid AtualizacaoVideoDTO dto, @PathVariable Long id){
        return ResponseEntity.ok().body(service.atualizarVideo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVideo(@PathVariable Long id){
        service.deletarVideo(id);
        return ResponseEntity.ok().body("Video deletado com sucesso!");
    }
}
