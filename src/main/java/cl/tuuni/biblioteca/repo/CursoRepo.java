package cl.tuuni.biblioteca.repo;

import cl.tuuni.biblioteca.entity.Curso;
import cl.tuuni.biblioteca.entity.Lenguaje;
import cl.tuuni.biblioteca.entity.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepo extends JpaRepository<Curso, Long> {
    List<Curso> findByActivoTrueOrderByIdDesc();
    List<Curso> findByActivoTrueAndNivelAndLenguajeOrderByIdDesc(Nivel nivel, Lenguaje lenguaje);
}
