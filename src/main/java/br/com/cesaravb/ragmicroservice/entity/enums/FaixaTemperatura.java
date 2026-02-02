package br.com.cesaravb.ragmicroservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Faixas de temperatura para controlar criatividade do LLM
 * 0.0 = determinístico, 1.0 = muito criativo
 */
@Getter
@AllArgsConstructor
public enum FaixaTemperatura {
    
    MUITO_DETERMINISTICA(
        0.0,
        0.3,
        "Muito Determinística",
        "Respostas muito consistentes e previsíveis",
        "Tarefas técnicas, cálculos, respostas exatas"
    ),
    
    DETERMINISTICA(
        0.3,
        0.5,
        "Determinística",
        "Respostas consistentes com pouca variação",
        "Respostas factuais, traduções, resumos"
    ),
    
    BALANCEADA(
        0.5,
        0.7,
        "Balanceada",
        "Balanço entre consistência e criatividade",
        "Maioria das aplicações, chat geral, Q&A"
    ),
    
    CRIATIVA(
        0.7,
        0.85,
        "Criativa",
        "Respostas mais criativas e variadas",
        "Brainstorming, geração de conteúdo, histórias"
    ),
    
    MUITO_CRIATIVA(
        0.85,
        1.0,
        "Muito Criativa",
        "Máxima criatividade, respostas muito variadas",
        "Ficção científica, poesia, ideias inovadoras"
    );

    private final double valorMinimo;
    private final double valorMaximo;
    private final String nome;
    private final String descricao;
    private final String usoCaso;

    /**
     * Obter faixa a partir de um valor
     */
    public static FaixaTemperatura fromValor(double valor) {
        if (!isValido(valor)) {
            throw new IllegalArgumentException("Temperatura fora da faixa válida (0-1): " + valor);
        }
        
        for (FaixaTemperatura faixa : values()) {
            if (valor >= faixa.valorMinimo && valor <= faixa.valorMaximo) {
                return faixa;
            }
        }
        
        return BALANCEADA;  // fallback
    }

    /**
     * Obter faixa recomendada
     */
    public static FaixaTemperatura getRecomendada() {
        return BALANCEADA;
    }

    /**
     * Validar se temperatura é válida
     */
    public static boolean isValido(double temperatura) {
        return temperatura >= 0.0 && temperatura <= 1.0;
    }

    /**
     * Obter valor médio da faixa
     */
    public double getValorMedio() {
        return (valorMinimo + valorMaximo) / 2.0;
    }
}