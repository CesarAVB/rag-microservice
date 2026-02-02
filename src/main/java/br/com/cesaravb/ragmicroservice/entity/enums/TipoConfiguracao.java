package br.com.cesaravb.ragmicroservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum com todos os tipos de configuração disponíveis
 * 
 * Cada tipo tem:
 * - chave: Nome da configuração (para uso em DTOs)
 * - descricao: Descrição para admin/documentação
 * - ehSensivel: Se é dados sensível (ex: API key)
 */
@Getter
@AllArgsConstructor
public enum TipoConfiguracao {
    
    // ========================================
    // = API KEYS (SENSÍVEL)
    // ========================================
    
    OPENROUTER_API_KEY(
        "openrouter.api.key",
        "Chave de API OpenRouter para chamadas LLM",
        true
    ),
    
    // ========================================
    // = MODELOS E EMBEDDINGS
    // ========================================
    
    EMBEDDING_MODEL(
        "embedding.model",
        "Modelo para gerar embeddings (ex: text-embedding-3-small)",
        false
    ),
    
    EMBEDDING_DIMENSION(
        "embedding.dimension",
        "Dimensão dos vetores de embedding (ex: 1536)",
        false
    ),
    
    LLM_MODEL_PADRAO(
        "llm.model.padrao",
        "Modelo LLM padrão a utilizar (ex: mistral-7b-instruct)",
        false
    ),
    
    // ========================================
    // = LIMITES
    // ========================================
    
    MAX_TOKENS_PER_REQUEST(
        "llm.max.tokens",
        "Máximo de tokens por requisição ao LLM",
        false
    ),
    
    MAX_FILE_SIZE_MB(
        "upload.max.size.mb",
        "Tamanho máximo de arquivo em MB para upload",
        false
    ),
    
    MAX_CHUNK_SIZE(
        "embedding.max.chunk.size",
        "Número máximo de caracteres por chunk",
        false
    ),
    
    // ========================================
    // = PARÂMETROS DO LLM
    // ========================================
    
    TEMPERATURE(
        "llm.temperature",
        "Temperatura do LLM (0=determinístico, 1=criativo)",
        false
    ),
    
    TOP_P(
        "llm.top.p",
        "Top P para nucleus sampling (0-1)",
        false
    ),
    
    TOP_K(
        "llm.top.k",
        "Top K para top-k sampling",
        false
    ),
    
    // ========================================
    // = TIMEOUT E PERFORMANCE
    // ========================================
    
    TIMEOUT_SEGUNDOS(
        "llm.timeout.segundos",
        "Timeout máximo para chamadas LLM em segundos",
        false
    ),
    
    CHUNK_SIZE(
        "embedding.chunk.size",
        "Tamanho dos chunks para divisão de documento",
        false
    ),
    
    CHUNK_OVERLAP(
        "embedding.chunk.overlap",
        "Overlap entre chunks para melhor contexto",
        false
    ),
    
    // ========================================
    // = VECTOR SEARCH
    // ========================================
    
    VECTOR_SEARCH_LIMIT(
        "vector.search.limit",
        "Número máximo de chunks retornados na busca vetorial",
        false
    ),
    
    VECTOR_SEARCH_MIN_SIMILARITY(
        "vector.search.min.similarity",
        "Similaridade mínima para aceitar resultado na busca vetorial",
        false
    ),
    
    // ========================================
    // = BUDGET/CUSTO
    // ========================================
    
    CUSTO_MAXIMO_CONVERSA(
        "budget.max.conversa",
        "Custo máximo em dólares por conversa",
        false
    ),
    
    CUSTO_MAXIMO_USUARIO(
        "budget.max.usuario",
        "Custo máximo em dólares por usuário por dia",
        false
    ),
    
    // ========================================
    // = LOGGING E DEBUG
    // ========================================
    
    LOG_LEVEL_RAG(
        "logging.level.rag",
        "Nível de log para operações RAG (DEBUG, INFO, WARN)",
        false
    ),
    
    DEBUG_MODE(
        "debug.mode",
        "Habilitar modo debug (true/false)",
        false
    );

    /**
     * Chave da configuração (usada para identificar)
     */
    private final String chave;

    /**
     * Descrição para referência
     */
    private final String descricao;

    /**
     * Se é dados sensível (ex: API key, senha)
     * Se true, valor não é retornado em GET
     */
    private final boolean ehSensivel;

    /**
     * Obter tipo por string
     */
    public static TipoConfiguracao fromString(String tipo) {
        try {
            return TipoConfiguracao.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de configuração inválido: " + tipo, e);
        }
    }

    /**
     * Obter tipo por chave
     */
    public static TipoConfiguracao fromChave(String chave) {
        for (TipoConfiguracao tipo : values()) {
            if (tipo.chave.equals(chave)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Chave de configuração inválida: " + chave);
    }

    /**
     * Verificar se existe tipo
     */
    public static boolean exists(String tipo) {
        try {
            TipoConfiguracao.valueOf(tipo.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}