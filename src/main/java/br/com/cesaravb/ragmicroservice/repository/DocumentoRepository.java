package br.com.cesaravb.ragmicroservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cesaravb.ragmicroservice.entity.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    List<Documento> findByAssistenteId(Long assistenteId);

    List<Documento> findByAssistenteIdAndStatus(Long assistenteId, String status);

    Optional<Documento> findByAssistenteIdAndNomeArquivo(Long assistenteId, String nomeArquivo);

    boolean existsByAssistenteIdAndNomeArquivo(Long assistenteId, String nomeArquivo);
}