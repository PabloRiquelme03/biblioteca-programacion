package cl.tuuni.biblioteca.controller;

import cl.tuuni.biblioteca.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller @RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarios;

    @GetMapping("/auth/login")   public String login()         { return "auth/login"; }
    @GetMapping("/auth/registro") public String registroForm() { return "auth/registro"; }

    @PostMapping("/auth/registro")
    public String registrar(@RequestParam String email,
                            @RequestParam String nombre,
                            @RequestParam String password,
                            Model model) {
        try { usuarios.registrar(email, nombre, password);
            model.addAttribute("ok","Cuenta creada. Ahora puedes iniciar sesión."); return "auth/login";
        } catch (Exception e) { model.addAttribute("error", e.getMessage()); return "auth/registro"; }
    }
}
