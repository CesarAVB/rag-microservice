package br.com.cesaravb.ragmicroservice.dto.response;

import java.time.LocalDateTime;

public record AssistenteResponse(

    Long id,

    String nome,

    String descricao,

    String promptSistema,

    String instrucoesPersonalizadas,

    String modeloPadrao,

    String status,

    LocalDateTime criadoEm,

    LocalDateTime atualizadoEm
) {
}