package matheusosses.aluraflix.service;

import matheusosses.aluraflix.dto.video.AtualizacaoVideoDTO;
import matheusosses.aluraflix.dto.video.CadastroVideoDTO;
import matheusosses.aluraflix.dto.video.VideoDto;
import matheusosses.aluraflix.exception.ValidacaoException;
import matheusosses.aluraflix.model.Categoria;
import matheusosses.aluraflix.model.Video;
import matheusosses.aluraflix.repository.CategoriaRepository;
import matheusosses.aluraflix.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final CategoriaRepository categoriaRepository;

    public VideoService(VideoRepository videoRepository, CategoriaRepository categoriaRepository) {
        this.videoRepository = videoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<VideoDto> listarVideos() {
        List<Video> videos = videoRepository.findByAtivoTrue();

        if (videos.isEmpty()) {
            throw new ValidacaoException("Nenhum video cadastrado");
        }

        return videos.stream()
                .map(VideoDto::new)
                .toList();
    }

    @Transactional
    public VideoDto cadastrarVideo(CadastroVideoDTO dto) {
        var id = Optional.ofNullable(dto.categoria()).orElse(1L);

        Categoria categoria = categoriaRepository.getReferenceById(id);
        Video video = new Video(dto, categoria);
        videoRepository.save(video);
        return new VideoDto(video);
    }


    public VideoDto buscarPorId(Long id) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new ValidacaoException("Nenhum id encontrado"));

        return new VideoDto(video);
    }

    @Transactional
    public VideoDto atualizarVideo(Long id, AtualizacaoVideoDTO dto) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new ValidacaoException("Nenhum id encontrado"));
        Categoria categoria;

        if(dto.categoria() != null) {
            categoria = categoriaRepository.findById(dto.categoria()).orElseThrow(() -> new ValidacaoException("Nenhuma categoria encontrado"));
        } else {
            categoria = null;
        }

        video.atualizar(dto, categoria);
        videoRepository.save(video);
        return new VideoDto(video);
    }

    @Transactional
    public void deletarVideo(Long id) {
        Video video = videoRepository.findByIdAndAtivo(id, true).orElseThrow(() -> new ValidacaoException("Nenhum id encontrado"));

        video.inativar();
        videoRepository.save(video);
    }

    public List<VideoDto> buscarPorTitulo(String search) {
        List<Video> videos = videoRepository.buscarPorTitulo(search);

        if (videos.isEmpty()) {
            throw new ValidacaoException("Nenhum video encontrado");
        }

        return videos.stream()
                .map(VideoDto::new)
                .toList();
    }
}
