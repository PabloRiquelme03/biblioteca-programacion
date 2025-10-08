package cl.tuuni.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Curso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false) private String titulo;
    @Column(length=1000) private String descripcion;

    @Enumerated(EnumType.STRING) @Column(nullable=false)
    private Nivel nivel;            // reutilizamos tu enum Nivel

    @Enumerated(EnumType.STRING) @Column(nullable=false)
    private Lenguaje lenguaje;      // reutilizamos tu enum Lenguaje

    @Builder.Default
    private boolean activo = true;
}
