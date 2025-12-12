package cl.tuuni.biblioteca.repo;

import cl.tuuni.biblioteca.entity.RecursoCompletado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecursoCompletadoRepo extends JpaRepository<RecursoCompletado, Long> {

    List<RecursoCompletado> findByUsuarioEmail(String email);

    Optional<RecursoCompletado> findByUsuarioEmailAndRecursoId(String email, Long recursoId);

    boolean existsByUsuarioEmailAndRecursoId(String email, Long recursoId);
}
