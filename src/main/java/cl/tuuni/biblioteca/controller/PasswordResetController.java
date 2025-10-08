package cl.tuuni.biblioteca.controller;

import cl.tuuni.biblioteca.service.MailService;
import cl.tuuni.biblioteca.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService servicio;
    private final MailService mail;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    @GetMapping("/auth/forgot")
    public String forgotForm(Model model){
        model.addAttribute("title","Recuperar contraseña");
        return "auth/forgot";
    }

    @PostMapping("/auth/forgot")
    public String forgot(@RequestParam String email, Model model){
        var token = servicio.iniciar(email);

        // Mensaje neutro para no filtrar existencia del correo
        model.addAttribute("ok", "Si el correo existe, te enviaremos instrucciones.");

        if (token != null) {
            String link = baseUrl + "/auth/reset?token=" + token;
            String body = "Solicitaste recuperar tu contraseña.\n\n" +
                    "Enlace (válido por 2 horas):\n" + link + "\n\n" +
                    "Si no fuiste tú, ignora este mensaje.";
            mail.sendSimple(email, "Recuperación de contraseña", body);
        }
        return "auth/forgot";
    }

    @GetMapping("/auth/reset")
    public String resetForm(@RequestParam String token, Model model){
        model.addAttribute("title","Nueva contraseña");
        model.addAttribute("token", token);
        return "auth/reset";
    }

    @PostMapping("/auth/reset")
    public String reset(@RequestParam String token,
                        @RequestParam String password,
                        Model model){
        boolean ok = servicio.resetear(token, password);
        if (ok) return "redirect:/auth/login?ok=Contraseña actualizada";
        model.addAttribute("error", "Token inválido o expirado");
        model.addAttribute("token", token);
        return "auth/reset";
    }
}
