package br.com.cesaravb.ragmicroservice.mapper;

import org.springframework.stereotype.Component;

import br.com.cesaravb.ragmicroservice.dto.request.CriarAssistenteRequest;
import br.com.cesaravb.ragmicroservice.dto.response.AssistenteResponse;
import br.com.cesaravb.ragmicroservice.entity.Assistente;

@Component
public class AssistenteMapper {

    public Assistente toEntity(CriarAssistenteRequest request) {
        return Assistente.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .promptSistema(request.promptSistema())
                .instrucoesPersonalizadas(request.instrucoesPersonalizadas())
                .modeloPadrao(request.modeloPadrao() != null ? request.modeloPadrao() : "mistral-7b-instruct")
                .status("ATIVO")
                .build();
    }

    public AssistenteResponse toResponse(Assistente entity) {
        return new AssistenteResponse(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPromptSistema(),
                entity.getInstrucoesPersonalizadas(),
                entity.getModeloPadrao(),
                entity.getStatus(),
                entity.getCriadoEm(),
                entity.getAtualizadoEm()
        );
    }
}