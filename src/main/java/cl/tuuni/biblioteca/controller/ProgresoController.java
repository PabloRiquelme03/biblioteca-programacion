package cl.tuuni.biblioteca.controller;

import cl.tuuni.biblioteca.service.ProgresoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProgresoController {

    private final ProgresoService servicio;

    @PostMapping("/progreso/{recursoId}/completar")
    public String completar(@PathVariable Long recursoId,
                            @RequestParam(required = false) String back,
                            Principal principal) {
        servicio.completar(recursoId, principal);
        return "redirect:" + (back != null ? back : "/recursos");
    }

    // 👇 NUEVO
    @PostMapping("/progreso/{recursoId}/desmarcar")
    public String desmarcar(@PathVariable Long recursoId,
                            @RequestParam(required = false) String back,
                            Principal principal) {
        servicio.desmarcar(recursoId, principal);
        return "redirect:" + (back != null ? back : "/recursos");
    }

    @GetMapping("/perfil/progreso")
    public String miProgreso(Model model, Principal principal) {
        model.addAttribute("title", "Mi progreso");
        model.addAttribute("items", servicio.listarDelUsuario(principal));
        return "perfil/progreso";
    }
}
