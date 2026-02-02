package br.com.cesaravb.ragmicroservice.repository;

import br.com.cesaravb.ragmicroservice.entity.Configuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {
    Optional<Configuracao> findByTipoConfiguracao(String tipoConfiguracao);
    Optional<Configuracao> findByChave(String chave);
}