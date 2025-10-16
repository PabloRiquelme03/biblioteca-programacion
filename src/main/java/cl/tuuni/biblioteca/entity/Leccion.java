package cl.tuuni.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Leccion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) private Curso curso;

    @Column(nullable=false) private String titulo;
    @Column(nullable=false) private String url; // destino de la lección (video/artículo)
    @Column(nullable=false) private int orden;
}
