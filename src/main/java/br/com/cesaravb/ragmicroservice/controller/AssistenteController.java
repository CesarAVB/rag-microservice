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

import br.com.cesaravb.ragmicroservice.dto.request.AtualizarAssistenteRequest;
import br.com.cesaravb.ragmicroservice.dto.request.CriarAssistenteRequest;
import br.com.cesaravb.ragmicroservice.dto.response.AssistenteResponse;
import br.com.cesaravb.ragmicroservice.service.AssistenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/assistentes")
@RequiredArgsConstructor
@Slf4j
public class AssistenteController {

    private final AssistenteService service;

    // ==================================
    // # criarAssistente - POST /api/assistentes
    // ==================================
    @PostMapping
    public ResponseEntity<AssistenteResponse> criarAssistente(@Valid @RequestBody CriarAssistenteRequest request) {
        log.info("Criando novo assistente: {}", request.nome());
        AssistenteResponse response = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ==================================
    // # obterAssistente - GET /api/assistentes/{id}
    // ==================================
    @GetMapping("/{id}")
    public ResponseEntity<AssistenteResponse> obterAssistente(@PathVariable Long id) {
        log.info("Obtendo assistente: {}", id);
        AssistenteResponse response = service.obterPorId(id);
        return ResponseEntity.ok(response);
    }

    // ==================================
    // # listarAssistentes - GET /api/assistentes
    // ==================================
    @GetMapping
    public ResponseEntity<List<AssistenteResponse>> listarAssistentes() {
        log.info("Listando assistentes");
        List<AssistenteResponse> response = service.listar();
        return ResponseEntity.ok(response);
    }

    // ==================================
    // # atualizarAssistente - PUT /api/assistentes/{id}
    // ==================================
    @PutMapping("/{id}")
    public ResponseEntity<AssistenteResponse> atualizarAssistente(@PathVariable Long id, @Valid @RequestBody AtualizarAssistenteRequest request) {
        log.info("Atualizando assistente: {}", id);
        AssistenteResponse response = service.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    // ==================================
    // # deletarAssistente - DELETE /api/assistentes/{id}
    // ==================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAssistente(@PathVariable Long id) {
        log.info("Deletando assistente: {}", id);
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}