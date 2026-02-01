package br.com.cesaravb.ragmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assistentes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assistente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String promptSistema;

    @Column(columnDefinition = "TEXT")
    private String instrucoesPersonalizadas;

    @Column(length = 100)
    private String modeloPadrao;

    @Column(length = 50)
    private String status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "updated_at")
    private LocalDateTime atualizadoEm;

    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
        atualizadoEm = LocalDateTime.now();
        if (status == null) {
            status = "ATIVO";
        }
        if (modeloPadrao == null) {
            modeloPadrao = "mistral-7b-instruct";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        atualizadoEm = LocalDateTime.now();
    }
}