package br.com.cesaravb.ragmicroservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarAssistenteRequest(

    @NotBlank(message = "Nome é obrigatório")
    String nome,

    String descricao,

    @NotBlank(message = "Prompt do sistema é obrigatório")
    String promptSistema,

    String instrucoesPersonalizadas,

    String modeloPadrao
) {
}