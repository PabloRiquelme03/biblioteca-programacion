package cl.tuuni.biblioteca.repo;

import cl.tuuni.biblioteca.entity.RecursoEducativo;
import cl.tuuni.biblioteca.entity.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecursoRepo extends JpaRepository<RecursoEducativo, Long> {
    List<RecursoEducativo> findByNivel(Nivel nivel);

    // 👇 Para que el seed sea idempotente
    boolean existsByTitulo(String titulo);
}
