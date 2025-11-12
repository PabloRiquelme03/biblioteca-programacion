package cl.tuuni.biblioteca.service;

import cl.tuuni.biblioteca.entity.Lenguaje;
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

    /**
     * Devuelve recursos filtrando opcionalmente por lenguaje y nivel.
     * Si ambos son null -> todos.
     */
    public List<RecursoEducativo> listarFiltrado(Lenguaje lenguaje, Nivel nivel) {

        if (lenguaje != null && nivel != null) {
            return repo.findByLenguajeAndNivel(lenguaje, nivel);
        }
        if (lenguaje != null) {
            return repo.findByLenguaje(lenguaje);
        }
        if (nivel != null) {
            return repo.findByNivel(nivel);
        }
        return repo.findAll();
    }
}
