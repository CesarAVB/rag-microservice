package br.com.cesaravb.ragmicroservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cesaravb.ragmicroservice.dto.request.IniciarConversaRequest;
import br.com.cesaravb.ragmicroservice.dto.response.ConversaResponse;
import br.com.cesaravb.ragmicroservice.service.ConversaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/conversas")
@RequiredArgsConstructor
@Slf4j
public class ConversaController {

    private final ConversaService service;

    // ==================================
    // # iniciarConversa - POST /api/conversas
    // ==================================
    @PostMapping
    public ResponseEntity<ConversaResponse> iniciarConversa(
            @Valid @RequestBody IniciarConversaRequest request) {
        log.info("Iniciando conversa com assistente: {}", request.assistenteId());
        ConversaResponse response = service.iniciarConversa(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ==================================
    // # obterConversa - GET /api/conversas/{id}
    // ==================================
    @GetMapping("/{id}")
    public ResponseEntity<ConversaResponse> obterConversa(@PathVariable Long id) {
        log.info("Obtendo conversa: {}", id);
        ConversaResponse response = service.obterPorId(id);
        return ResponseEntity.ok(response);
    }

    // ==================================
    // # listarPorAssistente - GET /api/conversas/assistente/{assistenteId}
    // ==================================
    @GetMapping("/assistente/{assistenteId}")
    public ResponseEntity<List<ConversaResponse>> listarPorAssistente(
            @PathVariable Long assistenteId) {
        log.info("Listando conversas do assistente: {}", assistenteId);
        List<ConversaResponse> response = service.listarPorAssistente(assistenteId);
        return ResponseEntity.ok(response);
    }

    // ==================================
    // # listarPorUsuario - GET /api/conversas/usuario/{usuarioId}
    // ==================================
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ConversaResponse>> listarPorUsuario(
            @PathVariable String usuarioId) {
        log.info("Listando conversas do usuário: {}", usuarioId);
        List<ConversaResponse> response = service.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(response);
    }

    // ==================================
    // # finalizarConversa - PUT /api/conversas/{id}/finalizar
    // ==================================
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<ConversaResponse> finalizarConversa(@PathVariable Long id) {
        log.info("Finalizando conversa: {}", id);
        ConversaResponse response = service.finalizarConversa(id);
        return ResponseEntity.ok(response);
    }
}