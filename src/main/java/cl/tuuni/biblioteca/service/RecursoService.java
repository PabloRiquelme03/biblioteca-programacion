package cl.tuuni.biblioteca.service;

import cl.tuuni.biblioteca.entity.Nivel;
import cl.tuuni.biblioteca.entity.RecursoEducativo;
import cl.tuuni.biblioteca.repo.RecursoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecursoService {
    private final RecursoRepo repo;

    public List<RecursoEducativo> listarPorNivel(Nivel nivel) {
        return repo.findByNivel(nivel);
    }
}
