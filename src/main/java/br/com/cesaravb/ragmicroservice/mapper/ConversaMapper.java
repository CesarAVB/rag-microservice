package br.com.cesaravb.ragmicroservice.mapper;

import org.springframework.stereotype.Component;

import br.com.cesaravb.ragmicroservice.dto.response.ConversaResponse;
import br.com.cesaravb.ragmicroservice.entity.Conversa;

@Component
public class ConversaMapper {

    public ConversaResponse toResponse(Conversa entity) {
        return new ConversaResponse(
                entity.getId(),
                entity.getAssistente().getId(),
                entity.getUsuarioId(),
                entity.getTitulo(),
                entity.getStatus(),
                entity.getTokensTotaisUsados(),
                entity.getCustoTotalConversa(),
                entity.getCriadoEm(),
                entity.getFinalizadaEm()
        );
    }
}