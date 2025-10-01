package cl.tuuni.biblioteca.service;

import cl.tuuni.biblioteca.entity.Usuario;
import cl.tuuni.biblioteca.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepo repo;
    private final BCryptPasswordEncoder encoder;

    /** Registro con encriptación BCrypt */
    public Usuario registrar(String email, String nombre, String rawPassword) {
        if (repo.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
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

    /** Utilidad: existe email */
    public boolean existeEmail(String email) {
        return repo.findByEmail(email).isPresent();
    }

    /** Utilidad: buscar usuario (o null si no existe) */
    public Usuario buscarPorEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    /** Carga de usuario para Spring Security (login) */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no existe"));

        if (!u.isHabilitado()) {
            throw new UsernameNotFoundException("Usuario deshabilitado");
        }

        List<GrantedAuthority> auth = u.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(u.getEmail(), u.getPassword(), auth);
    }
}
