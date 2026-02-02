package br.com.cesaravb.ragmicroservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Status de uma conversa
 */
@Getter
@AllArgsConstructor
public enum StatusConversa {
    
    ATIVA(
        "ATIVA",
        "Conversa aberta e recebendo mensagens"
    ),
    
    FINALIZADA(
        "FINALIZADA",
        "Conversa fechada, não aceita mais mensagens"
    );

    private final String valor;
    private final String descricao;

    /**
     * Obter por valor
     */
    public static StatusConversa fromValor(String valor) {
        for (StatusConversa status : values()) {
            if (status.valor.equals(valor)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status de conversa inválido: " + valor);
    }

    /**
     * Verificar se está ativa
     */
    public boolean estaAtiva() {
        return this == ATIVA;
    }

    /**
     * Verificar se está finalizada
     */
    public boolean estaFinalizada() {
        return this == FINALIZADA;
    }
}