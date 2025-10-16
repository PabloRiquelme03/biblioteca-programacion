package cl.tuuni.biblioteca.service;

import cl.tuuni.biblioteca.entity.EstadoProgreso;
import cl.tuuni.biblioteca.entity.Progreso;
import cl.tuuni.biblioteca.repo.ProgresoRepo;
import cl.tuuni.biblioteca.repo.RecursoRepo;
import cl.tuuni.biblioteca.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgresoService {

    private final ProgresoRepo progresoRepo;
    private final UsuarioRepo usuarioRepo;
    private final RecursoRepo recursoRepo;

    @Transactional
    public void completar(Long recursoId, Principal principal) {
        var usuario = usuarioRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
        var recurso = recursoRepo.findById(recursoId)
                .orElseThrow(() -> new IllegalArgumentException("Recurso no existe"));

        if (!progresoRepo.existsByUsuarioIdAndRecursoId(usuario.getId(), recurso.getId())) {
            var nuevo = Progreso.builder()
                    .usuario(usuario)
                    .recurso(recurso)
                    .estado(EstadoProgreso.COMPLETADO)
                    .build();
            progresoRepo.save(nuevo);
        }
    }

    // busca el progreso por email + recurso y lo borra si existe
    @Transactional
    public void desmarcar(Long recursoId, Principal principal) {
        progresoRepo.findByUsuarioEmailAndRecursoId(principal.getName(), recursoId)
                .ifPresent(progresoRepo::delete);
    }

    public List<Progreso> listarDelUsuario(Principal principal) {
        var usuario = usuarioRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
        return progresoRepo.findByUsuarioIdOrderByFechaDesc(usuario.getId());
    }

    public Set<Long> idsCompletados(Principal principal) {
        if (principal == null) return Set.of();
        var usuario = usuarioRepo.findByEmail(principal.getName()).orElse(null);
        if (usuario == null) return Set.of();
        return progresoRepo.findByUsuarioIdOrderByFechaDesc(usuario.getId())
                .stream().map(p -> p.getRecurso().getId())
                .collect(Collectors.toSet());
    }
}
