package br.com.cesaravb.ragmicroservice.dto.response;

public record ChecklistResponse(
    boolean openrouterApiKeyConfigurada,
    boolean embeddingModelConfigurado,
    boolean temperatureConfigurada,
    boolean maxTokensConfigurado,
    boolean sistemaPronto  // true se todas essenciais estão configuradas
) {}