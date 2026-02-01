package br.com.cesaravb.ragmicroservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnviarMensagemRequest(

    @NotNull(message = "ID da conversa é obrigatório")
    Long conversaId,

    @NotBlank(message = "Pergunta é obrigatória")
    String pergunta,

    String modeloLLM
) {
}