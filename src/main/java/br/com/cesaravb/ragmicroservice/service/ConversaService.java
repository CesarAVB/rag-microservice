package br.com.cesaravb.ragmicroservice.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cesaravb.ragmicroservice.dto.request.IniciarConversaRequest;
import br.com.cesaravb.ragmicroservice.dto.response.ConversaResponse;
import br.com.cesaravb.ragmicroservice.entity.Assistente;
import br.com.cesaravb.ragmicroservice.entity.Conversa;
import br.com.cesaravb.ragmicroservice.mapper.ConversaMapper;
import br.com.cesaravb.ragmicroservice.repository.ConversaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConversaService {

    private final ConversaRepository repository;
    private final ConversaMapper mapper;
    private final AssistenteService assistenteService;
    private final AuditService auditService;

    // ==================================
    // # iniciarConversa - Cria nova conversa
    // ==================================
    public ConversaResponse iniciarConversa(IniciarConversaRequest request) {
        Assistente assistente = assistenteService.obterEntidade(request.assistenteId());

        Conversa conversa = Conversa.builder()
                .assistente(assistente)
                .usuarioId(request.usuarioId())
                .titulo(request.titulo())
                .status("ATIVA")
                .tokensTotaisUsados(0)
                .custoTotalConversa(BigDecimal.ZERO)
                .build();

        Conversa salva = repository.save(conversa);
        auditService.registrarAcao("INICIAR_CONVERSA", "CONVERSA", salva.getId(), null);
        log.info("Conversa iniciada: {} - Assistente: {}", salva.getId(), request.assistenteId());

        return mapper.toResponse(salva);
    }

    // ==================================
    // # obterPorId - Recupera conversa por ID
    // ==================================
    @Transactional(readOnly = true)
    public ConversaResponse obterPorId(Long id) {
        Conversa conversa = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conversa não encontrada"));

        return mapper.toResponse(conversa);
    }

    // ==================================
    // # listarPorAssistente - Lista conversas de um assistente
    // ==================================
    @Transactional(readOnly = true)
    public List<ConversaResponse> listarPorAssistente(Long assistenteId) {
        return repository.findByAssistenteId(assistenteId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ==================================
    // # listarPorUsuario - Lista conversas de um usuário
    // ==================================
    @Transactional(readOnly = true)
    public List<ConversaResponse> listarPorUsuario(String usuarioId) {
        return repository.findByUsuarioId(usuarioId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ==================================
    // # finalizarConversa - Marca conversa como finalizada
    // ==================================
    public ConversaResponse finalizarConversa(Long id) {
        Conversa conversa = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conversa não encontrada"));

        conversa.setStatus("FINALIZADA");
        conversa.setFinalizadaEm(LocalDateTime.now());

        Conversa atualizada = repository.save(conversa);
        auditService.registrarAcao("FINALIZAR_CONVERSA", "CONVERSA", id, null);
        log.info("Conversa finalizada: {}", id);

        return mapper.toResponse(atualizada);
    }

    // ==================================
    // # obterEntidade - Retorna entity para uso interno
    // ==================================
    @Transactional(readOnly = true)
    public Conversa obterEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conversa não encontrada"));
    }

    // ==================================
    // # atualizarTokensECusto - Atualiza totais da conversa
    // ==================================
    public void atualizarTokensECusto(Long conversaId, Integer tokensAdicionais, BigDecimal custoAdicionado) {
        Conversa conversa = obterEntidade(conversaId);

        conversa.setTokensTotaisUsados(conversa.getTokensTotaisUsados() + tokensAdicionais);
        conversa.setCustoTotalConversa(conversa.getCustoTotalConversa().add(custoAdicionado));

        repository.save(conversa);
        log.debug("Tokens e custo atualizados - Conversa: {}, Tokens: {}, Custo: {}", 
                  conversaId, tokensAdicionais, custoAdicionado);
    }
}