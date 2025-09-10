package cl.tuuni.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recurso_educativo")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RecursoEducativo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 2000)
    private String descripcion;

    @Column(nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    private TipoRecurso tipo;

    @Enumerated(EnumType.STRING)
    private Lenguaje lenguaje;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    @Builder.Default
    private boolean activo = true;

    @Builder.Default
    private LocalDateTime creadoEn = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (creadoEn == null) creadoEn = LocalDateTime.now();
    }
}
