package cl.tuuni.biblioteca.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // público
                        .requestMatchers(
                                "/", "/recursos",
                                "/css/**", "/js/**", "/imgs/**",
                                "/auth/**",        // login, registro, forgot, reset
                                "/error",
                                "/cursos", "/cursos/*"  // listar y ver curso son públicos
                        ).permitAll()

                        // requiere login
                        .requestMatchers(
                                "/perfil/**",
                                "/progreso/**",
                                "/cursos/*/leccion/**", // marcar/desmarcar
                                "/cursos/*/inscribirme",
                                "/notas/**"             // cuaderno de anotaciones
                        ).authenticated()

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/auth/login").permitAll()
                        .loginProcessingUrl("/auth/login")
                        .failureUrl("/auth/login?error")
                        .defaultSuccessUrl("/", true)
                )

                .logout(l -> l.logoutUrl("/logout").logoutSuccessUrl("/"))

                // IMPORTANTE: dejamos CSRF habilitado (por defecto) y tus formularios ya envían _csrf
                .csrf(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
