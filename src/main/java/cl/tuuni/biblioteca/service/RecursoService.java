package cl.tuuni.biblioteca.service;

import cl.tuuni.biblioteca.entity.Lenguaje;
import cl.tuuni.biblioteca.entity.Nivel;
import cl.tuuni.biblioteca.entity.RecursoEducativo;
import cl.tuuni.biblioteca.repo.ProgresoRepo;
import cl.tuuni.biblioteca.repo.RecursoRepo;
import cl.tuuni.biblioteca.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecursoService {

    private final RecursoRepo repo;
    private final UsuarioRepo usuarioRepo;
    private final ProgresoRepo progresoRepo;

    /**
     * Lista recursos ACTIVOS, filtrados por lenguaje/nivel,
     * EXCLUYENDO los que el usuario ya completó.
     */
    public List<RecursoEducativo> listarFiltradoExcluyendoCompletados(
            Lenguaje lenguaje,
            Nivel nivel,
            Principal principal
    ) {
        List<RecursoEducativo> base = listarFiltradoActivos(lenguaje, nivel);

        if (principal == null) return base;

        var usuario = usuarioRepo.findByEmail(principal.getName()).orElse(null);
        if (usuario == null) return base;

        Set<Long> completadosIds = progresoRepo.findByUsuarioIdOrderByFechaDesc(usuario.getId())
                .stream()
                .map(p -> p.getRecurso().getId())
                .collect(Collectors.toSet());

        return base.stream()
                .filter(r -> !completadosIds.contains(r.getId()))
                .toList();
    }

    /**
     * Filtro público: SOLO ACTIVOS.
     */
    public List<RecursoEducativo> listarFiltradoActivos(Lenguaje lenguaje, Nivel nivel) {
        if (lenguaje != null && nivel != null) {
            return repo.findByActivoTrueAndLenguajeAndNivel(lenguaje, nivel);
        }
        if (lenguaje != null) {
            return repo.findByActivoTrueAndLenguaje(lenguaje);
        }
        if (nivel != null) {
            return repo.findByActivoTrueAndNivel(nivel);
        }
        return repo.findByActivoTrue();
    }

    // ============================
    // ADMIN helpers
    // ============================

    public List<RecursoEducativo> listarTodosAdmin() {
        return repo.findAll();
    }

    public RecursoEducativo buscarPorId(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public RecursoEducativo guardar(RecursoEducativo r) {
        return repo.save(r);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
