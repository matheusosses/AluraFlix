package matheusosses.aluraflix.repository;

import matheusosses.aluraflix.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
