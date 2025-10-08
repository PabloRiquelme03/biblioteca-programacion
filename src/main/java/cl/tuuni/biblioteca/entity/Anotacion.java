package cl.tuuni.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Anotacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) private Usuario usuario;

    private Long recursoId;  // opcional: enlazar a recurso/lección
    private Long leccionId;  // opcional

    @Column(nullable=false) private String titulo;
    @Column(nullable=false, length=4000) private String contenido;

    @Builder.Default private LocalDateTime creado = LocalDateTime.now();
    @Builder.Default private LocalDateTime actualizado = LocalDateTime.now();

    @PreUpdate void touch() { actualizado = LocalDateTime.now(); }
}
