package br.com.cesaravb.ragmicroservice.dto.response;

public record ChecklistConfiguracao(
    boolean openrouterApiKeyConfigurada,
    boolean embeddingModelConfigurado,
    boolean embeddingDimensionConfigurado,
    boolean maxTokensConfigurado,
    boolean sistemaPronto
) {}