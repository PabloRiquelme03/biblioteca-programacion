package cl.tuuni.biblioteca.repo;

import cl.tuuni.biblioteca.entity.ProgresoLeccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgresoLeccionRepo extends JpaRepository<ProgresoLeccion, Long> {

    Optional<ProgresoLeccion> findByInscripcionIdAndLeccionId(Long inscripcionId, Long leccionId);

    List<ProgresoLeccion> findByInscripcionId(Long inscripcionId);

    long countByInscripcionId(Long inscripcionId);

    void deleteByInscripcionId(Long inscripcionId);
}
