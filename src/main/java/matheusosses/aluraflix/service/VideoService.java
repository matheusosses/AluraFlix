package matheusosses.aluraflix.service;

import matheusosses.aluraflix.dto.AtualizacaoVideoDTO;
import matheusosses.aluraflix.dto.CadastroVideoDTO;
import matheusosses.aluraflix.dto.VideoDto;
import matheusosses.aluraflix.exception.ValidacaoException;
import matheusosses.aluraflix.model.Video;
import matheusosses.aluraflix.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
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
        Video video = new Video(dto);
        videoRepository.save(video);
        return new VideoDto(video);
    }


    public VideoDto buscarPorId(Long id) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new ValidacaoException("Nenhum id encontrado"));
        return new VideoDto(video);
    }

    @Transactional
    public VideoDto atualizarVideo(Long id, AtualizacaoVideoDTO dto) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new ValidacaoException("Nenhum id encontrado"));;

        video.atualizar(dto);
        videoRepository.save(video);
        return new VideoDto(video);
    }

    @Transactional
    public void deletarVideo(Long id) {
        Video video = videoRepository.findByIdAndAtivo(id, true).orElseThrow(() -> new ValidacaoException("Nenhum id encontrado"));

        video.inativar();
        videoRepository.save(video);
    }
}
