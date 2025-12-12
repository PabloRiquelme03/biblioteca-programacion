package cl.tuuni.biblioteca.controller;

import cl.tuuni.biblioteca.entity.Lenguaje;
import cl.tuuni.biblioteca.entity.Nivel;
import cl.tuuni.biblioteca.service.ProgresoService;
import cl.tuuni.biblioteca.service.RecursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class RecursoController {

    private final RecursoService servicio;
    private final ProgresoService progresoService;

    @GetMapping("/recursos")
    public String listar(
            @RequestParam(name = "lenguaje", required = false) String lenguajeParam,
            @RequestParam(name = "nivel", required = false) String nivelParam,
            Principal principal,
            Model model) {

        Lenguaje lenguaje = null;
        if (lenguajeParam != null && !lenguajeParam.isBlank()) {
            try { lenguaje = Lenguaje.valueOf(lenguajeParam.toUpperCase()); }
            catch (Exception ignored) { lenguaje = null; }
        }

        Nivel nivel = null;
        if (nivelParam != null && !nivelParam.isBlank()) {
            try { nivel = Nivel.valueOf(nivelParam.toUpperCase()); }
            catch (Exception ignored) { nivel = null; }
        }

        model.addAttribute("title", "Recursos");
        model.addAttribute("lenguajes", Lenguaje.values());
        model.addAttribute("niveles", Nivel.values());
        model.addAttribute("selectedLenguaje", lenguaje);
        model.addAttribute("selectedNivel", nivel);

        // ✅ lista pública sin completados y solo activos
        model.addAttribute("recursos",
                servicio.listarFiltradoExcluyendoCompletados(lenguaje, nivel, principal));

        // para pintar botones marcar/desmarcar si los mostraras
        model.addAttribute("completadosIds", progresoService.idsCompletados(principal));

        return "recursos/lista";
    }
}
