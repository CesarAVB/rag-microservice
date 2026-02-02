package br.com.cesaravb.ragmicroservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "configuracao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Configuracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_configuracao", nullable = false, unique = true)
    private String tipoConfiguracao;

    @Column(nullable = false)
    private String chave;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String valor;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "eh_sensivel")
    private Boolean ehSensivel = true;

    @Column(nullable = false)
    private String status = "ATIVO";

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    public enum TipoConfiguracao {
        // API Keys
        OPENROUTER_API_KEY("openrouter.api.key", "Chave de API OpenRouter", true),
        
        // Modelos
        EMBEDDING_MODEL("embedding.model", "Modelo para embeddings", false),
        EMBEDDING_DIMENSION("embedding.dimension", "Dimensão dos vetores", false),
        LLM_MODEL_PADRAO("llm.model.padrao", "Modelo LLM padrão", false),
        
        // Limites
        MAX_TOKENS_PER_REQUEST("llm.max.tokens", "Máximo de tokens por requisição", false),
        MAX_FILE_SIZE_MB("upload.max.size.mb", "Tamanho máximo de arquivo em MB", false),
        
        // Parâmetros LLM
        TEMPERATURE("llm.temperature", "Temperatura do LLM (0-1)", false),
        TOP_P("llm.top.p", "Top P do LLM (0-1)", false),
        TOP_K("llm.top.k", "Top K do LLM", false),
        
        // Budget/Custo
        CUSTO_MAXIMO_CONVERSA("budget.max.conversa", "Custo máximo por conversa em dólares", false),
        CUSTO_MAXIMO_USUARIO("budget.max.usuario", "Custo máximo por usuário em dólares", false),
        
        // Timeout/Performance
        TIMEOUT_SEGUNDOS("llm.timeout.segundos", "Timeout para chamadas LLM", false),
        CHUNK_SIZE("embedding.chunk.size", "Tamanho dos chunks para embedding", false),
        CHUNK_OVERLAP("embedding.chunk.overlap", "Overlap entre chunks", false),
        
        // Vector Search
        VECTOR_SEARCH_LIMIT("vector.search.limit", "Número máximo de resultados na busca vetorial", false);

        public final String chave;
        public final String descricao;
        public final boolean ehSensivel;

        TipoConfiguracao(String chave, String descricao, boolean ehSensivel) {
            this.chave = chave;
            this.descricao = descricao;
            this.ehSensivel = ehSensivel;
        }
    }
}