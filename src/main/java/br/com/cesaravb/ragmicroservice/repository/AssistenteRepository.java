package br.com.cesaravb.ragmicroservice.repository;

import br.com.cesaravb.ragmicroservice.entity.Assistente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssistenteRepository extends JpaRepository<Assistente, Long> {

    Optional<Assistente> findByNome(String nome);

    List<Assistente> findByStatus(String status);

    boolean existsByNome(String nome);
}