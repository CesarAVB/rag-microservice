package br.com.cesaravb.ragmicroservice.mapper;

import org.springframework.stereotype.Component;

import br.com.cesaravb.ragmicroservice.dto.response.DocumentoResponse;
import br.com.cesaravb.ragmicroservice.entity.Documento;

@Component
public class DocumentoMapper {

    public DocumentoResponse toResponse(Documento entity) {
        return new DocumentoResponse(
                entity.getId(),
                entity.getAssistente().getId(),
                entity.getNomeArquivo(),
                entity.getNomeOriginal(),
                entity.getTipoArquivo(),
                entity.getTamanhoBytes(),
                entity.getStatus(),
                entity.getProcessadoEm(),
                entity.getErroProcessamento(),
                entity.getCriadoEm()
        );
    }
}