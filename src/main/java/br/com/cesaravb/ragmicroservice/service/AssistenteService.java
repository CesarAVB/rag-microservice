package br.com.cesaravb.ragmicroservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cesaravb.ragmicroservice.dto.request.AtualizarAssistenteRequest;
import br.com.cesaravb.ragmicroservice.dto.request.CriarAssistenteRequest;
import br.com.cesaravb.ragmicroservice.dto.response.AssistenteResponse;
import br.com.cesaravb.ragmicroservice.entity.Assistente;
import br.com.cesaravb.ragmicroservice.mapper.AssistenteMapper;
import br.com.cesaravb.ragmicroservice.repository.AssistenteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AssistenteService {

    private final AssistenteRepository repository;
    private final AssistenteMapper mapper;
    private final AuditService auditService;

    // ==================================
    // # criar - Cria novo assistente
    // ==================================
    public AssistenteResponse criar(CriarAssistenteRequest request) {
        if (repository.existsByNome(request.nome())) {
            throw new IllegalArgumentException("Assistente com este nome já existe");
        }

        Assistente assistente = mapper.toEntity(request);
        Assistente salvo = repository.save(assistente);

        auditService.registrarAcao("CRIAR_ASSISTENTE", "ASSISTENTE", salvo.getId(), null);
        log.info("Assistente criado: {}", salvo.getId());

        return mapper.toResponse(salvo);
    }

    // ==================================
    // # obterPorId - Recupera assistente por ID
    // ==================================
    @Transactional(readOnly = true)
    public AssistenteResponse obterPorId(Long id) {
        Assistente assistente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assistente não encontrado"));

        return mapper.toResponse(assistente);
    }

    // ==================================
    // # listar - Lista todos os assistentes ativos
    // ==================================
    @Transactional(readOnly = true)
    public List<AssistenteResponse> listar() {
        return repository.findByStatus("ATIVO")
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ==================================
    // # atualizar - Atualiza assistente existente
    // ==================================
    public AssistenteResponse atualizar(Long id, AtualizarAssistenteRequest request) {
        Assistente assistente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assistente não encontrado"));

        if (request.descricao() != null) {
            assistente.setDescricao(request.descricao());
        }
        if (request.promptSistema() != null) {
            assistente.setPromptSistema(request.promptSistema());
        }
        if (request.instrucoesPersonalizadas() != null) {
            assistente.setInstrucoesPersonalizadas(request.instrucoesPersonalizadas());
        }
        if (request.modeloPadrao() != null) {
            assistente.setModeloPadrao(request.modeloPadrao());
        }
        if (request.status() != null) {
            assistente.setStatus(request.status());
        }

        Assistente atualizado = repository.save(assistente);
        auditService.registrarAcao("ATUALIZAR_ASSISTENTE", "ASSISTENTE", id, null);
        log.info("Assistente atualizado: {}", id);

        return mapper.toResponse(atualizado);
    }

    // ==================================
    // # deletar - Deleta assistente
    // ==================================
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Assistente não encontrado");
        }

        repository.deleteById(id);
        auditService.registrarAcao("DELETAR_ASSISTENTE", "ASSISTENTE", id, null);
        log.info("Assistente deletado: {}", id);
    }

    // ==================================
    // # obterEntidade - Retorna entity para uso interno
    // ==================================
    @Transactional(readOnly = true)
    public Assistente obterEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assistente não encontrado"));
    }
}