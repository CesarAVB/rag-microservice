package br.com.cesaravb.ragmicroservice.dto.response;

import java.time.LocalDateTime;

public record DocumentoResponse(

    Long id,

    Long assistenteId,

    String nomeArquivo,

    String nomeOriginal,

    String tipoArquivo,

    Long tamanhoBytes,

    String status,

    LocalDateTime processadoEm,

    String erroProcessamento,

    LocalDateTime criadoEm
) {
}