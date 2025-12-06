package cl.tuuni.biblioteca.config;

import cl.tuuni.biblioteca.entity.Curso;
import cl.tuuni.biblioteca.entity.Leccion;
import cl.tuuni.biblioteca.entity.Lenguaje;
import cl.tuuni.biblioteca.entity.Nivel;
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
    CommandLineRunner initCursos() {
        return args -> {

            // Si ya hay cursos, no volvemos a sembrar nada
            if (cursoRepo.count() > 0) {
                return;
            }

            // =====================================================
            // JAVA
            // =====================================================

            // --- Java Básico (principiante) ---
            Curso javaBasico = cursoRepo.save(Curso.builder()
                    .titulo("Java Básico")
                    .descripcion("Aprende los fundamentos del lenguaje Java y escribe tus primeros programas de consola.")
                    .nivel(Nivel.BASICO)
                    .lenguaje(Lenguaje.JAVA)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaBasico)
                    .titulo("Qué es Java y para qué sirve")
                    .url("https://azure.microsoft.com/es-es/resources/cloud-computing-dictionary/what-is-java-programming-language")
                    .orden(1)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaBasico)
                    .titulo("Instalación del JDK y configuración del IDE")
                    .url("https://www.youtube.com/watch?v=-QG4TFNKM2w")
                    .orden(2)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaBasico)
                    .titulo("Tu primer programa en Java (Hola Mundo)")
                    .url("https://www.youtube.com/watch?v=PMsTKxeNxGc")
                    .orden(3)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaBasico)
                    .titulo("Variables y tipos de datos")
                    .url("https://openwebinars.net/blog/introduccion-a-java-datos-y-variables/")
                    .orden(4)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaBasico)
                    .titulo("Condicionales y bucles básicos")
                    .url("https://fernandoarciniega.com/java-basico-6-control-de-flujo-condicionales-y-bucles-en-java/")
                    .orden(5)
                    .build());

            // Lecciones extra para que las semanas tengan más contenido
            leccionRepo.save(Leccion.builder()
                    .curso(javaBasico)
                    .titulo("Arreglos")
                    .url("https://www.programandojava.com/blog/arreglos-en-java/")
                    .orden(6)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaBasico)
                    .titulo("Métodos, argumentos y paso de parámetros")
                    .url("https://openwebinars.net/blog/introduccion-a-java-metodos-parametros-y-argumentos/")
                    .orden(7)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaBasico)
                    .titulo("Operadores")
                    .url("https://www.datacamp.com/es/doc/java/java-operators")
                    .orden(8)
                    .build());

            // --- Java Intermedio ---
            Curso javaIntermedio = cursoRepo.save(Curso.builder()
                    .titulo("Java Intermedio")
                    .descripcion("Profundiza en programación orientada a objetos, colecciones y manejo de errores en Java.")
                    .nivel(Nivel.INTERMEDIO)
                    .lenguaje(Lenguaje.JAVA)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaIntermedio)
                    .titulo("Clases, objetos y encapsulación")
                    .url("https://www.discoduroderoer.es/curso-de-programacion-java-desde-0-tema-7-poo-i/")
                    .orden(1)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaIntermedio)
                    .titulo("Herencia e interfaces en Java")
                    .url("https://www.discoduroderoer.es/curso-de-programacion-java-desde-0-tema-9-herencia/")
                    .orden(2)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaIntermedio)
                    .titulo("Colecciones: List, Set y Map")
                    .url("https://www.youtube.com/watch?v=zvRZp0CZHqE")
                    .orden(3)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaIntermedio)
                    .titulo("Manejo de excepciones en Java")
                    .url("https://www.discoduroderoer.es/curso-de-programacion-java-desde-0-tema-12-excepciones/")
                    .orden(4)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaIntermedio)
                    .titulo("Streams y programación funcional básica")
                    .url("https://www.youtube.com/watch?v=goQ4zWwI2iQ")
                    .orden(5)
                    .build());

            // --- Java Avanzado ---
            Curso javaAvanzado = cursoRepo.save(Curso.builder()
                    .titulo("Java Avanzado")
                    .descripcion("Da el salto al desarrollo de aplicaciones modernas con Java y buenas prácticas.")
                    .nivel(Nivel.AVANZADO)
                    .lenguaje(Lenguaje.JAVA)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaAvanzado)
                    .titulo("Arquitectura de aplicaciones Java")
                    .url("https://www.youtube.com/watch?v=jU2yQ_kA5dQ")
                    .orden(1)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaAvanzado)
                    .titulo("Introducción a APIs REST con Java")
                    .url("https://www.youtube.com/watch?v=VTWe5E-0uCE")
                    .orden(2)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaAvanzado)
                    .titulo("Persistencia con JPA e Hibernate (visión general)")
                    .url("https://www.youtube.com/watch?v=8SGI_XS5OPw")
                    .orden(3)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(javaAvanzado)
                    .titulo("Buenas prácticas y patrones básicos")
                    .url("https://www.youtube.com/watch?v=i2w8Q6gD0xA")
                    .orden(4)
                    .build());

            // =====================================================
            // JAVASCRIPT
            // =====================================================

            // --- JavaScript Básico ---
            Curso jsBasico = cursoRepo.save(Curso.builder()
                    .titulo("JavaScript Básico")
                    .descripcion("Aprende JavaScript desde cero para crear páginas web interactivas.")
                    .nivel(Nivel.BASICO)
                    .lenguaje(Lenguaje.JAVASCRIPT)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsBasico)
                    .titulo("Introducción a JavaScript en la web")
                    .url("https://developer.mozilla.org/es/docs/Learn/JavaScript/First_steps/What_is_JavaScript")
                    .orden(1)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsBasico)
                    .titulo("Primeros pasos con variables y tipos")
                    .url("https://developer.mozilla.org/es/docs/Learn/JavaScript/First_steps/A_first_splash")
                    .orden(2)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsBasico)
                    .titulo("Condicionales y bucles en JavaScript")
                    .url("https://developer.mozilla.org/es/docs/Learn/JavaScript/Building_blocks/conditionals")
                    .orden(3)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsBasico)
                    .titulo("Funciones básicas")
                    .url("https://developer.mozilla.org/es/docs/Learn/JavaScript/Building_blocks/Functions")
                    .orden(4)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsBasico)
                    .titulo("Manipulación básica del DOM")
                    .url("https://developer.mozilla.org/es/docs/Learn/JavaScript/Client-side_web_APIs/Manipulating_documents")
                    .orden(5)
                    .build());

            // --- JavaScript Intermedio ---
            Curso jsIntermedio = cursoRepo.save(Curso.builder()
                    .titulo("JavaScript Intermedio")
                    .descripcion("Domina estructuras, funciones y manipulación del DOM para crear experiencias dinámicas.")
                    .nivel(Nivel.INTERMEDIO)
                    .lenguaje(Lenguaje.JAVASCRIPT)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsIntermedio)
                    .titulo("Profundizando en funciones y scope")
                    .url("https://developer.mozilla.org/es/docs/Web/JavaScript/Guide/Functions")
                    .orden(1)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsIntermedio)
                    .titulo("Eventos y manejo de formularios")
                    .url("https://developer.mozilla.org/es/docs/Learn/Forms/Form_validation")
                    .orden(2)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsIntermedio)
                    .titulo("Asincronía con Promises y async/await")
                    .url("https://developer.mozilla.org/es/docs/Learn/JavaScript/Asynchronous/Promises")
                    .orden(3)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsIntermedio)
                    .titulo("Consumo de APIs desde JavaScript")
                    .url("https://developer.mozilla.org/es/docs/Learn/JavaScript/Client-side_web_APIs/Fetching_data")
                    .orden(4)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsIntermedio)
                    .titulo("Organización del código en módulos")
                    .url("https://developer.mozilla.org/es/docs/Web/JavaScript/Guide/Modules")
                    .orden(5)
                    .build());

            // --- JavaScript Avanzado ---
            Curso jsAvanzado = cursoRepo.save(Curso.builder()
                    .titulo("JavaScript Avanzado")
                    .descripcion("Lleva JavaScript a un nivel profesional con patrones modernos y buenas prácticas.")
                    .nivel(Nivel.AVANZADO)
                    .lenguaje(Lenguaje.JAVASCRIPT)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsAvanzado)
                    .titulo("Event loop y modelo de concurrencia")
                    .url("https://developer.mozilla.org/es/docs/Web/JavaScript/EventLoop")
                    .orden(1)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsAvanzado)
                    .titulo("Patrones de diseño en aplicaciones JavaScript")
                    .url("https://www.youtube.com/watch?v=k9QwaMZcZbA")
                    .orden(2)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsAvanzado)
                    .titulo("Introducción a frameworks modernos (visión general)")
                    .url("https://www.youtube.com/watch?v=SBwoFkR1-8Y")
                    .orden(3)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(jsAvanzado)
                    .titulo("Optimización y buenas prácticas")
                    .url("https://developer.mozilla.org/es/docs/Learn/Performance")
                    .orden(4)
                    .build());

            // =====================================================
            // PYTHON
            // =====================================================

            // --- Python Básico ---
            Curso pyBasico = cursoRepo.save(Curso.builder()
                    .titulo("Python Básico")
                    .descripcion("Aprende a programar desde cero con Python, un lenguaje simple y potente.")
                    .nivel(Nivel.BASICO)
                    .lenguaje(Lenguaje.PYTHON)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(pyBasico)
                    .titulo("Introducción a Python")
                    .url("https://docs.python.org/es/3/tutorial/introduction.html")
                    .orden(1)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(pyBasico)
                    .titulo("Instalación de Python y uso de la consola")
                    .url("https://realpython.com/intro-to-python-es/")
                    .orden(2)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(pyBasico)
                    .titulo("Tipos de datos y estructuras básicas")
                    .url("https://docs.python.org/es/3/tutorial/datastructures.html")
                    .orden(3)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(pyBasico)
                    .titulo("Control de flujo en Python")
                    .url("https://docs.python.org/es/3/tutorial/controlflow.html")
                    .orden(4)
                    .build());

            // --- Python Intermedio ---
            Curso pyIntermedio = cursoRepo.save(Curso.builder()
                    .titulo("Python Intermedio")
                    .descripcion("Comienza a usar Python en proyectos más útiles con archivos, módulos y librerías.")
                    .nivel(Nivel.INTERMEDIO)
                    .lenguaje(Lenguaje.PYTHON)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(pyIntermedio)
                    .titulo("Funciones, argumentos y módulos")
                    .url("https://docs.python.org/es/3/tutorial/modules.html")
                    .orden(1)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(pyIntermedio)
                    .titulo("Manejo de archivos en Python")
                    .url("https://realpython.com/read-write-files-python/")
                    .orden(2)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(pyIntermedio)
                    .titulo("Entornos virtuales y gestión de paquetes")
                    .url("https://docs.python.org/es/3/tutorial/venv.html")
                    .orden(3)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(pyIntermedio)
                    .titulo("Uso de librerías externas para tareas comunes")
                    .url("https://realpython.com/python-modules-packages/")
                    .orden(4)
                    .build());

            // --- Python Avanzado ---
            Curso pyAvanzado = cursoRepo.save(Curso.builder()
                    .titulo("Python Avanzado")
                    .descripcion("Profundiza en POO, manejo avanzado de errores, decoradores y módulos en Python.")
                    .nivel(Nivel.AVANZADO)
                    .lenguaje(Lenguaje.PYTHON)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(pyAvanzado)
                    .titulo("Clases y objetos en detalle")
                    .url("https://docs.python.org/es/3/tutorial/classes.html")
                    .orden(1)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(pyAvanzado)
                    .titulo("Errores y excepciones en Python")
                    .url("https://docs.python.org/es/3/tutorial/errors.html")
                    .orden(2)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(pyAvanzado)
                    .titulo("Decoradores en Python (concepto y práctica)")
                    .url("https://realpython.com/primer-on-python-decorators/")
                    .orden(3)
                    .build());

            leccionRepo.save(Leccion.builder()
                    .curso(pyAvanzado)
                    .titulo("Módulos y paquetes avanzados")
                    .url("https://docs.python.org/es/3/tutorial/modules.html")
                    .orden(4)
                    .build());

            System.out.println("✅ Semillas de cursos creadas correctamente (Java / JavaScript / Python).");
        };
    }
}
