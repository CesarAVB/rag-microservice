package br.com.cesaravb.ragmicroservice.dto.response;

import java.time.LocalDateTime;

import br.com.cesaravb.ragmicroservice.entity.Configuracao;

public record ConfiguracaoResponse(
    Long id,
    String tipoConfiguracao,
    String chave,
    String valor,
    String descricao,
    Boolean ehSensivel,
    String status,
    Boolean estaConfigurado,
    LocalDateTime criadoEm,
    LocalDateTime atualizadoEm
) {
    public static ConfiguracaoResponse from(Configuracao config) {
        // Não retorna valor se for sensível
        String valor = config.getEhSensivel() ? null : config.getValor();
        boolean estaConfigurado = config.getValor() != null && !config.getValor().isBlank();
        
        return new ConfiguracaoResponse(
            config.getId(),
            config.getTipoConfiguracao(),
            config.getChave(),
            valor,
            config.getDescricao(),
            config.getEhSensivel(),
            config.getStatus(),
            estaConfigurado,
            config.getCriadoEm(),
            config.getAtualizadoEm()
        );
    }
}