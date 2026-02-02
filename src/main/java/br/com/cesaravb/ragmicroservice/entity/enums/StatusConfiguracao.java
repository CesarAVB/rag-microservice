package br.com.cesaravb.ragmicroservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Status de uma configuração
 */
@Getter
@AllArgsConstructor
public enum StatusConfiguracao {
    
    ATIVO("ATIVO", "Configuração ativa e funcionando"),
    INATIVO("INATIVO", "Configuração desativada");

    private final String valor;
    private final String descricao;

    /**
     * Obter por valor
     */
    public static StatusConfiguracao fromValor(String valor) {
        for (StatusConfiguracao status : values()) {
            if (status.valor.equals(valor)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + valor);
    }

    /**
     * Verificar se é ativo
     */
    public boolean isAtivo() {
        return this == ATIVO;
    }
}