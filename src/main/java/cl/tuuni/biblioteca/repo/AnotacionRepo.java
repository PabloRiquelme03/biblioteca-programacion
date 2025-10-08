package cl.tuuni.biblioteca.repo;

import cl.tuuni.biblioteca.entity.Anotacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnotacionRepo extends JpaRepository<Anotacion, Long> {
    List<Anotacion> findByUsuarioIdOrderByActualizadoDesc(Long usuarioId);
}
