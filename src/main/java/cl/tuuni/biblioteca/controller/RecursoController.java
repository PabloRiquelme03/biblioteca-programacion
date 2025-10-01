package cl.tuuni.biblioteca.controller;

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
    public String listar(@RequestParam(name = "nivel", required = false) Nivel nivel,
                         Model model,
                         Principal principal) {

        if (nivel == null) nivel = Nivel.BASICO;

        model.addAttribute("title", "Recursos - " + nivel);
        model.addAttribute("nivel", nivel.name());
        model.addAttribute("recursos", servicio.listarPorNivel(nivel));

        // IDs de recursos completados por el usuario autenticado
        model.addAttribute("completados", progresoService.idsCompletados(principal));

        return "recursos/lista";
    }
}
