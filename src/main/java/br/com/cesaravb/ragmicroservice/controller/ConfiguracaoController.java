package br.com.cesaravb.ragmicroservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cesaravb.ragmicroservice.dto.request.AtualizarConfiguracaoRequest;
import br.com.cesaravb.ragmicroservice.dto.request.CriarConfiguracaoRequest;
import br.com.cesaravb.ragmicroservice.dto.response.ConfiguracaoResponse;
import br.com.cesaravb.ragmicroservice.service.ConfiguracaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/configuracoes")
@RequiredArgsConstructor
@Slf4j
public class ConfiguracaoController {

    private final ConfiguracaoService service;

    // ==================================
    // # Método - listar - Listar todas as configurações
    // ==================================
    @GetMapping
    public ResponseEntity<List<ConfiguracaoResponse>> listar() {
        List<ConfiguracaoResponse> configuracoes = service.listarTodas();
        return ResponseEntity.ok(configuracoes);
    }

    // ==================================
    // # Método - obterPorId - Obter configuração por ID
    // ==================================
    @GetMapping("/{id}")
    public ResponseEntity<ConfiguracaoResponse> obterPorId(@PathVariable Long id) {
        ConfiguracaoResponse configuracao = service.obterPorId(id);
        return ResponseEntity.ok(configuracao);
    }

    // ==================================
    // # Método - obterPorTipo - Obter configuração por tipo
    // ==================================
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<ConfiguracaoResponse> obterPorTipo(@PathVariable String tipo) {
        ConfiguracaoResponse configuracao = service.obterPorTipo(tipo);
        return ResponseEntity.ok(configuracao);
    }

    // ==================================
    // # Método - criar - Criar nova configuração
    // ==================================
    @PostMapping
    public ResponseEntity<ConfiguracaoResponse> criar(@RequestBody CriarConfiguracaoRequest request) {
        ConfiguracaoResponse configuracao = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(configuracao);
    }

    // ==================================
    // # Método - atualizar - Atualizar configuração
    // ==================================
    @PutMapping("/{id}")
    public ResponseEntity<ConfiguracaoResponse> atualizar(
        @PathVariable Long id,
        @RequestBody AtualizarConfiguracaoRequest request
    ) {
        ConfiguracaoResponse configuracao = service.atualizar(id, request);
        return ResponseEntity.ok(configuracao);
    }

    // ==================================
    // # Método - deletar - Deletar configuração
    // ==================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // ==================================
    // # Método - verificarStatus - Verificar quais configurações estão ativas
    // ==================================
    @GetMapping("/status/checklist")
    public ResponseEntity<ChecklistResponse> verificarStatus() {
        boolean openrouterApiKey = service.estaConfigurada("OPENROUTER_API_KEY");
        boolean embeddingModel = service.estaConfigurada("EMBEDDING_MODEL");
        boolean temperature = service.estaConfigurada("TEMPERATURE");
        boolean maxTokens = service.estaConfigurada("MAX_TOKENS_PER_REQUEST");

        ChecklistResponse checklist = new ChecklistResponse(
            openrouterApiKey,
            embeddingModel,
            temperature,
            maxTokens,
            openrouterApiKey && embeddingModel && maxTokens  // pronto se tem essenciais
        );

        return ResponseEntity.ok(checklist);
    }

    // ==================================
    // # Método - listarModelos - Retornar modelos cadastrados (embedding e llm padrão)
    // ==================================
    @GetMapping("/modelos")
    public ResponseEntity<List<ConfiguracaoResponse>> listarModelos() {
        List<ConfiguracaoResponse> modelos = service.listarModelosCadastrados();
        return ResponseEntity.ok(modelos);
    }

    // ==================================
    // # Método - ChecklistResponse - Response para checklist de status
    // ==================================
    public record ChecklistResponse(
        boolean openrouterApiKeyConfigurada,
        boolean embeddingModelConfigurado,
        boolean temperatureConfigurada,
        boolean maxTokensConfigurado,
        boolean sistemaPronto  // true se todas essenciais estão configuradas
    ) {}
}