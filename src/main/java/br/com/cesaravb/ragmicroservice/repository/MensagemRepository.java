package br.com.cesaravb.ragmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cesaravb.ragmicroservice.entity.Mensagem;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    List<Mensagem> findByConversaId(Long conversaId);

    @Query("SELECT SUM(m.tokensTotal) FROM Mensagem m WHERE m.conversa.id = :conversaId")
    Integer sumTokensPorConversa(@Param("conversaId") Long conversaId);

    @Query("SELECT SUM(m.custoConversa) FROM Mensagem m WHERE m.conversa.id = :conversaId")
    BigDecimal sumCustoPorConversa(@Param("conversaId") Long conversaId);

    Integer countByConversaId(Long conversaId);
}