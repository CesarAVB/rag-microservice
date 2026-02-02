package br.com.cesaravb.ragmicroservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Modelos de embedding disponíveis
 */
@Getter
@AllArgsConstructor
public enum ModeloEmbedding {
    
    TEXT_EMBEDDING_3_SMALL(
        "text-embedding-3-small",
        "Text Embedding 3 Small",
        1536,
        "Modelo pequeno e rápido",
        true  // padrão
    ),
    
    TEXT_EMBEDDING_3_LARGE(
        "text-embedding-3-large",
        "Text Embedding 3 Large",
        3072,
        "Modelo grande com melhor qualidade",
        false
    ),
    
    TEXT_EMBEDDING_ADA_002(
        "text-embedding-ada-002",
        "Text Embedding Ada 002",
        1536,
        "Modelo anterior, ainda funcional",
        false
    );

    private final String nomeApi;
    private final String nomeExibicao;
    private final int dimensao;
    private final String descricao;
    private final boolean padrao;

    /**
     * Obter por nome da API
     */
    public static ModeloEmbedding fromNomeApi(String nomeApi) {
        for (ModeloEmbedding modelo : values()) {
            if (modelo.nomeApi.equals(nomeApi)) {
                return modelo;
            }
        }
        throw new IllegalArgumentException("Modelo de embedding não encontrado: " + nomeApi);
    }

    /**
     * Obter padrão
     */
    public static ModeloEmbedding getPadrao() {
        for (ModeloEmbedding modelo : values()) {
            if (modelo.padrao) {
                return modelo;
            }
        }
        return TEXT_EMBEDDING_3_SMALL;
    }
}