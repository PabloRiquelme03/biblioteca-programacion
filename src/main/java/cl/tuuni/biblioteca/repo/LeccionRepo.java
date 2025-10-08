package cl.tuuni.biblioteca.repo;

import cl.tuuni.biblioteca.entity.Leccion;
import cl.tuuni.biblioteca.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeccionRepo extends JpaRepository<Leccion, Long> {

    // Opción 1: por entidad (ya la puedes tener)
    List<Leccion> findByCursoOrderByOrdenAsc(Curso curso);

    // Opción 2: por id de curso (ESTE es el que pide tu CursoService)
    List<Leccion> findByCursoIdOrderByOrdenAsc(Long cursoId);
}
