package cl.tuuni.biblioteca.config;

import cl.tuuni.biblioteca.entity.*;
import cl.tuuni.biblioteca.repo.CursoRepo;
import cl.tuuni.biblioteca.repo.LeccionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CursoSeed {

    private final CursoRepo cursoRepo;
    private final LeccionRepo leccionRepo;

    @Bean
    CommandLineRunner seedCursos() {
        return args -> {
            if (cursoRepo.count() == 0) {
                // === CURSO 1: SQL Básico ===
                Curso sql = cursoRepo.save(Curso.builder()
                        .titulo("SQL Básico")
                        .descripcion("Aprende a consultar, filtrar y manipular datos en bases de datos relacionales.")
                        .nivel(Nivel.BASICO)
                        .lenguaje(Lenguaje.JAVA) // o crea un enum SQL si prefieres
                        .build());

                leccionRepo.save(Leccion.builder().curso(sql).titulo("Introducción al lenguaje SQL").url("https://www.w3schools.com/sql/sql_intro.asp").orden(1).build());
                leccionRepo.save(Leccion.builder().curso(sql).titulo("Consultas SELECT y WHERE").url("https://www.w3schools.com/sql/sql_select.asp").orden(2).build());
                leccionRepo.save(Leccion.builder().curso(sql).titulo("Ordenamiento y filtrado de datos").url("https://www.w3schools.com/sql/sql_orderby.asp").orden(3).build());
                leccionRepo.save(Leccion.builder().curso(sql).titulo("Funciones de agregación").url("https://www.w3schools.com/sql/sql_min_max.asp").orden(4).build());
                leccionRepo.save(Leccion.builder().curso(sql).titulo("Uso de JOINs").url("https://www.w3schools.com/sql/sql_join.asp").orden(5).build());

                // === CURSO 2: JavaScript Intermedio ===
                Curso js = cursoRepo.save(Curso.builder()
                        .titulo("JavaScript Intermedio")
                        .descripcion("Domina estructuras, funciones y manipulación del DOM para crear experiencias dinámicas.")
                        .nivel(Nivel.INTERMEDIO)
                        .lenguaje(Lenguaje.JAVASCRIPT)
                        .build());

                leccionRepo.save(Leccion.builder().curso(js).titulo("Estructuras de control").url("https://developer.mozilla.org/es/docs/Web/JavaScript/Guide/Control_flow_and_error_handling").orden(1).build());
                leccionRepo.save(Leccion.builder().curso(js).titulo("Funciones y callbacks").url("https://developer.mozilla.org/es/docs/Web/JavaScript/Guide/Functions").orden(2).build());
                leccionRepo.save(Leccion.builder().curso(js).titulo("Manipulación del DOM").url("https://developer.mozilla.org/es/docs/Web/API/Document_Object_Model/Introduction").orden(3).build());
                leccionRepo.save(Leccion.builder().curso(js).titulo("Eventos y escuchadores").url("https://developer.mozilla.org/es/docs/Learn/JavaScript/Building_blocks/Events").orden(4).build());
                leccionRepo.save(Leccion.builder().curso(js).titulo("Promesas y Async/Await").url("https://developer.mozilla.org/es/docs/Learn/JavaScript/Asynchronous/Promises").orden(5).build());

                // === CURSO 3: Python Avanzado ===
                Curso py = cursoRepo.save(Curso.builder()
                        .titulo("Python Avanzado")
                        .descripcion("Profundiza en POO, manejo de excepciones, decoradores y módulos avanzados.")
                        .nivel(Nivel.AVANZADO)
                        .lenguaje(Lenguaje.PYTHON)
                        .build());

                leccionRepo.save(Leccion.builder().curso(py).titulo("Programación orientada a objetos").url("https://docs.python.org/es/3/tutorial/classes.html").orden(1).build());
                leccionRepo.save(Leccion.builder().curso(py).titulo("Manejo de excepciones").url("https://docs.python.org/es/3/tutorial/errors.html").orden(2).build());
                leccionRepo.save(Leccion.builder().curso(py).titulo("Decoradores y funciones lambda").url("https://realpython.com/primer-on-python-decorators/").orden(3).build());
                leccionRepo.save(Leccion.builder().curso(py).titulo("Uso de módulos y paquetes").url("https://docs.python.org/es/3/tutorial/modules.html").orden(4).build());
                leccionRepo.save(Leccion.builder().curso(py).titulo("Gestión de archivos y contexto").url("https://realpython.com/read-write-files-python/").orden(5).build());

                System.out.println("✅ Semillas de cursos creadas correctamente.");
            }
        };
    }
}
