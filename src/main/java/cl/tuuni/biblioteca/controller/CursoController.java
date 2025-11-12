package cl.tuuni.biblioteca.controller;

import cl.tuuni.biblioteca.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

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
        ra.addFlashAttribute("ok", "Inscripción realizada correctamente.");
        return "redirect:/cursos?tab=inscritos";
    }

    @GetMapping("/cursos/{id}")
    public String detalle(@PathVariable Long id, Principal p, Model model){
        var curso = servicio.curso(id);
        var lecciones = servicio.lecciones(id);
        var completadas = servicio.idsLeccionesCompletadas(p, id);

        // --- NUEVO: construir mapa leccionId -> embedUrl (si es YouTube) ---
        Map<Long,String> embedUrls = new HashMap<>();
        for (var l : lecciones) {
            var url = l.getUrl();
            var embed = toYoutubeEmbed(url);
            if (embed != null) embedUrls.put(l.getId(), embed);
        }

        model.addAttribute("title", curso.getTitulo());
        model.addAttribute("curso", curso);
        model.addAttribute("cursoTitulo", curso.getTitulo());
        model.addAttribute("lecciones", lecciones);
        model.addAttribute("cursoId", id);
        model.addAttribute("inscripcion", servicio.inscripcion(p, id));
        model.addAttribute("completadas", completadas);
        model.addAttribute("pendientes", Math.max(lecciones.size() - completadas.size(), 0));
        model.addAttribute("embedUrls", embedUrls); // <-- NUEVO

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

    // ------------------ Helpers ------------------

    /** Si la URL es de YouTube válida, devuelve el embed URL; si no, null. */
    private String toYoutubeEmbed(String url) {
        if (url == null) return null;
        try {
            var u = URI.create(url.trim());
            var host = u.getHost() == null ? "" : u.getHost().toLowerCase();

            // youtu.be/<id>
            if (host.endsWith("youtu.be")) {
                var path = u.getPath(); // /<id>
                if (path != null && path.length() > 1) {
                    var id = path.substring(1);
                    return "https://www.youtube.com/embed/" + sanitizeId(id);
                }
            }

            // youtube.com/watch?v=<id> (&params)
            if (host.contains("youtube.com")) {
                var query = u.getQuery(); // v=ID&foo=bar
                if (query != null) {
                    for (String part : query.split("&")) {
                        var kv = part.split("=", 2);
                        if (kv.length == 2 && kv[0].equals("v")) {
                            var id = URLDecoder.decode(kv[1], StandardCharsets.UTF_8);
                            return "https://www.youtube.com/embed/" + sanitizeId(id);
                        }
                    }
                }
            }
        } catch (Exception ignored) { }
        return null;
    }

    /** Limpia el ID básico (quita fragmentos o parámetros residuales). */
    private String sanitizeId(String id) {
        var clean = id;
        int cut = clean.indexOf('&');
        if (cut >= 0) clean = clean.substring(0, cut);
        cut = clean.indexOf('?');
        if (cut >= 0) clean = clean.substring(0, cut);
        cut = clean.indexOf('#');
        if (cut >= 0) clean = clean.substring(0, cut);
        return clean;
    }
}
