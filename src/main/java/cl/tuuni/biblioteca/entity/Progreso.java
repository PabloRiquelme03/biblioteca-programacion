package cl.tuuni.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "progresos",
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "recurso_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Progreso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(optional = false) @JoinColumn(name = "recurso_id")
    private RecursoEducativo recurso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private EstadoProgreso estado = EstadoProgreso.COMPLETADO;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime fecha = LocalDateTime.now();
}
