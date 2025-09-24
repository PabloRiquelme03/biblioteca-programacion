package cl.tuuni.biblioteca.service;

import cl.tuuni.biblioteca.entity.Usuario;
import cl.tuuni.biblioteca.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepo repo;
    private final BCryptPasswordEncoder encoder;

    /** Registro con encriptación BCrypt */
    public Usuario registrar(String email, String nombre, String rawPassword) {
        if (repo.findByEmail(email) != null) {
            throw new RuntimeException("Email ya registrado");
        }
        Usuario u = Usuario.builder()
                .email(email)
                .nombre(nombre)
                .password(encoder.encode(rawPassword))
                .habilitado(true)
                .roles(Set.of("ROLE_USER"))
                .build();
        return repo.save(u);
    }

    /** Carga de usuario para Spring Security (login) */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = repo.findByEmail(email);
        if (u == null || !u.isHabilitado()) {
            throw new UsernameNotFoundException("Usuario no existe o deshabilitado");
        }
        return new User(
                u.getEmail(),
                u.getPassword(),
                u.getRoles().stream().map(SimpleGrantedAuthority::new).toList()
        );
    }
}
