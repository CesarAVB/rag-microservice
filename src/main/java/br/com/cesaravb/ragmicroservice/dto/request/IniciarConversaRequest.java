package br.com.cesaravb.ragmicroservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IniciarConversaRequest(

    @NotNull(message = "ID do assistente é obrigatório")
    Long assistenteId,

    @NotBlank(message = "ID do usuário é obrigatório")
    String usuarioId,

    String titulo
) {
}