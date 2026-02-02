package br.com.cesaravb.ragmicroservice.dto.response;

/**
 * DTO para checklist de configurações no serviço
 */
public record ChecklistConfiguracao(
    boolean openrouterApiKeyConfigurada,
    boolean embeddingModelConfigurado,
    boolean embeddingDimensionConfigurado,
    boolean maxTokensConfigurado,
    boolean sistemaPronto
) {}