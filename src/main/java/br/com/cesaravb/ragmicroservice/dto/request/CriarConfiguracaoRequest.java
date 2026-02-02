package br.com.cesaravb.ragmicroservice.dto.request;

public record CriarConfiguracaoRequest(
	    String tipoConfiguracao,  // obrigatório
	    String chave,             // obrigatório
	    String valor,             // obrigatório
	    String descricao,         // opcional
	    Boolean ehSensivel,       // default: true
	    String status             // default: "ATIVO"
	) {}