package cl.tuuni.biblioteca.config;

import cl.tuuni.biblioteca.entity.Usuario;
import cl.tuuni.biblioteca.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration @RequiredArgsConstructor
public class UserSeed {
    private final BCryptPasswordEncoder encoder;

    @Bean
    CommandLineRunner addAdmin(UsuarioRepo repo) {
        return args -> {
            if (repo.findByEmail("admin@demo.cl") == null) {
                repo.save(Usuario.builder()
                        .email("admin@demo.cl")
                        .nombre("Admin")
                        .password(encoder.encode("admin123"))
                        .habilitado(true)
                        .roles(Set.of("ROLE_ADMIN","ROLE_USER"))
                        .build());
            }
        };
    }
}
