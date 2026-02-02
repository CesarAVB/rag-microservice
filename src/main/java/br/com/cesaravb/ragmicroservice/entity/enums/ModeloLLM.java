package br.com.cesaravb.ragmicroservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Modelos LLM disponíveis via OpenRouter
 */
@Getter
@AllArgsConstructor
public enum ModeloLLM {
    
    MISTRAL_7B(
        "mistral-7b-instruct",
        "Mistral 7B Instruct",
        "Modelo rápido e eficiente",
        true  // padrão
    ),
    
    MISTRAL_8X7B(
        "mistral-8x7b-instruct",
        "Mistral 8x7B (MoE)",
        "Mixture of Experts, mais poderoso",
        false
    ),
    
    OPENROUTER_AUTO(
        "openrouter/auto",
        "Auto (Balanceamento)",
        "OpenRouter escolhe automaticamente",
        false
    ),
    
    GPT_4_TURBO(
        "gpt-4-turbo",
        "GPT-4 Turbo",
        "Modelo GPT-4 otimizado",
        false
    ),
    
    CLAUDE_3_OPUS(
        "claude-3-opus",
        "Claude 3 Opus",
        "Modelo mais poderoso",
        false
    ),
    
    CLAUDE_3_SONNET(
        "claude-3-sonnet",
        "Claude 3 Sonnet",
        "Modelo balanceado",
        false
    );

    private final String nomeApi;
    private final String nomeExibicao;
    private final String descricao;
    private final boolean padrao;

    /**
     * Obter por nome da API
     */
    public static ModeloLLM fromNomeApi(String nomeApi) {
        for (ModeloLLM modelo : values()) {
            if (modelo.nomeApi.equals(nomeApi)) {
                return modelo;
            }
        }
        throw new IllegalArgumentException("Modelo não encontrado: " + nomeApi);
    }

    /**
     * Obter padrão
     */
    public static ModeloLLM getPadrao() {
        for (ModeloLLM modelo : values()) {
            if (modelo.padrao) {
                return modelo;
            }
        }
        return MISTRAL_7B;
    }
}