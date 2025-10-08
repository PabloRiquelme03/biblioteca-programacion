package cl.tuuni.biblioteca.repo;

import cl.tuuni.biblioteca.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InscripcionRepo extends JpaRepository<Inscripcion, Long> {

    Optional<Inscripcion> findByUsuarioEmailAndCursoId(String email, Long cursoId);

    // NUEVO: todas las inscripciones de un usuario
    List<Inscripcion> findByUsuarioEmail(String email);
}
