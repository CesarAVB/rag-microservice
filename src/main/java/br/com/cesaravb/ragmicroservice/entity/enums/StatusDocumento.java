package br.com.cesaravb.ragmicroservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Status de processamento de documento
 */
@Getter
@AllArgsConstructor
public enum StatusDocumento {
    
    PROCESSANDO(
        "PROCESSANDO",
        "Documento está sendo processado (dividindo em chunks, gerando embeddings)"
    ),
    
    PROCESSADO(
        "PROCESSADO",
        "Documento processado com sucesso e pronto para uso em RAG"
    ),
    
    ERRO(
        "ERRO",
        "Erro durante o processamento do documento"
    );

    private final String valor;
    private final String descricao;

    /**
     * Obter por valor
     */
    public static StatusDocumento fromValor(String valor) {
        for (StatusDocumento status : values()) {
            if (status.valor.equals(valor)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status de documento inválido: " + valor);
    }

    /**
     * Verificar se está pronto para uso
     */
    public boolean estaPronto() {
        return this == PROCESSADO;
    }

    /**
     * Verificar se está em processamento
     */
    public boolean estaProcessando() {
        return this == PROCESSANDO;
    }

    /**
     * Verificar se houve erro
     */
    public boolean temErro() {
        return this == ERRO;
    }
}