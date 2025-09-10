package cl.tuuni.biblioteca.config;

import cl.tuuni.biblioteca.entity.*;
import cl.tuuni.biblioteca.repo.RecursoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final RecursoRepo repo;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            if (repo.count() == 0) {
                repo.save(RecursoEducativo.builder()
                        .titulo("Guía Java básica")
                        .descripcion("Introducción a Java")
                        .url("https://docs.oracle.com/javase/tutorial/")
                        .tipo(TipoRecurso.GUIA)
                        .lenguaje(Lenguaje.JAVA)
                        .nivel(Nivel.BASICO)
                        .build());

                repo.save(RecursoEducativo.builder()
                        .titulo("Ejercicios de Python")
                        .descripcion("Colección de katas para practicar")
                        .url("https://www.codewars.com/")
                        .tipo(TipoRecurso.EJERCICIO)
                        .lenguaje(Lenguaje.PYTHON)
                        .nivel(Nivel.INTERMEDIO)
                        .build());
            }
        };
    }
}
