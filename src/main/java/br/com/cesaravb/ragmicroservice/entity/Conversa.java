package br.com.cesaravb.ragmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "conversas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assistente_id", nullable = false)
    private Assistente assistente;

    @Column(name = "usuario_id", nullable = false, length = 100)
    private String usuarioId;

    @Column(length = 255)
    private String titulo;

    @Column(length = 50)
    private String status;

    @Column(name = "tokens_totais_usados")
    private Integer tokensTotaisUsados;

    @Column(name = "custo_total_conversa", precision = 10, scale = 6)
    private BigDecimal custoTotalConversa;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "finalizada_em")
    private LocalDateTime finalizadaEm;

    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
        if (status == null) {
            status = "ATIVA";
        }
        if (tokensTotaisUsados == null) {
            tokensTotaisUsados = 0;
        }
        if (custoTotalConversa == null) {
            custoTotalConversa = BigDecimal.ZERO;
        }
    }
}