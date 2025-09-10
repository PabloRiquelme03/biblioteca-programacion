package cl.tuuni.biblioteca.controller;

import cl.tuuni.biblioteca.entity.Nivel;
import cl.tuuni.biblioteca.entity.RecursoEducativo;
import cl.tuuni.biblioteca.service.RecursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecursoController {

    private final RecursoService servicio;

    @GetMapping("/recursos")
    public String listar(@RequestParam(defaultValue = "BASICO") Nivel nivel, Model model) {
        List<RecursoEducativo> recursos = servicio.listarPorNivel(nivel);
        model.addAttribute("nivel", nivel);
        model.addAttribute("recursos", recursos);
        model.addAttribute("title", "Recursos - " + nivel);
        return "recursos/lista";
    }
}
