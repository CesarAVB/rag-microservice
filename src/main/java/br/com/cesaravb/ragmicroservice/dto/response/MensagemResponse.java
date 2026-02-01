package br.com.cesaravb.ragmicroservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MensagemResponse(

    Long id,

    Long conversaId,

    String pergunta,

    String resposta,

    Integer tokensInput,

    Integer tokensOutput,

    Integer tokensTotal,

    BigDecimal custoConversa,

    String modeloUtilizado,

    Integer chunksRecuperados,

    LocalDateTime criadoEm
) {
}