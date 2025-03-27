package matheusosses.aluraflix.repository;

import matheusosses.aluraflix.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByIdAndAtivo(Long id, boolean b);

    List<Video> findByAtivoTrue();
}
