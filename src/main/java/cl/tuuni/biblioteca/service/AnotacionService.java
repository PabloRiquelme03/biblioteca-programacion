package cl.tuuni.biblioteca.service;

import cl.tuuni.biblioteca.entity.Anotacion;
import cl.tuuni.biblioteca.repo.AnotacionRepo;
import cl.tuuni.biblioteca.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnotacionService {

    private final AnotacionRepo repo;
    private final UsuarioRepo usuarioRepo;

    public List<Anotacion> listar(Principal p){
        var u = usuarioRepo.findByEmail(p.getName()).orElseThrow();
        return repo.findByUsuarioIdOrderByActualizadoDesc(u.getId());
    }

    public Anotacion guardar(Principal p, String titulo, String contenido){
        var u = usuarioRepo.findByEmail(p.getName()).orElseThrow();
        var a = Anotacion.builder().usuario(u).titulo(titulo).contenido(contenido).build();
        return repo.save(a);
    }

    public void eliminar(Principal p, Long id){
        // (si quisieras, valida propiedad)
        repo.deleteById(id);
    }
}
