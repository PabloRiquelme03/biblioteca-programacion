package cl.tuuni.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anotacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con usuario: toda anotación pertenece a un usuario
    @ManyToOne(optional = false)
    private Usuario usuario;

    // Enlaces opcionales (por si luego asocias notas a recursos o lecciones específicas)
    private Long recursoId;
    private Long leccionId;

    // --- Campos principales ---
    @Column(nullable = false, length = 50)
    private String titulo; // Máx 50 caracteres

    @Column(nullable = false, length = 300)
    private String contenido; // Máx 300 caracteres

    // --- Fechas automáticas ---
    @Builder.Default
    private LocalDateTime creado = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime actualizado = LocalDateTime.now();

    // --- Actualiza la fecha cuando se modifica ---
    @PreUpdate
    void touch() {
        actualizado = LocalDateTime.now();
    }
}
