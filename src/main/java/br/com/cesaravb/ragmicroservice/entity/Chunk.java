package br.com.cesaravb.ragmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chunks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento_id", nullable = false)
    private Documento documento;

    @Column(name = "numero_chunk", nullable = false)
    private Integer numeroChunk;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "tamanho_caracteres")
    private Integer tamanhoCaracteres;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
        if (conteudo != null) {
            tamanhoCaracteres = conteudo.length();
        }
    }
}