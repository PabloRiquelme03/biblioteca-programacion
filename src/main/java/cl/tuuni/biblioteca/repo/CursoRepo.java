package cl.tuuni.biblioteca.repo;

import cl.tuuni.biblioteca.entity.Curso;
import cl.tuuni.biblioteca.entity.Lenguaje;
import cl.tuuni.biblioteca.entity.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepo extends JpaRepository<Curso, Long> {

    // Cursos activos para la vista de cursos
    List<Curso> findByActivoTrueOrderByIdDesc();

    // Filtro por nivel y lenguaje
    List<Curso> findByActivoTrueAndNivelAndLenguajeOrderByIdDesc(Nivel nivel, Lenguaje lenguaje);

    // Búsqueda para el seed: evita duplicar cursos
    Optional<Curso> findByTituloAndLenguaje(String titulo, Lenguaje lenguaje);
}
