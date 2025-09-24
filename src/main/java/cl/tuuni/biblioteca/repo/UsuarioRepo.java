package cl.tuuni.biblioteca.repo;

import cl.tuuni.biblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
    // metodo para buscar usuarios por email
    Usuario findByEmail(String email);
}
