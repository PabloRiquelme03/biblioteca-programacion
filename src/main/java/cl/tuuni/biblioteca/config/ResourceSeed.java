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
            // Construye la lista completa
            List<RecursoEducativo> all = new ArrayList<>();

            // ====== JAVA ======
            String[] titJavaBas = {
                    "Java Básico: Sintaxis y tipos",
                    "Java Básico: Control de flujo",
                    "Java Básico: Métodos y clases",
                    "Java Básico: Colecciones intro",
                    "Java Básico: Entrada/Salida"
            };
            String[] titJavaInt = {
                    "Java Intermedio: OOP avanzada",
                    "Java Intermedio: Excepciones",
                    "Java Intermedio: Streams",
                    "Java Intermedio: JDBC básico",
                    "Java Intermedio: Testing con JUnit"
            };
            String[] titJavaAdv = {
                    "Java Avanzado: Concurrencia",
                    "Java Avanzado: Streams paralelos",
                    "Java Avanzado: Generics a fondo",
                    "Java Avanzado: Seguridad",
                    "Java Avanzado: Performance Tuning"
            };

            for (String t : titJavaBas)
                all.add(build(t, "Introducción a Java", "https://docs.oracle.com/javase/tutorial/",
                        TipoRecurso.GUIA, Lenguaje.JAVA, Nivel.BASICO));
            for (String t : titJavaInt)
                all.add(build(t, "Java intermedio", "https://developer.oracle.com/",
                        TipoRecurso.ARTICULO, Lenguaje.JAVA, Nivel.INTERMEDIO));
            for (String t : titJavaAdv)
                all.add(build(t, "Java avanzado", "https://docs.oracle.com/",
                        TipoRecurso.GUIA, Lenguaje.JAVA, Nivel.AVANZADO));

            // ====== JAVASCRIPT ======
            String[] titJsBas = {
                    "JS Básico: Variables y operadores",
                    "JS Básico: Funciones",
                    "JS Básico: DOM intro",
                    "JS Básico: Eventos",
                    "JS Básico: Fetch intro"
            };
            String[] titJsInt = {
                    "JS Intermedio: Arrays y objetos",
                    "JS Intermedio: Promesas y Async/Await",
                    "JS Intermedio: Módulos",
                    "JS Intermedio: ES6+ features",
                    "JS Intermedio: Pruebas con Jest (conceptos)"
            };
            String[] titJsAdv = {
                    "JS Avanzado: Performance",
                    "JS Avanzado: Patrones",
                    "JS Avanzado: Seguridad en front",
                    "JS Avanzado: Accesibilidad (A11y)",
                    "JS Avanzado: Herramientas build"
            };

            for (String t : titJsBas)
                all.add(build(t, "Intro a JavaScript (MDN)", "https://developer.mozilla.org/es/docs/Web/JavaScript",
                        TipoRecurso.GUIA, Lenguaje.JAVASCRIPT, Nivel.BASICO));
            for (String t : titJsInt)
                all.add(build(t, "JavaScript intermedio (MDN)", "https://developer.mozilla.org/es/docs/Web/JavaScript",
                        TipoRecurso.ARTICULO, Lenguaje.JAVASCRIPT, Nivel.INTERMEDIO));
            for (String t : titJsAdv)
                all.add(build(t, "JavaScript avanzado (MDN)", "https://developer.mozilla.org/es/docs/Web/JavaScript",
                        TipoRecurso.GUIA, Lenguaje.JAVASCRIPT, Nivel.AVANZADO));

            // ====== HTML/CSS ======
            // Si prefieres separarlo, agrega HTML_CSS en tu enum; aquí usamos JAVASCRIPT como categoría genérica.
            String[] titHtmlCssBas = {
                    "HTML5 Básico: Estructura",
                    "HTML5/CSS3: Selectores y caja",
                    "HTML5/CSS3: Flexbox",
                    "HTML5/CSS3: Grid",
                    "HTML5/CSS3: Responsive"
            };
            String[] titHtmlCssInt = {
                    "HTML5: Semántica avanzada",
                    "CSS3: Animaciones",
                    "CSS3: Variables y BEM",
                    "Accesibilidad web (WCAG) intro",
                    "Buenas prácticas UI"
            };
            String[] titHtmlCssAdv = {
                    "SEO técnico para devs",
                    "Rendimiento web (Core Web Vitals)",
                    "Accesibilidad (WCAG) avanzada",
                    "Arquitectura CSS a escala",
                    "Testing UI (conceptos)"
            };

            for (String t : titHtmlCssBas)
                all.add(build(t, "HTML/CSS básico (MDN)", "https://developer.mozilla.org/es/docs/Learn",
                        TipoRecurso.GUIA, Lenguaje.JAVASCRIPT, Nivel.BASICO));
            for (String t : titHtmlCssInt)
                all.add(build(t, "HTML/CSS intermedio (MDN)", "https://developer.mozilla.org/es/docs/Learn",
                        TipoRecurso.ARTICULO, Lenguaje.JAVASCRIPT, Nivel.INTERMEDIO));
            for (String t : titHtmlCssAdv)
                all.add(build(t, "HTML/CSS avanzado (web.dev)", "https://web.dev/",
                        TipoRecurso.ARTICULO, Lenguaje.JAVASCRIPT, Nivel.AVANZADO));

            // ====== Inserta sólo los que faltan (por título) ======
            List<RecursoEducativo> nuevos = all.stream()
                    .filter(r -> !repo.existsByTitulo(r.getTitulo()))
                    .toList();

            if (!nuevos.isEmpty()) {
                repo.saveAll(nuevos);
            }
        };
    }

    private RecursoEducativo build(String titulo, String desc, String url,
                                   TipoRecurso tipo, Lenguaje len, Nivel niv) {
        return RecursoEducativo.builder()
                .titulo(titulo)
                .descripcion(desc)
                .url(url)
                .tipo(tipo)
                .lenguaje(len)
                .nivel(niv)
                .build();
    }
}
