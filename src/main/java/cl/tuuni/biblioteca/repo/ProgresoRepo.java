package cl.tuuni.biblioteca.repo;

import cl.tuuni.biblioteca.entity.Progreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgresoRepo extends JpaRepository<Progreso, Long> {

    boolean existsByUsuarioIdAndRecursoId(Long usuarioId, Long recursoId);

    Optional<Progreso> findByUsuarioIdAndRecursoId(Long usuarioId, Long recursoId);

    List<Progreso> findByUsuarioIdOrderByFechaDesc(Long usuarioId);

    // 👇 finder directo por email del usuario (evita errores de join property names)
    Optional<Progreso> findByUsuarioEmailAndRecursoId(String email, Long recursoId);
}
