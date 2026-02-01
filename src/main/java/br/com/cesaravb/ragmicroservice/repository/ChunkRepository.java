package br.com.cesaravb.ragmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cesaravb.ragmicroservice.entity.Chunk;

import java.util.List;

@Repository
public interface ChunkRepository extends JpaRepository<Chunk, Long> {

    List<Chunk> findByDocumentoId(Long documentoId);

    Integer countByDocumentoId(Long documentoId);

    void deleteByDocumentoId(Long documentoId);
}