package matheusosses.aluraflix.repository;

import matheusosses.aluraflix.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByIdAndAtivo(Long id, boolean b);

    List<Video> findByAtivoTrue();

    @Query("SELECT v FROM Video v JOIN v.categoria c WHERE v.ativo = true and c.id = :id ORDER BY c.id")
    List<Video> listarPorCategoria(Long id);

    @Query("SELECT v FROM Video v WHERE LOWER(v.titulo) LIKE LOWER(CONCAT('%',:search,'%'))")
    List<Video> buscarPorTitulo(String search);
}
