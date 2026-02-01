package br.com.cesaravb.ragmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cesaravb.ragmicroservice.entity.Conversa;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversaRepository extends JpaRepository<Conversa, Long> {

    List<Conversa> findByAssistenteId(Long assistenteId);

    List<Conversa> findByUsuarioId(String usuarioId);

    List<Conversa> findByAssistenteIdAndStatus(Long assistenteId, String status);

    List<Conversa> findByUsuarioIdAndStatus(String usuarioId, String status);

    Optional<Conversa> findByIdAndAssistenteId(Long conversaId, Long assistenteId);
}