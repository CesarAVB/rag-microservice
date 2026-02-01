package br.com.cesaravb.ragmicroservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ConversaResponse(

    Long id,

    Long assistenteId,

    String usuarioId,

    String titulo,

    String status,

    Integer tokensTotaisUsados,

    BigDecimal custoTotalConversa,

    LocalDateTime criadoEm,

    LocalDateTime finalizadaEm
) {
}