package cl.tuuni.biblioteca.controller;

import cl.tuuni.biblioteca.entity.Curso;
import cl.tuuni.biblioteca.entity.Leccion;
import cl.tuuni.biblioteca.entity.Lenguaje;
import cl.tuuni.biblioteca.entity.Nivel;
import cl.tuuni.biblioteca.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CursoController {

    private final CursoService servicio;

    // =========================================================
    // LISTADO GENERAL
    // =========================================================
    @GetMapping("/cursos")
    public String listar(@RequestParam(name = "tab", defaultValue = "disponibles") String tab,
                         Principal p,
                         Model model) {

        model.addAttribute("title", "Cursos");
        model.addAttribute("tab", tab);
        model.addAttribute("inscritos", servicio.listarInscritos(p));
        model.addAttribute("disponibles", servicio.listarDisponibles(p));
        model.addAttribute("completados", servicio.listarCompletados(p));
        return "cursos/lista";
    }

    // =========================================================
    // INSCRIBIR / ABANDONAR
    // =========================================================
    @PostMapping("/cursos/{id}/inscribirme")
    public String inscribir(@PathVariable Long id,
                            Principal p,
                            RedirectAttributes ra) {
        servicio.inscribirme(p, id);
        ra.addFlashAttribute("ok", "Inscripción realizada correctamente.");
        return "redirect:/cursos?tab=inscritos";
    }

    @PostMapping("/cursos/{id}/abandonar")
    public String abandonar(@PathVariable Long id,
                            Principal p,
                            RedirectAttributes ra) {
        servicio.abandonar(p, id);
        ra.addFlashAttribute("ok", "Has abandonado el curso correctamente.");
        return "redirect:/cursos?tab=disponibles";
    }

    // =========================================================
    // COMPLETAR CURSO
    // =========================================================
    @PostMapping("/cursos/{id}/completar")
    public String completarCurso(@PathVariable Long id,
                                 Principal p,
                                 RedirectAttributes ra) {
        servicio.marcarCursoComoCompletado(p, id);
        ra.addFlashAttribute("ok", "Has marcado este curso como completado.");
        return "redirect:/cursos/" + id;
    }

    // =========================================================
    // DESCRIPCIÓN ESTILO “UDEMY”
    // =========================================================
    @GetMapping("/cursos/{id}/descripcion")
    public String descripcion(@PathVariable Long id,
                              Principal p,
                              Model model) {

        var curso = servicio.curso(id);
        var insc = servicio.inscripcion(p, id);

        Lenguaje lang = curso.getLenguaje() != null ? curso.getLenguaje() : Lenguaje.JAVA;
        Nivel nivel = curso.getNivel() != null ? curso.getNivel() : Nivel.BASICO;

        String lenguajeNombre = switch (lang) {
            case JAVA -> "Java";
            case JAVASCRIPT -> "JavaScript";
            case PYTHON -> "Python";
        };

        String nivelNombre = switch (nivel) {
            case BASICO -> "Básico";
            case INTERMEDIO -> "Intermedio";
            case AVANZADO -> "Avanzado";
        };

        String disclaimerTiempo =
                "Las semanas que verás a continuación son solo una sugerencia para organizar tu estudio. " +
                        "No es obligatorio seguirlas al pie de la letra: adáptalas a tu propio ritmo y disponibilidad de tiempo.";

        List<String> loQueAprenderas = new ArrayList<>();
        List<String> requisitos = new ArrayList<>();
        List<String> recursos = new ArrayList<>();
        List<SemanaView> semanas = new ArrayList<>();
        String publicoObjetivo =
                "Personas interesadas en mejorar sus habilidades de programación de forma práctica.";

        // ====================================================================
        // Definir contenido por curso (solo Java / JS / Python)
        // ====================================================================

        // ------------- (TODO TU CÓDIGO COMO LO TENÍAS AQUÍ) -------------
        //     OJO: NO toqué nada desde aquí hacia abajo de este método
        //     lo dejo EXACTAMENTE igual que tu versión original.
        // -----------------------------------------------------------------

        if (lang == Lenguaje.JAVA && nivel == Nivel.BASICO) {
            // Java principiante
            publicoObjetivo = "Personas que están dando sus primeros pasos en programación " +
                    "y quieren aprender Java desde cero.";

            loQueAprenderas.add("Instalar y configurar el entorno de desarrollo para Java.");
            loQueAprenderas.add("Comprender la sintaxis básica del lenguaje: variables, tipos y operadores.");
            loQueAprenderas.add("Utilizar estructuras de control como condicionales e iteraciones.");
            loQueAprenderas.add("Crear y llamar métodos propios para organizar el código.");
            loQueAprenderas.add("Introducirte en la programación orientada a objetos (clases y objetos).");

            requisitos.add("No es obligatorio tener experiencia previa en programación.");
            requisitos.add("Tener motivación para practicar escribiendo código.");
            requisitos.add("Contar con un computador donde puedas instalar Java y un editor de código.");

            recursos.add("JDK 17 o superior (idealmente Java 21).");
            recursos.add("Un IDE como IntelliJ IDEA Community, Eclipse o VS Code con extensión de Java.");
            recursos.add("Acceso a documentación introductoria en español sobre Java.");
            recursos.add("Espacio para guardar ejemplos y pequeños proyectos.");

            semanas.add(new SemanaView(
                    "Semana 1",
                    "Fundamentos del lenguaje y primeros programas.",
                    "1-2 horas sugeridas.",
                    "Instalación de Java y el IDE, primer programa, tipos de datos primitivos y operaciones básicas."
            ));
            semanas.add(new SemanaView(
                    "Semana 2",
                    "Control de flujo y lógica básica.",
                    "1-2 horas sugeridas..",
                    "Uso de if/else, switch, bucles for y while, y resolución de problemas sencillos."
            ));
            semanas.add(new SemanaView(
                    "Semana 3",
                    "Métodos y organización del código.",
                    "1-2 horas sugeridas.",
                    "Definición de métodos, parámetros, retorno de valores y división del programa en partes reutilizables."
            ));
            semanas.add(new SemanaView(
                    "Semana 4",
                    "Introducción a la POO en Java.",
                    "1-2 horas sugeridas.",
                    "Concepto de clase y objeto, atributos, métodos y creación de tus primeros modelos simples."
            ));

        } else if (lang == Lenguaje.JAVA && nivel == Nivel.INTERMEDIO) {
            // Java intermedio
            publicoObjetivo = "Personas que ya manejan los fundamentos de Java " +
                    "y quieren profundizar en POO, colecciones y manejo de errores.";

            loQueAprenderas.add("Aplicar herencia y polimorfismo en tus diseños.");
            loQueAprenderas.add("Trabajar con colecciones como List, Set y Map.");
            loQueAprenderas.add("Manejar excepciones y errores de forma controlada.");
            loQueAprenderas.add("Introducirte en el uso de streams para procesar colecciones.");
            loQueAprenderas.add("Prepararte para cursos posteriores con frameworks Java.");

            requisitos.add("Haber cursado Java básico o equivalente.");
            requisitos.add("Sentirte cómodo con la sintaxis y estructuras de control.");
            requisitos.add("Tener un entorno Java ya configurado.");

            recursos.add("JDK 17 o superior.");
            recursos.add("IDE como IntelliJ IDEA Community o Eclipse.");
            recursos.add("Acceso a la documentación oficial de Java.");
            recursos.add("Repositorio local o remoto (opcional) para guardar ejercicios.");

            semanas.add(new SemanaView(
                    "Semana 1",
                    "Repaso de fundamentos y clases.",
                    "1-2 horas sugeridas.",
                    "Repaso de clases, objetos y encapsulación, ajuste del entorno para el curso."
            ));
            semanas.add(new SemanaView(
                    "Semana 2",
                    "Herencia, polimorfismo e interfaces.",
                    "1-2 horas sugeridas.",
                    "Diseño de jerarquías de clases, override de métodos y uso de interfaces."
            ));
            semanas.add(new SemanaView(
                    "Semana 3",
                    "Colecciones y manejo de excepciones.",
                    "1-2 horas sugeridas.",
                    "List, Set, Map, try/catch y excepciones personalizadas."
            ));
            semanas.add(new SemanaView(
                    "Semana 4",
                    "Streams y proyecto integrador.",
                    "1-2 horas sugeridas.",
                    "Uso básico de streams y desarrollo de un mini-proyecto."
            ));

        } else if (lang == Lenguaje.JAVA && nivel == Nivel.AVANZADO) {
            // Java avanzado
            publicoObjetivo = "Personas que ya dominan Java intermedio y quieren dar el salto " +
                    "a desarrollo de aplicaciones más complejas.";

            loQueAprenderas.add("Entender la arquitectura de aplicaciones Java modernas.");
            loQueAprenderas.add("Introducirte en APIs REST con frameworks como Spring Boot.");
            loQueAprenderas.add("Trabajar con bases de datos desde Java utilizando JPA/Hibernate (a nivel introductorio).");
            loQueAprenderas.add("Aplicar buenas prácticas de organización por capas.");
            loQueAprenderas.add("Preparar tus proyectos para integrarse con frontends web.");

            requisitos.add("Dominar POO, colecciones, excepciones y streams.");
            requisitos.add("Conocer fundamentos de bases de datos relacionales y SQL.");
            requisitos.add("Tener JDK y un IDE ya instalados.");

            recursos.add("JDK 17 o superior.");
            recursos.add("IntelliJ IDEA Community u otro IDE.");
            recursos.add("Un gestor de bases de datos relacional.");
            recursos.add("Cliente para base de datos (GUI o consola).");

            semanas.add(new SemanaView(
                    "Semana 1",
                    "Arquitectura y repaso avanzado.",
                    "1-2 horas sugeridas.",
                    "Capas de una aplicación, patrones básicos y configuración del entorno."
            ));
            semanas.add(new SemanaView(
                    "Semana 2",
                    "APIs REST con Java.",
                    "1-2 horas sugeridas.",
                    "Conceptos de API, endpoints, controladores y manejo de peticiones/respuestas."
            ));
            semanas.add(new SemanaView(
                    "Semana 3",
                    "Persistencia con bases de datos.",
                    "1-2 horas sugeridas.",
                    "Mapeo de entidades, operaciones CRUD y consultas básicas."
            ));
            semanas.add(new SemanaView(
                    "Semana 4",
                    "Proyecto integrador y siguientes pasos.",
                    "1-2 horas sugeridas.",
                    "Construcción de un pequeño backend y recomendaciones para profundizar."
            ));

        } else if (lang == Lenguaje.JAVASCRIPT && nivel == Nivel.BASICO) {
            // JavaScript principiante
            publicoObjetivo = "Personas que quieren aprender a crear páginas web interactivas " +
                    "y nunca han trabajado con JavaScript o muy poco.";

            loQueAprenderas.add("Comprender la sintaxis básica de JavaScript en el navegador.");
            loQueAprenderas.add("Trabajar con variables, tipos de datos y operadores.");
            loQueAprenderas.add("Usar estructuras de control y funciones simples.");
            loQueAprenderas.add("Manipular elementos básicos del DOM.");
            loQueAprenderas.add("Crear pequeñas interacciones en la página.");

            requisitos.add("Conocer HTML y CSS a nivel básico.");
            requisitos.add("Tener nociones de cómo funciona una página web.");
            requisitos.add("Disponer de un navegador moderno y un editor de código.");

            recursos.add("Navegador moderno (Chrome, Firefox, Edge).");
            recursos.add("Visual Studio Code u otro editor.");
            recursos.add("Documentación de MDN Web Docs en español.");
            recursos.add("Cuenta GitHub (opcional).");

            semanas.add(new SemanaView(
                    "Semana 1",
                    "Fundamentos y primeros scripts.",
                    "1-2 horas sugeridas.",
                    "Sintaxis básica, variables, tipos de datos y pruebas en la consola del navegador."
            ));
            semanas.add(new SemanaView(
                    "Semana 2",
                    "Control de flujo y funciones.",
                    "1-2 horas sugeridas.",
                    "Condicionales, bucles y funciones simples para organizar el código."
            ));
            semanas.add(new SemanaView(
                    "Semana 3",
                    "DOM básico e interacciones sencillas.",
                    "1-2 horas sugeridas.",
                    "Selección de elementos, cambio de texto y estilos, eventos básicos."
            ));
            semanas.add(new SemanaView(
                    "Semana 4",
                    "Mini-proyecto web.",
                    "1-2 horas sugeridas.",
                    "Construcción de una página interactiva que combine lo aprendido."
            ));

        } else if (lang == Lenguaje.JAVASCRIPT && nivel == Nivel.INTERMEDIO) {
            // JavaScript intermedio
            publicoObjetivo = "Personas que ya conocen los fundamentos de JavaScript " +
                    "y quieren dar el salto a un nivel intermedio.";

            loQueAprenderas.add("Profundizar en funciones, scope y closures.");
            loQueAprenderas.add("Manipular el DOM de forma dinámica.");
            loQueAprenderas.add("Trabajar con asincronía: Promises y async/await.");
            loQueAprenderas.add("Organizar el código en módulos reutilizables.");
            loQueAprenderas.add("Aplicar buenas prácticas básicas para JavaScript.");

            requisitos.add("Manejar los fundamentos de JavaScript.");
            requisitos.add("Conocer HTML y CSS a nivel básico.");
            requisitos.add("Tener un navegador moderno y un editor de código instalado.");

            recursos.add("Computador con Internet.");
            recursos.add("Navegador con herramientas de desarrollador.");
            recursos.add("VS Code u otro editor.");
            recursos.add("Documentación de MDN Web Docs en español.");

            semanas.add(new SemanaView(
                    "Semana 1",
                    "Repaso sólido de fundamentos y funciones.",
                    "1-2 horas sugeridas.",
                    "Repaso de sintaxis, tipos, funciones, parámetros y retorno. Introducción a scope y closures."
            ));
            semanas.add(new SemanaView(
                    "Semana 2",
                    "Manipulación del DOM y eventos.",
                    "1-2 horas sugeridas.",
                    "Selección de elementos, modificación de contenido y estilos, manejo de eventos."
            ));
            semanas.add(new SemanaView(
                    "Semana 3",
                    "Asincronía en JavaScript.",
                    "1-2 horas sugeridas.",
                    "Concepto de asincronía, Promises y async/await. Consumo básico de APIs."
            ));
            semanas.add(new SemanaView(
                    "Semana 4",
                    "Organización del código y proyecto integrador.",
                    "1-2 horas sugeridas.",
                    "Uso de módulos, separación por archivos y desarrollo de una pequeña aplicación web."
            ));

        } else if (lang == Lenguaje.JAVASCRIPT && nivel == Nivel.AVANZADO) {
            // JavaScript avanzado
            publicoObjetivo = "Personas que ya trabajan con JavaScript " +
                    "y quieren construir aplicaciones más estructuradas y complejas.";

            loQueAprenderas.add("Comprender mejor el modelo de ejecución de JavaScript (event loop).");
            loQueAprenderas.add("Trabajar con asincronía avanzada y patrones comunes.");
            loQueAprenderas.add("Organizar aplicaciones en módulos o componentes escalables.");
            loQueAprenderas.add("Introducirte en conceptos que usan frameworks modernos.");
            loQueAprenderas.add("Aplicar buenas prácticas de rendimiento y mantenimiento.");

            requisitos.add("Haber completado contenidos intermedios de JavaScript.");
            requisitos.add("Sentirte cómodo con DOM y async/await.");
            requisitos.add("Manejar herramientas básicas del ecosistema (npm, etc.).");

            recursos.add("Computador con Node.js instalado.");
            recursos.add("VS Code con extensiones para JavaScript.");
            recursos.add("Navegador moderno con DevTools.");
            recursos.add("Acceso a documentación avanzada.");

            semanas.add(new SemanaView(
                    "Semana 1",
                    "Event loop y asincronía avanzada.",
                    "1-2 horas sugeridas.",
                    "Cola de tareas, microtareas, promesas encadenadas y manejo de errores asincrónicos."
            ));
            semanas.add(new SemanaView(
                    "Semana 2",
                    "Arquitectura de aplicaciones JavaScript.",
                    "1-2 horas sugeridas.",
                    "Patrones básicos, separación en módulos y organización del proyecto."
            ));
            semanas.add(new SemanaView(
                    "Semana 3",
                    "Introducción a frameworks modernos.",
                    "1-2 horas sugeridas.",
                    "Conceptos comunes que luego usarás en frameworks (componentes, estado básico, rutas)."
            ));
            semanas.add(new SemanaView(
                    "Semana 4",
                    "Optimización y buenas prácticas.",
                    "1-2 horas sugeridas.",
                    "Buenas prácticas de rendimiento, legibilidad y mantenimiento del código."
            ));

        } else if (lang == Lenguaje.PYTHON && nivel == Nivel.BASICO) {
            // Python principiante
            publicoObjetivo = "Personas que quieren aprender a programar con un lenguaje sencillo " +
                    "y flexible como Python, partiendo desde cero.";

            loQueAprenderas.add("Instalar Python y configurar un entorno de trabajo.");
            loQueAprenderas.add("Comprender la sintaxis básica: variables, tipos y operadores.");
            loQueAprenderas.add("Usar estructuras de control (if, for, while) para resolver problemas.");
            loQueAprenderas.add("Trabajar con listas, diccionarios y otras estructuras básicas.");
            loQueAprenderas.add("Escribir tus primeras funciones y scripts.");

            requisitos.add("No se requiere experiencia previa en programación.");
            requisitos.add("Manejo básico del sistema operativo.");
            requisitos.add("Ganas de practicar y probar ejemplos.");

            recursos.add("Python 3 instalado (idealmente 3.10+).");
            recursos.add("Editor de código como VS Code o PyCharm Community.");
            recursos.add("Documentación en español de Python.");
            recursos.add("Terminal o consola para ejecutar scripts.");

            semanas.add(new SemanaView(
                    "Semana 1",
                    "Introducción y primeros scripts.",
                    "1-2 horas sugeridas.",
                    "Instalación, uso de la consola de Python, tipos básicos y operaciones simples."
            ));
            semanas.add(new SemanaView(
                    "Semana 2",
                    "Control de flujo y colecciones.",
                    "1-2 horas sugeridas.",
                    "If/else, bucles, listas y diccionarios con ejemplos prácticos."
            ));
            semanas.add(new SemanaView(
                    "Semana 3",
                    "Funciones y módulos básicos.",
                    "1-2 horas sugeridas.",
                    "Definición de funciones, parámetros, retorno y uso de módulos estándar sencillos."
            ));
            semanas.add(new SemanaView(
                    "Semana 4",
                    "Pequeños proyectos prácticos.",
                    "1-2 horas sugeridas.",
                    "Scripts que resuelvan tareas concretas (calculadoras, procesamiento de texto, etc.)."
            ));

        } else if (lang == Lenguaje.PYTHON && nivel == Nivel.INTERMEDIO) {
            // Python intermedio
            publicoObjetivo = "Personas que ya conocen los fundamentos de Python " +
                    "and quieren comenzar a usar el lenguaje en proyectos más útiles.";

            loQueAprenderas.add("Trabajar con archivos (lectura y escritura) de forma segura.");
            loQueAprenderas.add("Utilizar entornos virtuales y gestionar dependencias.");
            loQueAprenderas.add("Aprovechar librerías estándar y de terceros para tareas comunes.");
            loQueAprenderas.add("Aplicar manejo de errores y excepciones en tus scripts.");
            loQueAprenderas.add("Organizar proyectos en módulos y paquetes.");

            requisitos.add("Haber cursado Python básico o manejar cómodamente la sintaxis.");
            requisitos.add("Saber ejecutar scripts desde la terminal o el IDE.");
            requisitos.add("Tener Python 3 y un editor de código configurado.");

            recursos.add("Python 3 con pip configurado.");
            recursos.add("Entorno virtual (venv) para cada proyecto.");
            recursos.add("Editor de código (VS Code, PyCharm, etc.).");
            recursos.add("Acceso a documentación de librerías en español cuando sea posible.");

            semanas.add(new SemanaView(
                    "Semana 1",
                    "Archivos y manejo de errores.",
                    "1-2 horas sugeridas.",
                    "Lectura y escritura de archivos de texto, uso de try/except para controlar errores."
            ));
            semanas.add(new SemanaView(
                    "Semana 2",
                    "Entornos virtuales y paquetes.",
                    "1-2 horas sugeridas.",
                    "Creación de entornos virtuales, instalación de librerías y organización de proyectos."
            ));
            semanas.add(new SemanaView(
                    "Semana 3",
                    "Librerías útiles y automatización.",
                    "1-2 horas sugeridas.",
                    "Uso de librerías estándar y de terceros para automatizar tareas sencillas."
            ));
            semanas.add(new SemanaView(
                    "Semana 4",
                    "Proyecto integrador.",
                    "1-2 horas sugeridas.",
                    "Desarrollo de un pequeño proyecto que combine archivos, librerías y buenas prácticas."
            ));

        } else if (lang == Lenguaje.PYTHON && nivel == Nivel.AVANZADO) {
            // Python avanzado
            publicoObjetivo = "Personas que ya programan en Python a nivel intermedio " +
                    "y quieren profundizar en POO, manejo avanzado de errores, decoradores y módulos.";

            loQueAprenderas.add("Aplicar programación orientada a objetos en Python con clases, herencia y composición.");
            loQueAprenderas.add("Manejar errores y excepciones de forma robusta.");
            loQueAprenderas.add("Entender y utilizar decoradores para añadir comportamiento reutilizable.");
            loQueAprenderas.add("Organizar proyectos usando paquetes y módulos de forma profesional.");
            loQueAprenderas.add("Trabajar con archivos y recursos externos siguiendo buenas prácticas.");

            requisitos.add("Conocer Python a nivel intermedio.");
            requisitos.add("Manejar un entorno de desarrollo como VS Code, PyCharm o similar.");
            requisitos.add("Tener experiencia resolviendo ejercicios o pequeños proyectos.");

            recursos.add("Computador con Python 3 instalado.");
            recursos.add("Entorno virtual (venv).");
            recursos.add("Editor de código como VS Code o PyCharm Community.");
            recursos.add("Acceso a la documentación oficial de Python.");
            recursos.add("Repositorio Git (opcional) para versionar los ejercicios.");

            semanas.add(new SemanaView(
                    "Semana 1",
                    "POO en Python: clases y objetos.",
                    "1-2 horas sugeridas.",
                    "Repaso de clases, atributos, métodos, herencia y composición."
            ));
            semanas.add(new SemanaView(
                    "Semana 2",
                    "Manejo avanzado de errores y excepciones.",
                    "1-2 horas sugeridas.",
                    "Tipos de excepciones, bloques try/except/else/finally y excepciones personalizadas."
            ));
            semanas.add(new SemanaView(
                    "Semana 3",
                    "Decoradores y funciones de orden superior.",
                    "1-2 horas sugeridas.",
                    "Funciones como ciudadanos de primera clase, closures y creación de decoradores útiles."
            ));
            semanas.add(new SemanaView(
                    "Semana 4",
                    "Módulos, paquetes y trabajo con archivos.",
                    "1-2 horas sugeridas.",
                    "Estructura de paquetes, imports y lectura/escritura de archivos en proyectos."
            ));

        } else {
            // Fallback genérico
            loQueAprenderas.add("Comprender los conceptos clave del tema central del curso.");
            loQueAprenderas.add("Aplicar lo aprendido en ejercicios prácticos.");
            loQueAprenderas.add("Desarrollar un criterio propio para seguir aprendiendo de forma autónoma.");

            requisitos.add("Motivación por aprender y practicar de forma constante.");
            requisitos.add("Conocimientos previos acordes al nivel del curso.");
            requisitos.add("Acceso regular a un computador con conexión a Internet.");

            recursos.add("Computador personal con un editor de código instalado.");
            recursos.add("Navegador web actualizado.");
            recursos.add("Acceso a documentación oficial y material de apoyo en español.");

            semanas.add(new SemanaView(
                    "Semana 1",
                    "Introducción y fundamentos.",
                    "1-2 horas sugeridas.",
                    "Revisión de conceptos base y configuración del entorno de trabajo."
            ));
            semanas.add(new SemanaView(
                    "Semana 2",
                    "Profundización en los temas centrales.",
                    "1-2 horas sugeridas.",
                    "Ejercicios guiados para afianzar los contenidos más importantes."
            ));
            semanas.add(new SemanaView(
                    "Semana 3",
                    "Aplicación práctica.",
                    "1-2 horas sugeridas.",
                    "Desarrollo de un pequeño proyecto que integre lo aprendido."
            ));
            semanas.add(new SemanaView(
                    "Semana 4",
                    "Cierre y siguientes pasos.",
                    "1-2 horas sugeridas.",
                    "Repaso general y recomendaciones para seguir estudiando."
            ));
        }

        model.addAttribute("title", curso.getTitulo());
        model.addAttribute("curso", curso);
        model.addAttribute("inscrito", insc != null);
        model.addAttribute("lenguajeNombre", lenguajeNombre);
        model.addAttribute("nivelNombre", nivelNombre);
        model.addAttribute("publicoObjetivo", publicoObjetivo);
        model.addAttribute("loQueAprenderas", loQueAprenderas);
        model.addAttribute("semanas", semanas);
        model.addAttribute("requisitos", requisitos);
        model.addAttribute("recursos", recursos);
        model.addAttribute("disclaimerTiempo", disclaimerTiempo);

        return "cursos/descripcion";
    }

    // =========================================================
    // DETALLE DEL CURSO (LECCIONES + SEMANAS)
    // =========================================================
    @GetMapping("/cursos/{id}")
    public String detalle(@PathVariable Long id,
                          Principal p,
                          Model model) {

        var curso = servicio.curso(id);
        var lecciones = servicio.lecciones(id);
        var completadas = servicio.idsLeccionesCompletadas(p, id);

        Map<Long, String> embedUrls = new HashMap<>();
        for (var l : lecciones) {
            var url = l.getUrl();
            var embed = toYoutubeEmbed(url);
            if (embed != null) {
                embedUrls.put(l.getId(), embed);
            }
        }

        // Agrupar lecciones en semanas (estructura sugerida)
        var semanasGrupos = agruparPorSemanas(lecciones, 4); // 4 semanas sugeridas

        var insc = servicio.inscripcion(p, id);

        model.addAttribute("title", curso.getTitulo());
        model.addAttribute("curso", curso);
        model.addAttribute("cursoTitulo", curso.getTitulo());
        model.addAttribute("lecciones", lecciones);
        model.addAttribute("cursoId", id);
        model.addAttribute("inscripcion", insc);
        model.addAttribute("completadas", completadas);
        model.addAttribute("pendientes", Math.max(lecciones.size() - completadas.size(), 0));
        model.addAttribute("embedUrls", embedUrls);
        model.addAttribute("semanasGrupos", semanasGrupos);

        return "cursos/detalle";
    }

    // =========================================================
    // PROGRESO DE LECCIONES
    // =========================================================
    @PostMapping("/cursos/{cursoId}/leccion/{leccionId}/completar")
    public String completar(@PathVariable Long cursoId,
                            @PathVariable Long leccionId,
                            Principal p) {
        servicio.marcarLeccion(p, cursoId, leccionId);
        return "redirect:/cursos/" + cursoId;
    }

    @PostMapping("/cursos/{cursoId}/leccion/{leccionId}/desmarcar")
    public String desmarcar(@PathVariable Long cursoId,
                            @PathVariable Long leccionId,
                            Principal p) {
        servicio.desmarcarLeccion(p, cursoId, leccionId);
        return "redirect:/cursos/" + cursoId;
    }

    // =========================================================
    // RECORDS / HELPERS
    // =========================================================

    public record SemanaView(String nombre,
                             String titulo,
                             String duracion,
                             String detalle) {
    }

    public record SemanaGrupo(int numero, List<Leccion> lecciones) {
    }

    /** Agrupa las lecciones en n semanas, repartiendo de forma uniforme. */
    private List<SemanaGrupo> agruparPorSemanas(List<Leccion> lecciones, int numSemanas) {
        List<SemanaGrupo> resultado = new ArrayList<>();
        if (lecciones == null || lecciones.isEmpty() || numSemanas <= 0) {
            return resultado;
        }

        int total = lecciones.size();
        int base = total / numSemanas;
        int extra = total % numSemanas; // las primeras "extra" semanas tendrán 1 lección más

        int index = 0;
        for (int i = 1; i <= numSemanas; i++) {
            int cantidad = base + (i <= extra ? 1 : 0);
            if (index >= total) {
                resultado.add(new SemanaGrupo(i, new ArrayList<>()));
            } else {
                int fin = Math.min(index + cantidad, total);
                resultado.add(new SemanaGrupo(i, new ArrayList<>(lecciones.subList(index, fin))));
                index = fin;
            }
        }
        return resultado;
    }

    /** Si la URL es de YouTube válida, devuelve el embed URL; si no, null. */
    private String toYoutubeEmbed(String url) {
        if (url == null) return null;
        try {
            var u = URI.create(url.trim());
            var host = u.getHost() == null ? "" : u.getHost().toLowerCase();

            // youtu.be/<id>
            if (host.endsWith("youtu.be")) {
                var path = u.getPath();
                if (path != null && path.length() > 1) {
                    var id = path.substring(1);
                    return "https://www.youtube.com/embed/" + sanitizeId(id);
                }
            }

            // youtube.com/watch?v=<id>&...
            if (host.contains("youtube.com")) {
                var query = u.getQuery();
                if (query != null) {
                    for (String part : query.split("&")) {
                        var kv = part.split("=", 2);
                        if (kv.length == 2 && kv[0].equals("v")) {
                            var id = URLDecoder.decode(kv[1], StandardCharsets.UTF_8);
                            return "https://www.youtube.com/embed/" + sanitizeId(id);
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    /** Limpia el ID (quita fragmentos o parámetros residuales). */
    private String sanitizeId(String id) {
        var clean = id;
        int cut = clean.indexOf('&');
        if (cut >= 0) clean = clean.substring(0, cut);
        cut = clean.indexOf('?');
        if (cut >= 0) clean = clean.substring(0, cut);
        cut = clean.indexOf('#');
        if (cut >= 0) clean = clean.substring(0, cut);
        return clean;
    }
}
