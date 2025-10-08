package cl.tuuni.biblioteca.controller;

import cl.tuuni.biblioteca.service.AnotacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AnotacionController {

    private final AnotacionService servicio;

    @GetMapping("/notas")
    public String lista(Model model, Principal p){
        model.addAttribute("title","Mis notas");
        model.addAttribute("notas", servicio.listar(p));
        return "notas/lista";
    }

    @PostMapping("/notas")
    public String crear(@RequestParam String titulo, @RequestParam String contenido, Principal p){
        servicio.guardar(p, titulo, contenido);
        return "redirect:/notas";
    }

    @PostMapping("/notas/{id}/eliminar")
    public String eliminar(@PathVariable Long id, Principal p){
        servicio.eliminar(p, id);
        return "redirect:/notas";
    }
}
