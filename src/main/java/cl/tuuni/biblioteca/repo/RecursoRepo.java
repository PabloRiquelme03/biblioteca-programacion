package cl.tuuni.biblioteca.repo;

import cl.tuuni.biblioteca.entity.RecursoEducativo;
import cl.tuuni.biblioteca.entity.Lenguaje;
import cl.tuuni.biblioteca.entity.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecursoRepo extends JpaRepository<RecursoEducativo, Long> {

    // ====== SOLO ACTIVOS (para la vista pública) ======
    List<RecursoEducativo> findByActivoTrue();

    List<RecursoEducativo> findByActivoTrueAndNivel(Nivel nivel);

    List<RecursoEducativo> findByActivoTrueAndLenguaje(Lenguaje lenguaje);

    List<RecursoEducativo> findByActivoTrueAndLenguajeAndNivel(Lenguaje lenguaje, Nivel nivel);

    // Usado por ResourceSeed para evitar duplicados
    boolean existsByTitulo(String titulo);
}
