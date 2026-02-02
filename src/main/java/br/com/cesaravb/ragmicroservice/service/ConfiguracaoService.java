package br.com.cesaravb.ragmicroservice.service;

import br.com.cesaravb.ragmicroservice.dto.request.CriarConfiguracaoRequest;
import br.com.cesaravb.ragmicroservice.dto.request.AtualizarConfiguracaoRequest;
import br.com.cesaravb.ragmicroservice.dto.response.ConfiguracaoResponse;
import br.com.cesaravb.ragmicroservice.entity.Configuracao;
import br.com.cesaravb.ragmicroservice.entity.enums.StatusConfiguracao;
import br.com.cesaravb.ragmicroservice.entity.enums.TipoConfiguracao;
import br.com.cesaravb.ragmicroservice.repository.ConfiguracaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConfiguracaoService {

    private final ConfiguracaoRepository repository;

    // ==================================
    // # Métodos - Listar
    // ==================================

    // Retorna lista de todas as configurações do sistema
    public List<ConfiguracaoResponse> listarTodas() {
        log.info("Listando todas as configurações");
        return repository.findAll().stream()
            .map(ConfiguracaoResponse::from)
            .toList();
    }

    // ==================================
    // # Métodos - Obter
    // ==================================

    // Obter configuração por ID
    public ConfiguracaoResponse obterPorId(Long id) {
        log.debug("Obtendo configuração por ID: {}", id);
        Configuracao config = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Configuração não encontrada: " + id));
        return ConfiguracaoResponse.from(config);
    }

    // Obter configuração por tipo (ex: OPENROUTER_API_KEY)
    public ConfiguracaoResponse obterPorTipo(String tipoConfiguracao) {
        log.debug("Obtendo configuração por tipo: {}", tipoConfiguracao);
        
        // Validar se tipo é válido
        if (!TipoConfiguracao.exists(tipoConfiguracao)) {
            throw new IllegalArgumentException("Tipo de configuração inválido: " + tipoConfiguracao);
        }
        
        Configuracao config = repository.findByTipoConfiguracao(tipoConfiguracao)
            .orElseThrow(() -> new IllegalArgumentException("Configuração não encontrada: " + tipoConfiguracao));
        return ConfiguracaoResponse.from(config);
    }

    // Obter valor da configuração (apenas para uso interno)
    // Retorna valor mesmo que seja sensível (para operação interna)
    public String obterValor(String tipoConfiguracao) {
        log.debug("Obtendo valor de configuração: {}", tipoConfiguracao);
        return repository.findByTipoConfiguracao(tipoConfiguracao)
            .map(Configuracao::getValor)
            .orElseThrow(() -> new IllegalArgumentException("Configuração não encontrada: " + tipoConfiguracao));
    }

    // ==================================
    // # Métodos - Criar
    // ==================================

    // Criar nova configuração
    public ConfiguracaoResponse criar(CriarConfiguracaoRequest request) {
        log.info("Criando nova configuração: {}", request.tipoConfiguracao());
        
        // Validar se tipo é válido
        if (!TipoConfiguracao.exists(request.tipoConfiguracao())) {
            throw new IllegalArgumentException("Tipo de configuração inválido: " + request.tipoConfiguracao());
        }
        
        // Validar se já existe
        if (repository.findByTipoConfiguracao(request.tipoConfiguracao()).isPresent()) {
            throw new IllegalArgumentException("Configuração já existe: " + request.tipoConfiguracao());
        }

        // Validar se valor está vazio
        if (request.valor() == null || request.valor().isBlank()) {
            throw new IllegalArgumentException("Valor da configuração não pode estar vazio");
        }

        // Criar entidade
        Configuracao config = new Configuracao();
        config.setTipoConfiguracao(request.tipoConfiguracao());
        config.setChave(request.chave());
        config.setValor(request.valor());
        config.setDescricao(request.descricao());
        config.setEhSensivel(request.ehSensivel() != null ? request.ehSensivel() : true);
        config.setStatus(request.status() != null ? request.status() : StatusConfiguracao.ATIVO.getValor());

        // Salvar
        Configuracao salva = repository.save(config);
        log.info("Configuração criada com sucesso: {} (ID: {})", config.getTipoConfiguracao(), salva.getId());
        return ConfiguracaoResponse.from(salva);
    }

    // ==================================
    // # Métodos - Atualizar
    // ==================================

    // Atualizar configuração existente
    public ConfiguracaoResponse atualizar(Long id, AtualizarConfiguracaoRequest request) {
        log.info("Atualizando configuração ID: {}", id);
        
        // Buscar configuração
        Configuracao config = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Configuração não encontrada: " + id));

        // Validar se novo valor está vazio
        if (request.valor() == null || request.valor().isBlank()) {
            throw new IllegalArgumentException("Valor da configuração não pode estar vazio");
        }

        // Atualizar valor
        config.setValor(request.valor());
        
        // Atualizar descrição se fornecida
        if (request.descricao() != null) {
            config.setDescricao(request.descricao());
        }
        
        // Atualizar status se fornecido
        if (request.status() != null) {
            // Validar status
            try {
                StatusConfiguracao.fromValor(request.status());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Status inválido: " + request.status());
            }
            config.setStatus(request.status());
        }

        // Salvar
        Configuracao atualizada = repository.save(config);
        log.info("Configuração atualizada com sucesso: {} (ID: {})", config.getTipoConfiguracao(), id);
        return ConfiguracaoResponse.from(atualizada);
    }

    // ==================================
    // # Métodos - Deletar
    // ==================================

    // Deletar configuração por ID
    public void deletar(Long id) {
        log.info("Deletando configuração ID: {}", id);
        
        // Buscar configuração
        Configuracao config = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Configuração não encontrada: " + id));
        
        // Deletar
        repository.delete(config);
        log.info("Configuração deletada com sucesso: {} (ID: {})", config.getTipoConfiguracao(), id);
    }

    // ==================================
    // # Métodos - Validar
    // ==================================

    // Verificar se configuração existe e está ativa
    public boolean estaConfigurada(String tipoConfiguracao) {
        return repository.findByTipoConfiguracao(tipoConfiguracao)
            .map(c -> c.getValor() != null 
                && !c.getValor().isBlank() 
                && StatusConfiguracao.ATIVO.getValor().equals(c.getStatus()))
            .orElse(false);
    }

    // Verificar se todas as configurações obrigatórias estão configuradas
    public boolean sistemaPronto() {
        boolean openrouter = estaConfigurada(TipoConfiguracao.OPENROUTER_API_KEY.name());
        boolean embedding = estaConfigurada(TipoConfiguracao.EMBEDDING_MODEL.name());
        boolean dimension = estaConfigurada(TipoConfiguracao.EMBEDDING_DIMENSION.name());
        boolean maxTokens = estaConfigurada(TipoConfiguracao.MAX_TOKENS_PER_REQUEST.name());
        
        boolean pronto = openrouter && embedding && dimension && maxTokens;
        
        if (!pronto) {
            log.warn("Sistema não está pronto. Configurações faltando:");
            if (!openrouter) log.warn("  - OPENROUTER_API_KEY");
            if (!embedding) log.warn("  - EMBEDDING_MODEL");
            if (!dimension) log.warn("  - EMBEDDING_DIMENSION");
            if (!maxTokens) log.warn("  - MAX_TOKENS_PER_REQUEST");
        }
        
        return pronto;
    }

    // ==================================
    // # Métodos - Checklist
    // ==================================

    // Retornar status de todas as configurações essenciais
    public ChecklistConfiguracao gerarChecklist() {
        log.debug("Gerando checklist de configurações");
        
        return new ChecklistConfiguracao(
            estaConfigurada(TipoConfiguracao.OPENROUTER_API_KEY.name()),
            estaConfigurada(TipoConfiguracao.EMBEDDING_MODEL.name()),
            estaConfigurada(TipoConfiguracao.EMBEDDING_DIMENSION.name()),
            estaConfigurada(TipoConfiguracao.MAX_TOKENS_PER_REQUEST.name()),
            sistemaPronto()
        );
    }

    // ==================================
    // # Método - listarModelosCadastrados - Retornar modelos cadastrados (embedding e llm padrão)
    // ==================================
    public List<ConfiguracaoResponse> listarModelosCadastrados() {
        log.debug("Listando modelos cadastrados");
        return repository.findAll().stream()
            .filter(c -> TipoConfiguracao.EMBEDDING_MODEL.name().equals(c.getTipoConfiguracao())
                || TipoConfiguracao.LLM_MODEL_PADRAO.name().equals(c.getTipoConfiguracao()))
            .map(ConfiguracaoResponse::from)
            .toList();
    }

    // ==================================
    // # DTOs Internos
    // ==================================

    public record ChecklistConfiguracao(
        boolean openrouterApiKeyConfigurada,
        boolean embeddingModelConfigurado,
        boolean embeddingDimensionConfigurado,
        boolean maxTokensConfigurado,
        boolean sistemaPronto
    ) {}
}