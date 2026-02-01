package br.com.cesaravb.ragmicroservice.dto.response;

import java.math.BigDecimal;

public record CustoResponse(

    Long conversaId,

    Integer tokensTotal,

    BigDecimal custoTotal,

    String moeda
) {
}