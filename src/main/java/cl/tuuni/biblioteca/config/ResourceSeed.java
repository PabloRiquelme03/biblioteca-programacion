package cl.tuuni.biblioteca.config;

import cl.tuuni.biblioteca.entity.Lenguaje;
import cl.tuuni.biblioteca.entity.Nivel;
import cl.tuuni.biblioteca.entity.RecursoEducativo;
import cl.tuuni.biblioteca.entity.TipoRecurso;
import cl.tuuni.biblioteca.repo.RecursoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ResourceSeed {

    private final RecursoRepo repo;

    @Bean
    CommandLineRunner seedResources() {
        return args -> {

            List<RecursoEducativo> all = new ArrayList<>();

            // =====================================================
            // JAVA - BÁSICO
            // =====================================================
            all.add(build("Java Básico: Sintaxis y tipos",
                    "Variables, tipos primitivos, operadores y conversiones.",
                    "", TipoRecurso.GUIA, Lenguaje.JAVA, Nivel.BASICO));

            all.add(build("Java Básico: Control de flujo",
                    "Condicionales (if/switch) y bucles (for/while/do-while).",
                    "", TipoRecurso.GUIA, Lenguaje.JAVA, Nivel.BASICO));

            all.add(build("Java Básico: Métodos y funciones",
                    "Parámetros, retorno, sobrecarga y buenas prácticas.",
                    "", TipoRecurso.GUIA, Lenguaje.JAVA, Nivel.BASICO));

            all.add(build("Java Básico: Arreglos y colecciones intro",
                    "Arreglos, ArrayList y conceptos base de colecciones.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVA, Nivel.BASICO));

            all.add(build("Java Básico: Entrada/Salida",
                    "Lectura/escritura simple de archivos y consola.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVA, Nivel.BASICO));

            // =====================================================
            // JAVA - INTERMEDIO
            // =====================================================
            all.add(build("Java Intermedio: POO avanzada",
                    "Encapsulación, herencia, polimorfismo e interfaces.",
                    "", TipoRecurso.GUIA, Lenguaje.JAVA, Nivel.INTERMEDIO));

            all.add(build("Java Intermedio: Excepciones",
                    "try/catch, excepciones personalizadas y control de errores.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVA, Nivel.INTERMEDIO));

            all.add(build("Java Intermedio: Colecciones (List/Set/Map)",
                    "Uso correcto de List, Set y Map en casos reales.",
                    "", TipoRecurso.GUIA, Lenguaje.JAVA, Nivel.INTERMEDIO));

            all.add(build("Java Intermedio: Streams y Lambdas",
                    "Programación funcional básica en Java.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVA, Nivel.INTERMEDIO));

            all.add(build("Java Intermedio: JDBC básico",
                    "Conexión y consultas a BD con JDBC.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVA, Nivel.INTERMEDIO));

            // =====================================================
            // JAVA - AVANZADO
            // =====================================================
            all.add(build("Java Avanzado: Concurrencia",
                    "Threads, Executors y sincronización básica.",
                    "", TipoRecurso.GUIA, Lenguaje.JAVA, Nivel.AVANZADO));

            all.add(build("Java Avanzado: Generics a fondo",
                    "Wildcards, bounds y casos típicos con genéricos.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVA, Nivel.AVANZADO));

            all.add(build("Java Avanzado: Performance y profiling",
                    "Buenas prácticas y herramientas básicas de diagnóstico.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVA, Nivel.AVANZADO));

            all.add(build("Java Avanzado: Seguridad en aplicaciones",
                    "Buenas prácticas generales (hashing, validación, etc.).",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVA, Nivel.AVANZADO));

            all.add(build("Java Avanzado: Arquitectura y patrones",
                    "Patrones comunes y arquitectura por capas.",
                    "", TipoRecurso.GUIA, Lenguaje.JAVA, Nivel.AVANZADO));

            // =====================================================
            // JAVASCRIPT - BÁSICO
            // =====================================================
            all.add(build("JavaScript Básico: Variables y tipos",
                    "let/const, tipos primitivos y coerción.",
                    "", TipoRecurso.GUIA, Lenguaje.JAVASCRIPT, Nivel.BASICO));

            all.add(build("JavaScript Básico: Funciones",
                    "Funciones, arrow functions y scope básico.",
                    "", TipoRecurso.GUIA, Lenguaje.JAVASCRIPT, Nivel.BASICO));

            all.add(build("JavaScript Básico: DOM intro",
                    "Seleccionar y modificar elementos del DOM.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVASCRIPT, Nivel.BASICO));

            all.add(build("JavaScript Básico: Eventos",
                    "addEventListener, eventos comunes y validaciones.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVASCRIPT, Nivel.BASICO));

            all.add(build("JavaScript Básico: Fetch intro",
                    "Peticiones HTTP simples y JSON.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVASCRIPT, Nivel.BASICO));

            // =====================================================
            // JAVASCRIPT - INTERMEDIO
            // =====================================================
            all.add(build("JavaScript Intermedio: Arrays y objetos",
                    "Map/filter/reduce y manipulación de objetos.",
                    "", TipoRecurso.GUIA, Lenguaje.JAVASCRIPT, Nivel.INTERMEDIO));

            all.add(build("JavaScript Intermedio: Promises y async/await",
                    "Asincronía, manejo de errores y patrones comunes.",
                    "", TipoRecurso.GUIA, Lenguaje.JAVASCRIPT, Nivel.INTERMEDIO));

            all.add(build("JavaScript Intermedio: Módulos",
                    "import/export y organización de código.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVASCRIPT, Nivel.INTERMEDIO));

            all.add(build("JavaScript Intermedio: ES6+ features",
                    "Destructuring, spread, template literals, etc.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVASCRIPT, Nivel.INTERMEDIO));

            all.add(build("JavaScript Intermedio: Testing (conceptos)",
                    "Qué es test unitario y cómo se aplica en front.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVASCRIPT, Nivel.INTERMEDIO));

            // =====================================================
            // JAVASCRIPT - AVANZADO
            // =====================================================
            all.add(build("JavaScript Avanzado: Performance",
                    "Optimización, rendering, lazy loading y profiling básico.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVASCRIPT, Nivel.AVANZADO));

            all.add(build("JavaScript Avanzado: Patrones",
                    "Módulos, observer, pub/sub, factory (visión práctica).",
                    "", TipoRecurso.GUIA, Lenguaje.JAVASCRIPT, Nivel.AVANZADO));

            all.add(build("JavaScript Avanzado: Seguridad en front",
                    "XSS, CSRF y buenas prácticas para mitigación.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVASCRIPT, Nivel.AVANZADO));

            all.add(build("JavaScript Avanzado: Accesibilidad (A11y)",
                    "Buenas prácticas para UI accesible.",
                    "", TipoRecurso.ARTICULO, Lenguaje.JAVASCRIPT, Nivel.AVANZADO));

            all.add(build("JavaScript Avanzado: Tooling",
                    "Bundlers, linters y estructura de proyectos.",
                    "", TipoRecurso.GUIA, Lenguaje.JAVASCRIPT, Nivel.AVANZADO));

            // =====================================================
            // PYTHON - BÁSICO
            // =====================================================
            all.add(build("Python Básico: Sintaxis y tipos",
                    "Variables, tipos básicos y operaciones comunes.",
                    "", TipoRecurso.GUIA, Lenguaje.PYTHON, Nivel.BASICO));

            all.add(build("Python Básico: Control de flujo",
                    "if, for, while y comprensión de listas (intro).",
                    "", TipoRecurso.GUIA, Lenguaje.PYTHON, Nivel.BASICO));

            all.add(build("Python Básico: Funciones",
                    "Parámetros, return y buenas prácticas.",
                    "", TipoRecurso.ARTICULO, Lenguaje.PYTHON, Nivel.BASICO));

            all.add(build("Python Básico: Estructuras (list/dict/set)",
                    "Colecciones principales y casos típicos.",
                    "", TipoRecurso.GUIA, Lenguaje.PYTHON, Nivel.BASICO));

            all.add(build("Python Básico: Lectura/Escritura simple",
                    "Archivos básicos y manejo simple de errores.",
                    "", TipoRecurso.ARTICULO, Lenguaje.PYTHON, Nivel.BASICO));

            // =====================================================
            // PYTHON - INTERMEDIO
            // =====================================================
            all.add(build("Python Intermedio: Manejo de archivos",
                    "Lectura/escritura segura y rutas.",
                    "", TipoRecurso.GUIA, Lenguaje.PYTHON, Nivel.INTERMEDIO));

            all.add(build("Python Intermedio: Módulos y paquetes",
                    "Imports, estructura de proyecto y paquetes.",
                    "", TipoRecurso.ARTICULO, Lenguaje.PYTHON, Nivel.INTERMEDIO));

            all.add(build("Python Intermedio: Entornos virtuales y pip",
                    "venv, pip, requirements.txt.",
                    "", TipoRecurso.GUIA, Lenguaje.PYTHON, Nivel.INTERMEDIO));

            all.add(build("Python Intermedio: Excepciones",
                    "try/except, raise y manejo robusto.",
                    "", TipoRecurso.ARTICULO, Lenguaje.PYTHON, Nivel.INTERMEDIO));

            all.add(build("Python Intermedio: Librerías externas (intro)",
                    "Cómo elegir e integrar librerías de terceros.",
                    "", TipoRecurso.ARTICULO, Lenguaje.PYTHON, Nivel.INTERMEDIO));

            // =====================================================
            // PYTHON - AVANZADO
            // =====================================================
            all.add(build("Python Avanzado: POO (clases y herencia)",
                    "Clases, herencia, composición y buenas prácticas.",
                    "", TipoRecurso.GUIA, Lenguaje.PYTHON, Nivel.AVANZADO));

            all.add(build("Python Avanzado: Decoradores",
                    "Decorators y funciones de orden superior.",
                    "", TipoRecurso.GUIA, Lenguaje.PYTHON, Nivel.AVANZADO));

            all.add(build("Python Avanzado: Context managers",
                    "with, __enter__/__exit__ y uso profesional.",
                    "", TipoRecurso.ARTICULO, Lenguaje.PYTHON, Nivel.AVANZADO));

            all.add(build("Python Avanzado: Tipado (type hints)",
                    "typing, mypy (conceptos) y mejora de calidad.",
                    "", TipoRecurso.ARTICULO, Lenguaje.PYTHON, Nivel.AVANZADO));

            all.add(build("Python Avanzado: Arquitectura de proyecto",
                    "Estructura profesional, módulos, paquetes y tests (concepto).",
                    "", TipoRecurso.GUIA, Lenguaje.PYTHON, Nivel.AVANZADO));

            // Insertar solo los que no existen
            List<RecursoEducativo> nuevos = all.stream()
                    .filter(r -> !repo.existsByTitulo(r.getTitulo()))
                    .toList();

            if (!nuevos.isEmpty()) {
                repo.saveAll(nuevos);
                System.out.println("✅ Recursos sembrados: " + nuevos.size());
            } else {
                System.out.println("ℹ️ No se sembraron recursos (ya existían).");
            }
        };
    }

    private RecursoEducativo build(String titulo, String desc, String url,
                                   TipoRecurso tipo, Lenguaje len, Nivel niv) {
        return RecursoEducativo.builder()
                .titulo(titulo)
                .descripcion(desc)
                .url(url == null ? "" : url.trim())
                .tipo(tipo)
                .lenguaje(len)
                .nivel(niv)
                .activo(true)
                .build();
    }
}
