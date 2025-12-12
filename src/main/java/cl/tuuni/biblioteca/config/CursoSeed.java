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

            // =====================================================
            // JAVA
            // =====================================================

            // --- Java Básico (principiante) ---
            if (cursoRepo.findByTituloAndLenguaje("Java Básico", Lenguaje.JAVA).isEmpty()) {
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
            }

            // --- Java Intermedio ---
            if (cursoRepo.findByTituloAndLenguaje("Java Intermedio", Lenguaje.JAVA).isEmpty()) {
                Curso javaIntermedio = cursoRepo.save(Curso.builder()
                        .titulo("Java Intermedio")
                        .descripcion("Profundiza en programación orientada a objetos, colecciones y manejo de errores en Java.")
                        .nivel(Nivel.INTERMEDIO)
                        .lenguaje(Lenguaje.JAVA)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(javaIntermedio)
                        .titulo("Clases, objetos y encapsulación")
                        .url("https://www.datacamp.com/es/tutorial/oop-in-java")
                        .orden(1)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(javaIntermedio)
                        .titulo("Herencia e interfaces en Java")
                        .url("https://www.youtube.com/watch?v=dwQNB_BkwU8")
                        .orden(2)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(javaIntermedio)
                        .titulo("Colecciones: List, Set y Map")
                        .url("https://www.youtube.com/watch?v=mE1vnyn_QgU")
                        .orden(3)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(javaIntermedio)
                        .titulo("Manejo de excepciones en Java")
                        .url("https://openwebinars.net/blog/introduccion-a-poo-en-java-excepciones/")
                        .orden(4)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(javaIntermedio)
                        .titulo("Streams, Lambdas y programación funcional básica")
                        .url("https://www.youtube.com/watch?v=sSQtRfk_OaM")
                        .orden(5)
                        .build());
            }

            // --- Java Avanzado ---
            if (cursoRepo.findByTituloAndLenguaje("Java Avanzado", Lenguaje.JAVA).isEmpty()) {
                Curso javaAvanzado = cursoRepo.save(Curso.builder()
                        .titulo("Java Avanzado")
                        .descripcion("Da el salto al desarrollo de aplicaciones modernas con Java y buenas prácticas.")
                        .nivel(Nivel.AVANZADO)
                        .lenguaje(Lenguaje.JAVA)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(javaAvanzado)
                        .titulo("Arquitectura de aplicaciones Java")
                        .url("https://developer.android.com/topic/architecture?hl=es-419")
                        .orden(1)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(javaAvanzado)
                        .titulo("Introducción a APIs REST con Java")
                        .url("https://www.youtube.com/watch?v=5DXAV4FTcso")
                        .orden(2)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(javaAvanzado)
                        .titulo("Persistencia con JPA e Hibernate (visión general)")
                        .url("https://www.youtube.com/watch?v=kAIw1FBKJxg")
                        .orden(3)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(javaAvanzado)
                        .titulo("Buenas prácticas y patrones básicos")
                        .url("Buenas prácticas y patrones básicos")
                        .orden(4)
                        .build());
            }

            // =====================================================
            // JAVASCRIPT
            // =====================================================

            // --- JavaScript Básico ---
            if (cursoRepo.findByTituloAndLenguaje("JavaScript Básico", Lenguaje.JAVASCRIPT).isEmpty()) {
                Curso jsBasico = cursoRepo.save(Curso.builder()
                        .titulo("JavaScript Básico")
                        .descripcion("Aprende JavaScript desde cero para crear páginas web interactivas.")
                        .nivel(Nivel.BASICO)
                        .lenguaje(Lenguaje.JAVASCRIPT)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsBasico)
                        .titulo("Introducción a JavaScript en la web")
                        .url("https://developer.mozilla.org/es/docs/Web/JavaScript/Guide/Introduction")
                        .orden(1)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsBasico)
                        .titulo("Primeros pasos con variables y tipos")
                        .url("https://www.youtube.com/watch?v=sPIVZPE9R4o")
                        .orden(2)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsBasico)
                        .titulo("Condicionales y bucles en JavaScript")
                        .url("https://developer.mozilla.org/es/docs/Web/JavaScript/Guide/Loops_and_iteration")
                        .orden(3)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsBasico)
                        .titulo("Funciones básicas")
                        .url("https://developer.mozilla.org/es/docs/Web/JavaScript/Guide/Functions")
                        .orden(4)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsBasico)
                        .titulo("Manipulación básica del DOM")
                        .url("https://www.youtube.com/watch?v=z1a0eHWn5ds")
                        .orden(5)
                        .build());
            }

            // --- JavaScript Intermedio ---
            if (cursoRepo.findByTituloAndLenguaje("JavaScript Intermedio", Lenguaje.JAVASCRIPT).isEmpty()) {
                Curso jsIntermedio = cursoRepo.save(Curso.builder()
                        .titulo("JavaScript Intermedio")
                        .descripcion("Domina estructuras, funciones y manipulación del DOM para crear experiencias dinámicas.")
                        .nivel(Nivel.INTERMEDIO)
                        .lenguaje(Lenguaje.JAVASCRIPT)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsIntermedio)
                        .titulo("Profundizando en funciones y scope")
                        .url("https://www.youtube.com/watch?v=dRbO2Ypv9e8")
                        .orden(1)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsIntermedio)
                        .titulo("Eventos y manejo de formularios")
                        .url("https://www.youtube.com/watch?v=XKDZzoSRCpk")
                        .orden(2)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsIntermedio)
                        .titulo("Asincronía con Promises y async/await")
                        .url("https://www.youtube.com/watch?v=6O8ax3JYboc")
                        .orden(3)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsIntermedio)
                        .titulo("Consumo de APIs desde JavaScript")
                        .url("https://www.freecodecamp.org/espanol/news/consumiendo-servicios-api-desde-javascript/")
                        .orden(4)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsIntermedio)
                        .titulo("Organización del código en módulos")
                        .url("https://www.youtube.com/watch?v=_Fmsf6U4Gtg")
                        .orden(5)
                        .build());
            }

            // --- JavaScript Avanzado ---
            if (cursoRepo.findByTituloAndLenguaje("JavaScript Avanzado", Lenguaje.JAVASCRIPT).isEmpty()) {
                Curso jsAvanzado = cursoRepo.save(Curso.builder()
                        .titulo("JavaScript Avanzado")
                        .descripcion("Lleva JavaScript a un nivel profesional con patrones modernos y buenas prácticas.")
                        .nivel(Nivel.AVANZADO)
                        .lenguaje(Lenguaje.JAVASCRIPT)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsAvanzado)
                        .titulo("Event loop y modelo de concurrencia")
                        .url("https://developer.mozilla.org/es/docs/Web/JavaScript/Reference/Execution_model")
                        .orden(1)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsAvanzado)
                        .titulo("Patrones de diseño en aplicaciones JavaScript")
                        .url("https://kinsta.com/es/blog/patrones-de-diseno-javascript/")
                        .orden(2)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsAvanzado)
                        .titulo("Introducción a frameworks modernos (visión general)")
                        .url("https://www.youtube.com/watch?v=NK0tcGv_PQw")
                        .orden(3)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(jsAvanzado)
                        .titulo("Optimización y buenas prácticas")
                        .url("https://www.youtube.com/watch?v=sU4e6_i_ExU")
                        .orden(4)
                        .build());
            }

            // =====================================================
            // PYTHON
            // =====================================================

            // --- Python Básico ---
            if (cursoRepo.findByTituloAndLenguaje("Python Básico", Lenguaje.PYTHON).isEmpty()) {
                Curso pyBasico = cursoRepo.save(Curso.builder()
                        .titulo("Python Básico")
                        .descripcion("Aprende a programar desde cero con Python, un lenguaje simple y potente.")
                        .nivel(Nivel.BASICO)
                        .lenguaje(Lenguaje.PYTHON)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(pyBasico)
                        .titulo("Introducción a Python")
                        .url("https://www.coursera.org/mx/articles/what-is-python-used-for-a-beginners-guide-to-using-python?campaignid=23013457628&adgroupid=&device=c&keyword=&matchtype=&network=x&devicemodel=&creativeid=&assetgroupid=6609642748&targetid=&extensionid=&placement=&gad_campaignid=23309444864")
                        .orden(1)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(pyBasico)
                        .titulo("Instalación de Python")
                        .url("Instalación de Python y uso de la consola")
                        .orden(2)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(pyBasico)
                        .titulo("Tipos de datos y estructuras básicas")
                        .url("https://www.datacamp.com/es/tutorial/data-structures-guide-python")
                        .orden(3)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(pyBasico)
                        .titulo("Control de flujo en Python")
                        .url("https://tutorial.recursospython.com/control-de-flujo/")
                        .orden(4)
                        .build());
            }

            // --- Python Intermedio ---
            if (cursoRepo.findByTituloAndLenguaje("Python Intermedio", Lenguaje.PYTHON).isEmpty()) {
                Curso pyIntermedio = cursoRepo.save(Curso.builder()
                        .titulo("Python Intermedio")
                        .descripcion("Comienza a usar Python en proyectos más útiles con archivos, módulos y librerías.")
                        .nivel(Nivel.INTERMEDIO)
                        .lenguaje(Lenguaje.PYTHON)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(pyIntermedio)
                        .titulo("Funciones, argumentos y módulos")
                        .url("https://www.youtube.com/watch?v=BS_atY4WRyE")
                        .orden(1)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(pyIntermedio)
                        .titulo("Manejo de archivos en Python")
                        .url("https://www.freecodecamp.org/espanol/news/manejo-de-archivos-en-python-como-crear-leer-y-escribir-en-un-archivo/")
                        .orden(2)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(pyIntermedio)
                        .titulo("Entornos virtuales y gestión de paquetes")
                        .url("https://www.youtube.com/watch?v=3ajWWU1A_pk")
                        .orden(3)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(pyIntermedio)
                        .titulo("Uso de librerías externas para tareas comunes")
                        .url("https://immune.institute/blog/librerias-python-que-son/")
                        .orden(4)
                        .build());
            }

            // --- Python Avanzado ---
            if (cursoRepo.findByTituloAndLenguaje("Python Avanzado", Lenguaje.PYTHON).isEmpty()) {
                Curso pyAvanzado = cursoRepo.save(Curso.builder()
                        .titulo("Python Avanzado")
                        .descripcion("Profundiza en POO, manejo avanzado de errores, decoradores y módulos en Python.")
                        .nivel(Nivel.AVANZADO)
                        .lenguaje(Lenguaje.PYTHON)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(pyAvanzado)
                        .titulo("Clases y objetos en detalle")
                        .url("https://www.youtube.com/watch?v=-E4O0mz-gGE")
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
                        .url("https://ellibrodepython.com/decoradores-python")
                        .orden(3)
                        .build());

                leccionRepo.save(Leccion.builder()
                        .curso(pyAvanzado)
                        .titulo("Módulos y paquetes avanzados")
                        .url("https://www.youtube.com/watch?v=rwUVwxh5N74")
                        .orden(4)
                        .build());
            }

            System.out.println("✅ Semillas de cursos verificadas/creadas (Java / JavaScript / Python).");
        };
    }
}
