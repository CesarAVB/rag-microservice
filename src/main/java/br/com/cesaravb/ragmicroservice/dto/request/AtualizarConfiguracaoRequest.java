package br.com.cesaravb.ragmicroservice.dto.request;

public record AtualizarConfiguracaoRequest(
	    String valor,             // obrigatório
	    String descricao,         // opcional
	    String status             // opcional: ATIVO, INATIVO
	) {}