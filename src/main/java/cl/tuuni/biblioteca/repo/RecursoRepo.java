package cl.tuuni.biblioteca.repo;

import cl.tuuni.biblioteca.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecursoRepo extends JpaRepository<RecursoEducativo, Long> {
    List<RecursoEducativo> findByNivelAndActivoTrue(Nivel nivel);
}
