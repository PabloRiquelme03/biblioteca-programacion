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
     * Lista recursos, filtrados por lenguaje/nivel,
     * PERO excluyendo los que el usuario ya completó.
     */
    public List<RecursoEducativo> listarFiltradoExcluyendoCompletados(
            Lenguaje lenguaje,
            Nivel nivel,
            Principal principal
    ) {
        // obtener todos los recursos filtrados
        List<RecursoEducativo> base = listarFiltrado(lenguaje, nivel);

        if (principal == null) return base;

        var usuario = usuarioRepo.findByEmail(principal.getName()).orElse(null);
        if (usuario == null) return base;

        // obtener ids completados
        Set<Long> completadosIds = progresoRepo.findByUsuarioIdOrderByFechaDesc(usuario.getId())
                .stream()
                .map(p -> p.getRecurso().getId())
                .collect(Collectors.toSet());

        // excluirlos
        return base.stream()
                .filter(r -> !completadosIds.contains(r.getId()))
                .toList();
    }

    /**
     * Filtro original sin excluir nada.
     */
    public List<RecursoEducativo> listarFiltrado(Lenguaje lenguaje, Nivel nivel) {
        if (lenguaje != null && nivel != null) {
            return repo.findByLenguajeAndNivel(lenguaje, nivel);
        }
        if (lenguaje != null) {
            return repo.findByLenguaje(lenguaje);
        }
        if (nivel != null) {
            return repo.findByNivel(nivel);
        }
        return repo.findAll();
    }
}
