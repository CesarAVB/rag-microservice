package br.com.cesaravb.ragmicroservice.mapper;

import org.springframework.stereotype.Component;

import br.com.cesaravb.ragmicroservice.dto.response.MensagemResponse;
import br.com.cesaravb.ragmicroservice.entity.Mensagem;

@Component
public class MensagemMapper {

    public MensagemResponse toResponse(Mensagem entity) {
        return new MensagemResponse(
                entity.getId(),
                entity.getConversa().getId(),
                entity.getPergunta(),
                entity.getResposta(),
                entity.getTokensInput(),
                entity.getTokensOutput(),
                entity.getTokensTotal(),
                entity.getCustoConversa(),
                entity.getModeloUtilizado(),
                entity.getChunksRecuperados(),
                entity.getCriadoEm()
        );
    }
}