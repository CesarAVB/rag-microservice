package br.com.cesaravb.ragmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cesaravb.ragmicroservice.entity.AuditLog;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByUsuarioId(String usuarioId);

    List<AuditLog> findByAcao(String acao);

    List<AuditLog> findByEntidadeTipoAndEntidadeId(String entidadeTipo, Long entidadeId);

    List<AuditLog> findByUsuarioIdAndAcao(String usuarioId, String acao);
}