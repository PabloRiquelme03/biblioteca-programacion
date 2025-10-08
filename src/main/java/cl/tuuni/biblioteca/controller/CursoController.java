package cl.tuuni.biblioteca.controller;

import cl.tuuni.biblioteca.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CursoController {

    private final CursoService servicio;

    @GetMapping("/cursos")
    public String listar(@RequestParam(name = "tab", defaultValue = "disponibles") String tab,
                         Principal p,
                         Model model){
        model.addAttribute("title", "Cursos");
        model.addAttribute("tab", tab);
        model.addAttribute("inscritos", servicio.listarInscritos(p));
        model.addAttribute("disponibles", servicio.listarDisponibles(p));
        return "cursos/lista";
    }

    @PostMapping("/cursos/{id}/inscribirme")
    public String inscribir(@PathVariable Long id, Principal p, RedirectAttributes ra){
        servicio.inscribirme(p, id);
        ra.addFlashAttribute("ok", "¡Inscripción realizada correctamente!");
        return "redirect:/cursos?tab=inscritos";
    }

    @PostMapping("/cursos/{id}/abandonar")
    public String abandonar(@PathVariable Long id, Principal p, RedirectAttributes ra){
        servicio.abandonar(p, id); // asumiendo que ya lo tienes en tu service
        ra.addFlashAttribute("info", "Has abandonado el curso.");
        return "redirect:/cursos?tab=disponibles";
    }

    @GetMapping("/cursos/{id}")
    public String detalle(@PathVariable Long id, Principal p, Model model){
        var curso = servicio.curso(id);
        var lecciones = servicio.lecciones(id);
        var completadas = servicio.idsLeccionesCompletadas(p, id);

        model.addAttribute("title", curso.getTitulo());
        model.addAttribute("curso", curso);
        model.addAttribute("cursoTitulo", curso.getTitulo());
        model.addAttribute("lecciones", lecciones);
        model.addAttribute("cursoId", id);
        model.addAttribute("inscripcion", servicio.inscripcion(p, id));
        model.addAttribute("completadas", completadas);
        model.addAttribute("pendientes", Math.max(lecciones.size() - completadas.size(), 0));

        return "cursos/detalle";
    }

    @PostMapping("/cursos/{cursoId}/leccion/{leccionId}/completar")
    public String completar(@PathVariable Long cursoId, @PathVariable Long leccionId, Principal p){
        servicio.marcarLeccion(p, cursoId, leccionId);
        return "redirect:/cursos/"+cursoId;
    }

    @PostMapping("/cursos/{cursoId}/leccion/{leccionId}/desmarcar")
    public String desmarcar(@PathVariable Long cursoId, @PathVariable Long leccionId, Principal p){
        servicio.desmarcarLeccion(p, cursoId, leccionId);
        return "redirect:/cursos/"+cursoId;
    }
}
