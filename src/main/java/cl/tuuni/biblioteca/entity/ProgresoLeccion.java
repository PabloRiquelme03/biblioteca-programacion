package cl.tuuni.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"inscripcion_id","leccion_id"}))
public class ProgresoLeccion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) private Inscripcion inscripcion;
    @ManyToOne(optional=false) private Leccion leccion;

    @Builder.Default
    private LocalDateTime completadoAt = LocalDateTime.now();
}
