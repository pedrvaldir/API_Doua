package com.doua.domain.comentario;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComentarioRepository extends CrudRepository<Comentario, Long> {
    List<Comentario> findByAcaoId( Long idAcao);
}
