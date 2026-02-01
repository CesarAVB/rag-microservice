package br.com.cesaravb.ragmicroservice.dto.request;

public record AtualizarAssistenteRequest(

    String descricao,

    String promptSistema,

    String instrucoesPersonalizadas,

    String modeloPadrao,

    String status
) {
}
