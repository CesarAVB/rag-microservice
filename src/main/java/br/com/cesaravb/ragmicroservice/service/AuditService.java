package br.com.cesaravb.ragmicroservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cesaravb.ragmicroservice.entity.AuditLog;
import br.com.cesaravb.ragmicroservice.repository.AuditLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuditService {

    private final AuditLogRepository repository;
    private final ObjectMapper objectMapper;

    // ==================================
    // # registrarAcao - Registra ação de usuário
    // ==================================
    public void registrarAcao(String acao, String entidadeTipo, Long entidadeId, JsonNode detalhes) {
        try {
            String usuarioId = obterUsuarioId();
            String ipOrigem = obterIpOrigem();
            String userAgent = obterUserAgent();

            AuditLog log = AuditLog.builder()
                    .usuarioId(usuarioId)
                    .acao(acao)
                    .entidadeTipo(entidadeTipo)
                    .entidadeId(entidadeId)
                    .detalhes(detalhes)
                    .ipOrigem(ipOrigem)
                    .userAgent(userAgent)
                    .build();

            repository.save(log);
            //log.info("Auditoria registrada: {} - {} - {}", acao, entidadeTipo, entidadeId);
        } catch (Exception e) {
            log.error("Erro ao registrar auditoria", e);
        }
    }

    // ==================================
    // # obterUsuarioId - Obtém ID do usuário da sessão
    // ==================================
    private String obterUsuarioId() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String userId = request.getHeader("X-User-Id");
                return userId != null ? userId : "ANONIMO";
            }
        } catch (Exception e) {
            log.debug("Erro ao obter usuário", e);
        }
        return "ANONIMO";
    }

    // ==================================
    // # obterIpOrigem - Obtém IP da requisição
    // ==================================
    private String obterIpOrigem() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String ip = request.getHeader("X-Forwarded-For");
                if (ip == null || ip.isEmpty()) {
                    ip = request.getRemoteAddr();
                }
                return ip;
            }
        } catch (Exception e) {
            log.debug("Erro ao obter IP", e);
        }
        return "DESCONHECIDO";
    }

    // ==================================
    // # obterUserAgent - Obtém User-Agent da requisição
    // ==================================
    private String obterUserAgent() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                return request.getHeader("User-Agent");
            }
        } catch (Exception e) {
            log.debug("Erro ao obter User-Agent", e);
        }
        return "DESCONHECIDO";
    }
}