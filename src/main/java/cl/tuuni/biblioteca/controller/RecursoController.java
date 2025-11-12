package cl.tuuni.biblioteca.controller;

import cl.tuuni.biblioteca.entity.Lenguaje;
import cl.tuuni.biblioteca.entity.Nivel;
import cl.tuuni.biblioteca.service.RecursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RecursoController {

    private final RecursoService servicio;

    @GetMapping("/recursos")
    public String listar(
            @RequestParam(name = "lenguaje", required = false) String lenguajeParam,
            @RequestParam(name = "nivel", required = false) String nivelParam,
            Model model) {

        // Parseo seguro de enums (evita 500 si viene algo raro)
        Lenguaje lenguaje = null;
        if (lenguajeParam != null && !lenguajeParam.isBlank()) {
            try {
                lenguaje = Lenguaje.valueOf(lenguajeParam.toUpperCase());
            } catch (IllegalArgumentException ignored) {
                lenguaje = null;
            }
        }

        Nivel nivel = null;
        if (nivelParam != null && !nivelParam.isBlank()) {
            try {
                nivel = Nivel.valueOf(nivelParam.toUpperCase());
            } catch (IllegalArgumentException ignored) {
                nivel = null;
            }
        }

        model.addAttribute("title", "Recursos");

        // Para los selects
        model.addAttribute("lenguajes", Lenguaje.values());
        model.addAttribute("niveles", Nivel.values());
        model.addAttribute("selectedLenguaje", lenguaje);
        model.addAttribute("selectedNivel", nivel);

        // Lista filtrada
        model.addAttribute("recursos", servicio.listarFiltrado(lenguaje, nivel));

        return "recursos/lista";
    }
}
