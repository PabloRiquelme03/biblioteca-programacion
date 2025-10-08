package cl.tuuni.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PasswordResetToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) private Usuario usuario;

    @Column(nullable=false, unique=true) private String token;
    @Column(nullable=false) private LocalDateTime expira;
    @Builder.Default private boolean usado = false;

    public boolean valido() {
        return !usado && LocalDateTime.now().isBefore(expira);
    }
}
