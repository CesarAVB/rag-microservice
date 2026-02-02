package br.com.cesaravb.ragmicroservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Tipos de arquivo suportados para upload
 */
@Getter
@AllArgsConstructor
public enum TipoArquivo {
    
    PDF(
        "PDF",
        "application/pdf",
        ".pdf",
        "Documento PDF"
    ),
    
    TXT(
        "TXT",
        "text/plain",
        ".txt",
        "Arquivo de texto"
    ),
    
    DOCX(
        "DOCX",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
        ".docx",
        "Documento Word"
    );

    private final String tipo;
    private final String mimeType;
    private final String extensao;
    private final String descricao;

    /**
     * Obter por extensão
     */
    public static TipoArquivo fromExtensao(String extensao) {
        String ext = extensao.toLowerCase();
        for (TipoArquivo tipo : values()) {
            if (tipo.extensao.equals(ext)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de arquivo não suportado: " + extensao);
    }

    /**
     * Verificar se é suportado
     */
    public static boolean isSuportado(String nomeArquivo) {
        String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf(".")).toLowerCase();
        for (TipoArquivo tipo : values()) {
            if (tipo.extensao.equals(extensao)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obter por tipo
     */
    public static TipoArquivo fromTipo(String tipo) {
        for (TipoArquivo t : values()) {
            if (t.tipo.equals(tipo)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Tipo de arquivo inválido: " + tipo);
    }
}