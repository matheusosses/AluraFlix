package matheusosses.aluraflix.controller;

import jakarta.validation.Valid;
import matheusosses.aluraflix.dto.video.AtualizacaoVideoDTO;
import matheusosses.aluraflix.dto.video.CadastroVideoDTO;
import matheusosses.aluraflix.dto.video.VideoDto;
import matheusosses.aluraflix.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService service;

    public VideoController(VideoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VideoDto> cadastrarVideo(@RequestBody @Valid CadastroVideoDTO dto){
        return ResponseEntity.ok(service.cadastrarVideo(dto));
    }

    @GetMapping
    public ResponseEntity<List<VideoDto>> listarVideos(){
        return ResponseEntity.ok(service.listarVideos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> atualizarVideo(@RequestBody @Valid AtualizacaoVideoDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(service.atualizarVideo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVideo(@PathVariable Long id){
        service.deletarVideo(id);
        return ResponseEntity.ok("Video deletado com sucesso!");
    }
}
