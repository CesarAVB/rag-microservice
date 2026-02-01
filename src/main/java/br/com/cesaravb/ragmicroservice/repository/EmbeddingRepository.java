package br.com.cesaravb.ragmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cesaravb.ragmicroservice.entity.Embedding;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmbeddingRepository extends JpaRepository<Embedding, Long> {

    Optional<Embedding> findByChunkId(Long chunkId);

    @Query(value = "SELECT e.* FROM embeddings e " +
           "JOIN chunks c ON e.chunk_id = c.id " +
           "JOIN documentos d ON c.documento_id = d.id " +
           "WHERE d.assistente_id = :assistenteId " +
           "AND d.status = 'PROCESSADO' " +
           "ORDER BY e.vetor <-> :vetor LIMIT :limite", 
           nativeQuery = true)
    List<Embedding> buscarPorSimilaridade(
            @Param("assistenteId") Long assistenteId,
            @Param("vetor") Object vetor,
            @Param("limite") Integer limite
    );

    void deleteByChunkId(Long chunkId);
}