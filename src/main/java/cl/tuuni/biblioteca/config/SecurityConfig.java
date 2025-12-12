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
                        // ✅ Admin
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // recursos públicos
                        .requestMatchers(
                                "/", "/recursos",
                                "/css/**", "/js/**", "/imgs/**", "/videos/**",
                                "/auth/**",
                                "/error",
                                "/cursos", "/cursos/*"
                        ).permitAll()

                        // requiere login
                        .requestMatchers(
                                "/perfil/**",
                                "/progreso/**",
                                "/cursos/*/leccion/**",
                                "/cursos/*/inscribirme",
                                "/notas/**"
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
                .csrf(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
