package cl.tuuni.biblioteca.service;

import cl.tuuni.biblioteca.entity.*;
import cl.tuuni.biblioteca.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepo cursoRepo;
    private final LeccionRepo leccionRepo;
    private final InscripcionRepo inscripcionRepo;
    private final ProgresoLeccionRepo progresoLeccionRepo;
    private final UsuarioRepo usuarioRepo;

    /* -------- Listados -------- */

    public List<Curso> listarTodos() {
        return cursoRepo.findAll();
    }

    public List<Curso> listarInscritos(Principal p) {
        if (p == null) return List.of();
        return inscripcionRepo.findByUsuarioEmail(p.getName())
                .stream()
                .map(Inscripcion::getCurso)
                .toList();
    }

    public List<Curso> listarDisponibles(Principal p) {
        var todos = listarTodos();
        if (p == null) return todos;
        var inscritosIds = listarInscritos(p).stream()
                .map(Curso::getId)
                .collect(Collectors.toSet());
        return todos.stream()
                .filter(c -> !inscritosIds.contains(c.getId()))
                .toList();
    }

    public List<Leccion> lecciones(Long cursoId) {
        return leccionRepo.findByCursoIdOrderByOrdenAsc(cursoId);
    }

    /* -------- Inscripción -------- */

    public Inscripcion inscripcion(Principal p, Long cursoId) {
        if (p == null) return null;
        return inscripcionRepo.findByUsuarioEmailAndCursoId(p.getName(), cursoId).orElse(null);
    }

    @Transactional
    public void inscribirme(Principal p, Long cursoId) {
        if (p == null) return;
        var existente = inscripcion(p, cursoId);
        if (existente != null) return;

        var curso = cursoRepo.findById(cursoId).orElseThrow();
        var usuario = usuarioRepo.findByEmail(p.getName())
                .orElseThrow();

        var insc = Inscripcion.builder()
                .usuario(usuario)
                .curso(curso)
                .finalizado(false)
                .build();

        inscripcionRepo.save(insc);
    }

    @Transactional
    public void abandonar(Principal p, Long cursoId) {
        if (p == null) return;
        var insc = inscripcionRepo.findByUsuarioEmailAndCursoId(p.getName(), cursoId).orElse(null);
        if (insc == null) return;

        progresoLeccionRepo.deleteByInscripcionId(insc.getId());
        inscripcionRepo.delete(insc);
    }

    /* -------- Progreso de lecciones -------- */

    @Transactional
    public void marcarLeccion(Principal p, Long cursoId, Long leccionId) {
        var insc = inscripcion(p, cursoId);
        if (insc == null) return;

        progresoLeccionRepo.findByInscripcionIdAndLeccionId(insc.getId(), leccionId)
                .or(() -> {
                    var pl = ProgresoLeccion.builder()
                            .inscripcion(insc)
                            .leccion(Leccion.builder().id(leccionId).build())
                            .build();
                    return Optional.of(progresoLeccionRepo.save(pl));
                });

        actualizarFinalizadoSiCorresponde(insc.getId(), cursoId);
    }

    @Transactional
    public void desmarcarLeccion(Principal p, Long cursoId, Long leccionId) {
        var insc = inscripcion(p, cursoId);
        if (insc == null) return;

        progresoLeccionRepo.findByInscripcionIdAndLeccionId(insc.getId(), leccionId)
                .ifPresent(progresoLeccionRepo::delete);

        actualizarFinalizadoSiCorresponde(insc.getId(), cursoId);
    }

    public Set<Long> idsLeccionesCompletadas(Principal p, Long cursoId) {
        var insc = inscripcion(p, cursoId);
        if (insc == null) return Set.of();

        return progresoLeccionRepo.findByInscripcionId(insc.getId()).stream()
                .map(pl -> pl.getLeccion().getId())
                .collect(Collectors.toSet());
    }

    public Curso curso(Long id) {
        return cursoRepo.findById(id).orElseThrow();
    }

    /* -------- Auxiliares -------- */

    private void actualizarFinalizadoSiCorresponde(Long inscripcionId, Long cursoId) {
        int total = leccionRepo.findByCursoIdOrderByOrdenAsc(cursoId).size();
        long hechos = progresoLeccionRepo.countByInscripcionId(inscripcionId);

        var insc = inscripcionRepo.findById(inscripcionId).orElseThrow();
        insc.setFinalizado(total > 0 && hechos >= total);
        inscripcionRepo.save(insc);
    }
}
