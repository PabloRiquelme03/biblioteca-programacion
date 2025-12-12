package cl.tuuni.biblioteca.config;

import cl.tuuni.biblioteca.entity.Usuario;
import cl.tuuni.biblioteca.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class UserSeed {

    private final BCryptPasswordEncoder encoder;

    @Bean
    CommandLineRunner addAdmin(UsuarioRepo repo) {
        return args -> {
            // ✅ admin fijo para desarrollo
            String adminEmail = "admin@demo.cl";

            if (repo.findByEmail(adminEmail).isEmpty()) {
                repo.save(Usuario.builder()
                        .email(adminEmail)
                        .nombre("Admin")
                        .password(encoder.encode("admin123"))
                        .habilitado(true)
                        .roles(Set.of("ROLE_ADMIN", "ROLE_USER"))
                        .build());
            }
        };
    }
}
