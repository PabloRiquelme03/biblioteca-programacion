package cl.tuuni.biblioteca.service;

import cl.tuuni.biblioteca.entity.PasswordResetToken;
import cl.tuuni.biblioteca.repo.PasswordResetTokenRepo;
import cl.tuuni.biblioteca.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UsuarioRepo usuarioRepo;
    private final PasswordResetTokenRepo tokenRepo;
    private final BCryptPasswordEncoder encoder;

    /** Inicia el proceso y devuelve el token (o null si el correo no existe). */
    public String iniciar(String email){
        var u = usuarioRepo.findByEmail(email).orElse(null);
        if (u == null) return null;

        var token = UUID.randomUUID().toString();
        tokenRepo.save(PasswordResetToken.builder()
                .usuario(u)
                .token(token)
                .expira(LocalDateTime.now().plusHours(2))
                .usado(false)
                .build());
        return token;
    }

    /** Aplica el reset a partir del token. */
    public boolean resetear(String token, String nuevaClave){
        var t = tokenRepo.findByToken(token).orElse(null);
        if (t == null || t.isUsado() || t.getExpira().isBefore(LocalDateTime.now())) return false;

        var u = t.getUsuario();
        u.setPassword(encoder.encode(nuevaClave));
        usuarioRepo.save(u);

        t.setUsado(true);
        tokenRepo.save(t);
        return true;
    }
}
